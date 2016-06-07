package com.sogeti.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IRoleDAO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericDAO;
import com.sogeti.model.RoleDO;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author syahiaou
 *
 */

@Service
@Transactional
public class RoleDAOImpl extends GenericDAO<RoleDO> implements IRoleDAO {
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(RoleDAOImpl.class);
			
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleDO find(final int pIdRole) throws DaoException {
		
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : findRoleById");
		
		RoleDO roleDO = null;
		
		if (pIdRole == 0) {
			throw new DaoException("idRole obligatoire.La valeur NULL est interdite.");
		} else {
			try {
				//on cree la session 
				final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleDO.class);				
				//On verifie l'egalit� du role recu avec clui qu'on n'a en base
				criteria.add(Restrictions.eq("idRole", pIdRole));
				
				roleDO = (RoleDO) criteria.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete findRoleById().");
				throw new DaoException("Connexion �chou� : l'id role n'est pas connu!");
			}
		}
		
		if (roleDO == null) {
			throw new DaoException("Le role n'est pas connu!");
		}
		
		LOGGER.info("Fin m�thode : findRoleById");
		return roleDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleDO findRoleByCodeRole(final String pCodeRole) throws DaoException {
		
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : findRoleByCodeRole");
		
		RoleDO roleDO = null;
		
		if (pCodeRole == null) {
			throw new DaoException("idRole obligatoire.La valeur NULL est interdite.");
		} else {
			try {
				//On verifie l'egalit� du role recu avec clui qu'on n'a en base
				final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleDO.class);
				criteria.add(Restrictions.eq("codeRole", pCodeRole));
				
				roleDO = (RoleDO) criteria.uniqueResult();
				 
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete findRoleByCodeRole().");
				// TODO
				throw new DaoException("Connexion �chou� : XXXX");
			}
		}
		
		LOGGER.info("Fin m�thode : findRoleByCodeRole");
		return roleDO;
			
	}
	

	/**
	 * {@inheritDoc}
	 */
	public void create(RoleDO pObject) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(RoleDO pObject) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(RoleDO pObject) throws DaoException {
		// TODO Auto-generated method stub
		
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<RoleDO> listeObjects() throws DaoException {
		
		//On initialise le logger
		LOGGER.info("D�but m�thode : listerRoles");
		
		List<RoleDO> lListeRoles = new ArrayList<RoleDO>();
		
		try {
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleDO.class);
			
			// permet de r�cupere la liste
			lListeRoles = criteria.list();
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete listerRoles().");
			// TODO
			throw new DaoException("Connexion �chou� : XXXXXXX");
		}
		
		LOGGER.info("Fin m�thode : listerRoles");
		return lListeRoles;
	}
		
}
