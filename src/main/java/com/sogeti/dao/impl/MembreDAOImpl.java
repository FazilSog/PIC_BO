package com.sogeti.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IMembreDAO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericDAO;
import com.sogeti.model.ClientDO;
import com.sogeti.model.MembreDO;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author syahiaou
 *
 */

@Service
@Transactional
public class MembreDAOImpl extends GenericDAO<MembreDO> implements IMembreDAO {
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(MembreDAOImpl.class);

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */

	public MembreDO authentifierMembre(final String pUsername, final String pPassword) throws DaoException {
		
		//Logger
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : AuthentifierMembre");
		// on v�rifie si le membre existe dans la table
		final MembreDO membreDO = findMembreByNameAndPass(pUsername, pPassword);
		// si le membre n'existe pas dans la table
		if (membreDO == null)
		{
			throw new DaoException("Login / mdp invalide ou user d�sactiv�");
		}
		
		LOGGER.info("Fin m�thode : AuthentifierMembre");
		return membreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO find(final int pIdMembre) throws DaoException {
		
		//logger
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : findMembreById");
		
		MembreDO membreDO = null;
		
		if (pIdMembre == 0) {
			throw new DaoException("L'identifiant est obligatoire. Valeur z�ro est interdit.");
		} else {

			try {
				LOGGER.info("On recup�re l'utilisateur � partie de son Id: " + pIdMembre);
				// create a new criteria
				final Criteria crit = HibernateSessionFactory.getSession().createCriteria(MembreDO.class);
				crit.add(Restrictions.eq("idMembre", pIdMembre));
				membreDO = (MembreDO) crit.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete findMembreById().");
				throw new DaoException("Connexion �chou� : Identifiant inconnu");
			}
		}
		
		// si le membre n'est pas connu, on l�ve une exception
		if (membreDO == null) {
			
			throw new DaoException("Le Membre n'est pas connu.");
		}

		LOGGER.info("Fin m�thode : findMembreById");
		return membreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO findMembreByNameAndPass(final String pUsername, final String pPassword) throws DaoException {
		
		//Initialisation de LOGGER
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : findMembreByNameAndPass");
		
		
		MembreDO membreDO = null;
		
		// si le user ou le pwd sont null on va lever une Exception 
		if (pUsername == null || pPassword == null) {
			throw new DaoException("Le login et mot de passe sont obligatoires.Les valeurs NULL sont interdites.");
		}
		// on recupere le resulat unique du user et du pwd(s'ils sont egaux) 
		try {
			final Criteria crit = HibernateSessionFactory.getSession().createCriteria(MembreDO.class);
			crit.add(Restrictions.eq("username", pUsername));
			crit.add(Restrictions.eq("password", pPassword));
		
			// Retourne l'objet Membre
			membreDO =  (MembreDO) crit.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete findMembreByNameAndPass().");
			throw new DaoException("Connexion �chou� : USER/MDP incorrect");
		}
		
		LOGGER.info("Fin m�thode : findMembreByNameAndPass");
		return membreDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public MembreDO setMembre(final MembreDO pMemberDO) throws DaoException {
		
		//Initialisation du logger
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : setMembre");
		
		// si le user ou le pwd sont null on va lever une Exception 
		if (pMemberDO == null) {
			throw new DaoException("Modification �chou� : Le membre n'est pas connu.");
		}
		
		try {
	        HibernateSessionFactory.getSession().merge(pMemberDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete setMembre().");
			throw new DaoException("Connexion �chou� : Impossible de mettre � jour le membre");
		}
		
		LOGGER.info("Fin m�thode : setMembre");
		return pMemberDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void create(final MembreDO pMembreDO) throws DaoException {
		
		
		//ON initialise le LOGGER
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : addMembre");
		
		// si oui, on leve une exception
		final MembreDO membreDO = findMembreByNameAndPass(pMembreDO.getUsername(), pMembreDO.getPassword());
		
		// on test si le membreDO est diff�rent de null
		if (membreDO != null) {
			
			throw new DaoException("Cr�ation �chou� : Le Membre existe d�j�.");
		} else {
		
			try {
				// la m�thode save permet de cr�er le membre dans la table
				HibernateSessionFactory.getSession().save(pMembreDO);
									
			} catch (HibernateException ex) {
				
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete addMembre().");
				throw new DaoException("Connexion �chou� : Impossible de cr�er le membre");
			}				
		}
		LOGGER.info("Fin m�thode : addMembre");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void update(final MembreDO pMembreDO) throws DaoException {
		
		
		//On initialise le LOGGER
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : updateMembre");
		
		try {
			// la m�thode update permet de modifier le membre dans la table
			HibernateSessionFactory.getSession().update(pMembreDO);
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete updateMembre().");
			throw new DaoException("connexion �chou� : Impossible de mettre � jour le membre");
		}
			
		LOGGER.info("Fin m�thode : updateMembre");
	}
		
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void delete(final MembreDO pMembreDO) throws DaoException {
		
		
		//On initialise le LOGGER
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : deleteMembre");
			
		try {
			// la m�thode delete permet de supprimer le membre dans la table
			HibernateSessionFactory.getSession().delete(pMembreDO);
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete deleteMembre().");
			throw new DaoException("Connexion �chou� : Impossible de supprimer le membre");
		}
		
		LOGGER.info("Fin m�thode : deleteMembre");
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<MembreDO> listeObjects() throws DaoException {
		
		
		//On initialise le LOGGER
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : listerMembres");
		
		List<MembreDO> lListeMembres = new ArrayList<MembreDO>();
		
		try {
			// La m�thode CreateCriteria permet de cr�er une instance de la classe MembreDO
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(MembreDO.class);
			// on recup�re la lise des objects de type membreDO
			lListeMembres = criteria.list();
			
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerMembres().");
			throw new DaoException("Connexion �chou� : Impossible de r�cup�rer la liste des membres");
		}
		
		LOGGER.info("Fin m�thode : listerMembres");
		
		return lListeMembres;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<MembreDO> listeMembreByClient(final ClientDO pClientDO) throws DaoException {
		
		
		//On initialise le LOGGER
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : listerMembres");
		
		List<MembreDO> lListeMembres = new ArrayList<MembreDO>();
		
		try {
			// La m�thode CreateCriteria permet de cr�er une instance de la classe MembreDO
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(MembreDO.class)
					.add(Restrictions.eq("client", pClientDO));
			// on recup�re la lise des objects de type membreDO
			lListeMembres = criteria.list();
			
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerMembres().");
			throw new DaoException("Connexion �chou� : Impossible de r�cup�rer la liste des membres");
		}
		
		LOGGER.info("Fin m�thode : listerMembres");
		
		return lListeMembres;
	}
}
	