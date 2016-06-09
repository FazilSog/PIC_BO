package com.sogeti.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IRoleProjetDAO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericDAO;
import com.sogeti.model.MembreDO;
import com.sogeti.model.ProjetDO;
import com.sogeti.model.RoleProjetDO;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author syahiaou
 *
 */

@Service
@Transactional
public class RoleProjetDAOImpl extends GenericDAO<RoleProjetDO> implements IRoleProjetDAO {

	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(RoleProjetDAOImpl.class);
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void create(final RoleProjetDO pRoleProjetDO) throws DaoException {
		
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : addRoleProjet");
		 try {
			 //On ajoute le projet
			 HibernateSessionFactory.getSession().save(pRoleProjetDO);
			 
		 } catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete addRoleProjet().");
			// TODO v�rifier le message d'erreur
			throw new DaoException("Impossible d'ajouter le role projet");
			}
		 LOGGER.info("Fin m�thode : addRoleProjet");
		}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void update(final RoleProjetDO pRoleProjet) throws DaoException {
		
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : updateRoleProjet");
		
		try {
			HibernateSessionFactory.getSession().update(pRoleProjet);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete updateRoleProjet().");
			// TODO v�rifier le message d'erreur
			throw new DaoException("Impossible de faire un update sur un role projet");
		}
	 LOGGER.info("Fin m�thode : updateRoleProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleProjetDO find(final int pIdRoleProjet) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : findRoleProjetByID");
		
		RoleProjetDO roleProjet = null;
				
		try {
			//On verifie l'egalit� du role recu avec clui qu'on n'a en base
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleProjetDO.class);
			criteria.add(Restrictions.eq("idRoleProjet", pIdRoleProjet));
			roleProjet = (RoleProjetDO) criteria.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete findRoleProjetByID().");
			// TODO v�rifier le message d'erreur
			throw new DaoException("Impossible de trouver le role projet");
		}
		LOGGER.info("Fin m�thode : findRoleProjetByID");
		
		if (roleProjet == null) {
			throw new DaoException("Le role projet n'est pas connu!");
		}
		
		return roleProjet;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleProjetDO findRoleProjet(final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : findRoleProjet");
		
		RoleProjetDO roleProjet = null;
				
		try {
			//On verifie l'egalit� du role recu avec clui qu'on n'a en base
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleProjetDO.class);
			criteria.add(Restrictions.eq("projet", pProjetDO));
			roleProjet = (RoleProjetDO) criteria.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete findRoleProjet().");
			// TODO v�rifier le message d'erreur
			throw new DaoException("Impossible de retrouver le RoleProjet");
		}
		LOGGER.info("Fin m�thode : findRoleProjet");
		
		if (roleProjet == null) {
			throw new DaoException("Le role projet n'est pas connu !");
		}
		return roleProjet;
	}
	
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void delete(final RoleProjetDO pRoleProjetDO) throws DaoException {
		
		// On initialise le LOGGER
		LOGGER.info("D�but m�thode : deleteRoleProjet");
				
		try {
			//On recupere la session est on supprime l'objet RoleProjetDO
			HibernateSessionFactory.getSession().delete(pRoleProjetDO);
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete deleteRoleProjet().");
			// TODO v�rifier le message d'erreur
			throw new DaoException("Impossible de supprimer le RoleProjet");
		}
		LOGGER.info("Fin m�thode : deleteRoleProjet");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public RoleProjetDO findRoleProjetByProjetAndMembre(final ProjetDO pProjetDO, final MembreDO pMembreDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : findRoleProjetByProjetAndMembre");
		
		RoleProjetDO roleProjet = null;
				
		try {
			//On verifie l'egalit� du role recu avec clui qu'on n'a en base
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleProjetDO.class);
			criteria.add(Restrictions.eq("projet", pProjetDO));
			criteria.add(Restrictions.eq("membre", pMembreDO));
			
			roleProjet = (RoleProjetDO) criteria.uniqueResult();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete findRoleProjetByProjetAndMembre().");
			// TODO v�rifier le message d'erreur
			throw new DaoException("Impossible de retrouver le RoleProjet d'un membre sur un projet");
		}
		LOGGER.info("Fin m�thode : findRoleProjetByProjetAndMembre");
		
		if (roleProjet == null) {
			throw new DaoException("Le role projet n'est pas connu !");
		}
		return roleProjet;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<RoleProjetDO> listeObjects() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}