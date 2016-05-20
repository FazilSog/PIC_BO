package com.sogeti.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IMembreDAO;
import com.sogeti.dao.model.ClientDO;
import com.sogeti.dao.model.MembreDO;
import com.sogeti.dao.model.RoleDO;
import com.sogeti.exception.DaoException;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author syahiaou
 *
 */

@Service
@Transactional
public class MembreDAOImpl implements IMembreDAO {
	
	// Initialisation du LOGGER
	private Logger lLOGGER = Logger.getLogger(MembreDAOImpl.class);

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */

	public MembreDO AuthentifierMembre(final String pUsername, final String pPassword) throws DaoException {
		
		lLOGGER.info("Début méthode : AuthentifierMembre");
		// on vérifie si le membre existe dans la table
		final MembreDO lMembreDO = findMembreByNameAndPass(pUsername, pPassword);
		// si le membre n'existe pas dans la table
		if (lMembreDO == null)
		{
			throw new DaoException("Login / mdp invalide ou user désactivé");
		}
		
		lLOGGER.info("Fin méthode : AuthentifierMembre");
		return lMembreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO findMembreById(final int pIdMembre) throws DaoException {
		
		lLOGGER.info("Début méthode : getMembre");
		
		MembreDO lMembreDO = null;
		
		if (pIdMembre == 0) {
			throw new DaoException("L'identifiant est obligatoire. Valeur zéro est interdit.");
		} else {

			try {
				lLOGGER.info("On recupére l'utilisateur à partie de son Id: " + pIdMembre);
				// create a new criteria
				final Criteria lCrit = HibernateSessionFactory.getSession().createCriteria(MembreDO.class);
				lCrit.add(Restrictions.eq("idMembre", pIdMembre));
				lMembreDO = (MembreDO) lCrit.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete getMembre().");
				throw new DaoException("Connexion échoué : Identifiant inconnu");
			}
		}
		lLOGGER.info("Fin méthode : getMembre");
		return lMembreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO findMembreByNameAndPass(final String pUsername, final String pPassword) throws DaoException {
		
		//Initialisation de LOGGER
		lLOGGER.info("Début méthode : getMembre");
		
		MembreDO lMembreDO = null;
		
		// si le user ou le pwd sont null on va lever une Exception 
		if (pUsername == null || pPassword == null) {
			throw new DaoException("Le login et mot de passe sont obligatoires.Les valeurs NULL sont interdites.");
		}
		// on recupere le resulat unique du user et du pwd(s'ils sont egaux) 
		try {
			final Criteria lCrit = HibernateSessionFactory.getSession().createCriteria(MembreDO.class);
			lCrit.add(Restrictions.eq("username", pUsername));
			lCrit.add(Restrictions.eq("password", pPassword));
		
			// Retourne l'objet Membre
			lMembreDO =  (MembreDO) lCrit.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete getMembre().");
			throw new DaoException("Connexion échoué : USER/MDP incorrect");
		}
		
		lLOGGER.info("Fin méthode : getMembre");
		return lMembreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO setMembre(final MembreDO pMemberDO) throws DaoException {
		
		lLOGGER.info("Début méthode : setMembre");
		
		// si le user ou le pwd sont null on va lever une Exception 
		if (pMemberDO == null) {
			throw new DaoException("Modification échoué : Le membre n'est pas connu.");
		}
		
		try {
	        HibernateSessionFactory.getSession().merge(pMemberDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete setMembre().");
			throw new DaoException("Connexion échoué : Impossible de mettre à jour le membre");
		}
		
		lLOGGER.info("Fin méthode : setMembre");
		return pMemberDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public int addMembre(final String pUsername, final String pPassword, final boolean pStatus, final ClientDO pClientDO,
			final RoleDO pRoleDO) throws DaoException {
		
		//ON initialise le LOGGER
		lLOGGER.info("Début méthode : addMembre");
		
		// si oui, on leve une exception
		final MembreDO lMembreDO = findMembreByNameAndPass(pUsername, pPassword);
		
		MembreDO lMembreDOadd =  null;
		
		int lIdMembre = 0;
		if (lMembreDO != null) {
			
			throw new DaoException("Création échoué : Le Membre existe déjà.");
		} else {
			// si non, on crée le membre et on recupère son identifiant
			if (pUsername == null || pPassword == null) {
				throw new DaoException("Le login et mot de passe sont obligatoires.Les valeurs NULL sont interdites.");
			} else {
				// on instancie le membre
				lMembreDOadd = new MembreDO();
				lMembreDOadd.setUsername(pUsername);
				lMembreDOadd.setPassword(pPassword);
				lMembreDOadd.setStatus(pStatus);
				lMembreDOadd.setClient(pClientDO);
				lMembreDOadd.setRoleMembre(pRoleDO);
				
				try {
					// la méthode save permet de créer le membre dans la table
					lIdMembre = (int) HibernateSessionFactory.getSession().save(lMembreDOadd);
										
				} catch (HibernateException ex) {
					
					// Critical errors : database unreachable, etc.
					lLOGGER.error("Exception - DataAccessException occurs : " 
							+ ex.getMessage() + " on complete addMembre().");
					throw new DaoException("Connexion échoué : Impossible de créer le membre");
				}				
			}
		}
		lLOGGER.info("Fin méthode : addMembre");
		return lIdMembre;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void updateMembre(final int pIdMembre, final String pUsername, final String pPassword, final boolean pStatus, 
			final ClientDO pClientDO, final RoleDO pRoleDO) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : updateMembre");
		
		//On recupere le membre via son Id
		final MembreDO lMembreDO = findMembreById(pIdMembre);
		
		// si le membre n'existe pas, on leve une exception
		if (lMembreDO == null) {
			
			throw new DaoException("Modification échoué : Le Membre n'est pas connu.");
		} else {
			// on modifie le membre
			lMembreDO.setUsername(pUsername);
			lMembreDO.setPassword(pPassword);
			lMembreDO.setStatus(pStatus);
			lMembreDO.setRoleMembre(pRoleDO);
			lMembreDO.setClient(pClientDO);
			
			try {
				// la méthode update permet de modifier le membre dans la table
				HibernateSessionFactory.getSession().update(lMembreDO);
			} catch (HibernateException ex) {
				
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete updateMembre().");
				throw new DaoException("connexion échoué : Impossible de mettre à jour le membre");
			}
		}
		lLOGGER.info("Fin méthode : updateMembre");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteMembre(final int pIdMembre) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : deleteMembre");
		
		//On recupere le membre via son Id
		final MembreDO lMembreDO = findMembreById(pIdMembre);
		
		// si le membre n'existe pas, on leve une exception
		if (lMembreDO == null) {
			
			throw new DaoException("Modification échoué : Le Membre n'est pas connu.");
		} else {			
			
			try {
				// la méthode delete permet de supprimer le membre dans la table
				HibernateSessionFactory.getSession().delete(lMembreDO);
			} catch (HibernateException ex) {
				
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete deleteMembre().");
				throw new DaoException("Connexion échoué : Impossible de supprimer le membre");
			}
		}
		lLOGGER.info("Fin méthode : deleteMembre");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	@SuppressWarnings("unchecked")
	public List<MembreDO> listerMembres() throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : listerMembres");
		
		List<MembreDO> lListeMembres = new ArrayList<MembreDO>();
		
		try {
			// La méthode CreateCriteria permet de créer une instance de la classe MembreDO
			Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(MembreDO.class);
			// on recupère la lise des objects de type membreDO
			lListeMembres = lCriteria.list();
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerMembres().");
			throw new DaoException("Connexion échoué : Impossible de récupérer la liste des membres");
		}
		
		lLOGGER.info("Fin méthode : listerMembres");
		
		return lListeMembres;
	}
	

}
	