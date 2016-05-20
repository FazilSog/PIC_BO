package com.sogeti.daoImpl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IRoleProjetDAO;
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
	private Logger lLOGGER = Logger.getLogger(ClientDAOImpl.class);
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void addRoleProjet(final RoleProjetDO pRoleProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : addRoleProjet");
		 try {
			
			 //On ajoute le projet
			 HibernateSessionFactory.getSession().save(pRoleProjetDO);
			 
		 } catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion échoué : Identifiant inconnu");
			}
		 lLOGGER.info("Fin méthode : addRoleProjet");
		}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void updateRoleProjet(final RoleProjetDO pRoleProjet) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : updateRoleProjet");
		
		try {
				HibernateSessionFactory.getSession().update(pRoleProjet);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion échoué : Identifiant inconnu");
		}
	 lLOGGER.info("Fin méthode : updateRoleProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleProjetDO findRoleProjetByID (final int pIdRoleProjet) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : findClientById");
		
		RoleProjetDO lRoleProjet = null;
				
		try {
			//On verifie l'egalité du role recu avec clui qu'on n'a en base
			final Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(RoleProjetDO.class);
			lCriteria.add(Restrictions.eq("idRoleProjet",pIdRoleProjet));
			lRoleProjet = (RoleProjetDO) lCriteria.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion échoué : Identifiant inconnu");
		}
		lLOGGER.info("Fin méthode : findClientById");
		
		if (lRoleProjet != null)
		{
			throw new DaoException("Connexion échoué : Le role projet n'est pas connu!");
		}
		
		return lRoleProjet;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleProjetDO findRoleProjetByIdProjet (final int pIdProjet) throws DaoException
	{
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : findRoleProjetByIdProjet");
		
		RoleProjetDO lRoleProjet = null;
				
		try {
			//On verifie l'egalité du role recu avec clui qu'on n'a en base
			final Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(RoleProjetDO.class);
			lCriteria.add(Restrictions.eq("idprojet", pIdProjet));
			lRoleProjet = (RoleProjetDO) lCriteria.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion échoué : Identifiant inconnu");
		}
		lLOGGER.info("Fin méthode : findRoleProjetByIdProjet");
		
		if (lRoleProjet != null)
		{
			throw new DaoException("Connexion échoué : Le role projet n'est pas connu!");
		}
		return lRoleProjet;
	}
	
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteRoleProjet(final RoleProjetDO pRoleProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : deleteRoleProjet");
				
		try {
			//On recupere la session est on supprime l'objet RoleProjetDO
			HibernateSessionFactory.getSession().delete(pRoleProjetDO);
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion échoué : Identifiant inconnu");
		}
	 lLOGGER.info("Fin méthode : deleteRoleProjet");
	}
}