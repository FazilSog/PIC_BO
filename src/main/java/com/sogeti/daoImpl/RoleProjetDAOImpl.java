package com.sogeti.daoImpl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IRoleProjetDAO;
import com.sogeti.dao.model.ProjetDO;
import com.sogeti.dao.model.RoleProjetDO;
import com.sogeti.exception.DaoException;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author syahiaou
 *
 */

@Service
@Transactional
public class RoleProjetDAOImpl implements IRoleProjetDAO {

	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(RoleProjetDAOImpl.class);
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void addRoleProjet(final RoleProjetDO pRoleProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : addRoleProjet");
		 try {
			
			 //On ajoute le projet
			 HibernateSessionFactory.getSession().save(pRoleProjetDO);
			 
		 } catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion �chou� : Identifiant inconnu");
			}
		 LOGGER.info("Fin m�thode : addRoleProjet");
		}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void updateRoleProjet(final RoleProjetDO pRoleProjet) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : updateRoleProjet");
		
		try {
			HibernateSessionFactory.getSession().update(pRoleProjet);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion �chou� : Identifiant inconnu");
		}
	 LOGGER.info("Fin m�thode : updateRoleProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleProjetDO findRoleProjetByID (final int pIdRoleProjet) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : findClientById");
		
		RoleProjetDO roleProjet = null;
				
		try {
			//On verifie l'egalit� du role recu avec clui qu'on n'a en base
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleProjetDO.class);
			criteria.add(Restrictions.eq("idRoleProjet",pIdRoleProjet));
			roleProjet = (RoleProjetDO) criteria.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion �chou� : Identifiant inconnu");
		}
		LOGGER.info("Fin m�thode : findClientById");
		
		if (roleProjet != null)
		{
			throw new DaoException("Connexion �chou� : Le role projet n'est pas connu!");
		}
		
		return roleProjet;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleProjetDO findRoleProjet (final ProjetDO pProjetDO) throws DaoException {
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : findRoleProjetByIdProjet");
		
		RoleProjetDO roleProjet = null;
				
		try {
			//On verifie l'egalit� du role recu avec clui qu'on n'a en base
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleProjetDO.class);
			criteria.add(Restrictions.eq("projet", pProjetDO));
			roleProjet = (RoleProjetDO) criteria.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion �chou� : Identifiant inconnu");
		}
		LOGGER.info("Fin m�thode : findRoleProjetByIdProjet");
		
		if (roleProjet == null)
		{
			throw new DaoException("Le role projet n'est pas connu !");
		}
		return roleProjet;
	}
	
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteRoleProjet(final RoleProjetDO pRoleProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : deleteRoleProjet");
				
		try {
			//On recupere la session est on supprime l'objet RoleProjetDO
			HibernateSessionFactory.getSession().delete(pRoleProjetDO);
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion �chou� : Identifiant inconnu");
		}
		LOGGER.info("Fin m�thode : deleteRoleProjet");
	}
}