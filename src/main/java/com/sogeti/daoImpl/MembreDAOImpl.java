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
		
		lLOGGER.info("D�but m�thode : AuthentifierMembre");
		// on v�rifie si le membre existe dans la table
		final MembreDO lMembreDO = findMembreByNameAndPass(pUsername, pPassword);
		// si le membre n'existe pas dans la table
		if (lMembreDO == null)
		{
			throw new DaoException("Login / mdp invalide ou user d�sactiv�");
		}
		
		lLOGGER.info("Fin m�thode : AuthentifierMembre");
		return lMembreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO findMembreById(final int pIdMembre) throws DaoException {
		
		lLOGGER.info("D�but m�thode : getMembre");
		
		MembreDO lMembreDO = null;
		
		if (pIdMembre == 0) {
			throw new DaoException("L'identifiant est obligatoire. Valeur z�ro est interdit.");
		} else {

			try {
				lLOGGER.info("On recup�re l'utilisateur � partie de son Id: " + pIdMembre);
				// create a new criteria
				final Criteria lCrit = HibernateSessionFactory.getSession().createCriteria(MembreDO.class);
				lCrit.add(Restrictions.eq("idMembre", pIdMembre));
				lMembreDO = (MembreDO) lCrit.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete getMembre().");
				throw new DaoException("Connexion �chou� : Identifiant inconnu");
			}
		}
		lLOGGER.info("Fin m�thode : getMembre");
		return lMembreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO findMembreByNameAndPass(final String pUsername, final String pPassword) throws DaoException {
		
		//Initialisation de LOGGER
		lLOGGER.info("D�but m�thode : getMembre");
		
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
			throw new DaoException("Connexion �chou� : USER/MDP incorrect");
		}
		
		lLOGGER.info("Fin m�thode : getMembre");
		return lMembreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO setMembre(final MembreDO pMemberDO) throws DaoException {
		
		lLOGGER.info("D�but m�thode : setMembre");
		
		// si le user ou le pwd sont null on va lever une Exception 
		if (pMemberDO == null) {
			throw new DaoException("Modification �chou� : Le membre n'est pas connu.");
		}
		
		try {
	        HibernateSessionFactory.getSession().merge(pMemberDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete setMembre().");
			throw new DaoException("Connexion �chou� : Impossible de mettre � jour le membre");
		}
		
		lLOGGER.info("Fin m�thode : setMembre");
		return pMemberDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public int addMembre(final String pUsername, final String pPassword, final boolean pStatus, final ClientDO pClientDO,
			final RoleDO pRoleDO) throws DaoException {
		
		//ON initialise le LOGGER
		lLOGGER.info("D�but m�thode : addMembre");
		
		// si oui, on leve une exception
		final MembreDO lMembreDO = findMembreByNameAndPass(pUsername, pPassword);
		
		MembreDO lMembreDOadd =  null;
		
		int lIdMembre = 0;
		if (lMembreDO != null) {
			
			throw new DaoException("Cr�ation �chou� : Le Membre existe d�j�.");
		} else {
			// si non, on cr�e le membre et on recup�re son identifiant
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
					// la m�thode save permet de cr�er le membre dans la table
					lIdMembre = (int) HibernateSessionFactory.getSession().save(lMembreDOadd);
										
				} catch (HibernateException ex) {
					
					// Critical errors : database unreachable, etc.
					lLOGGER.error("Exception - DataAccessException occurs : " 
							+ ex.getMessage() + " on complete addMembre().");
					throw new DaoException("Connexion �chou� : Impossible de cr�er le membre");
				}				
			}
		}
		lLOGGER.info("Fin m�thode : addMembre");
		return lIdMembre;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void updateMembre(final int pIdMembre, final String pUsername, final String pPassword, final boolean pStatus, 
			final ClientDO pClientDO, final RoleDO pRoleDO) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("D�but m�thode : updateMembre");
		
		//On recupere le membre via son Id
		final MembreDO lMembreDO = findMembreById(pIdMembre);
		
		// si le membre n'existe pas, on leve une exception
		if (lMembreDO == null) {
			
			throw new DaoException("Modification �chou� : Le Membre n'est pas connu.");
		} else {
			// on modifie le membre
			lMembreDO.setUsername(pUsername);
			lMembreDO.setPassword(pPassword);
			lMembreDO.setStatus(pStatus);
			lMembreDO.setRoleMembre(pRoleDO);
			lMembreDO.setClient(pClientDO);
			
			try {
				// la m�thode update permet de modifier le membre dans la table
				HibernateSessionFactory.getSession().update(lMembreDO);
			} catch (HibernateException ex) {
				
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete updateMembre().");
				throw new DaoException("connexion �chou� : Impossible de mettre � jour le membre");
			}
		}
		lLOGGER.info("Fin m�thode : updateMembre");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteMembre(final int pIdMembre) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("D�but m�thode : deleteMembre");
		
		//On recupere le membre via son Id
		final MembreDO lMembreDO = findMembreById(pIdMembre);
		
		// si le membre n'existe pas, on leve une exception
		if (lMembreDO == null) {
			
			throw new DaoException("Modification �chou� : Le Membre n'est pas connu.");
		} else {			
			
			try {
				// la m�thode delete permet de supprimer le membre dans la table
				HibernateSessionFactory.getSession().delete(lMembreDO);
			} catch (HibernateException ex) {
				
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete deleteMembre().");
				throw new DaoException("Connexion �chou� : Impossible de supprimer le membre");
			}
		}
		lLOGGER.info("Fin m�thode : deleteMembre");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	@SuppressWarnings("unchecked")
	public List<MembreDO> listerMembres() throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("D�but m�thode : listerMembres");
		
		List<MembreDO> lListeMembres = new ArrayList<MembreDO>();
		
		try {
			// La m�thode CreateCriteria permet de cr�er une instance de la classe MembreDO
			Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(MembreDO.class);
			// on recup�re la lise des objects de type membreDO
			lListeMembres = lCriteria.list();
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerMembres().");
			throw new DaoException("Connexion �chou� : Impossible de r�cup�rer la liste des membres");
		}
		
		lLOGGER.info("Fin m�thode : listerMembres");
		
		return lListeMembres;
	}
	

}
	