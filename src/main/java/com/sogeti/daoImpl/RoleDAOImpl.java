package com.sogeti.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IRoleDAO;
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
public class RoleDAOImpl implements IRoleDAO {
	
	// Initialisation du LOGGER
	private Logger lLOGGER = Logger.getLogger(RoleDAOImpl.class);
			
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleDO findRoleById(final int pIdRole) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : findRoleById");
		
		RoleDO roleDO = null;
		
		if (pIdRole == 0) {
			throw new DaoException("idRole obligatoire.La valeur NULL est interdite.");
		} else {
			try {
				//on cree la session 
				final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleDO.class);				
				//On verifie l'egalité du role recu avec clui qu'on n'a en base
				criteria.add(Restrictions.eq("idRole", pIdRole));
				
				roleDO = (RoleDO) criteria.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete getProjet().");
				// TODO
				throw new DaoException("Connexion échoué : XXXXXXX");
			}
		}
		
		lLOGGER.info("Fin méthode : findRoleById");
		return roleDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleDO findRoleByCodeRole(final String pCodeRole) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : findRoleByCodeRole");
		
		RoleDO roleDO = null;
		
		if (pCodeRole == null) {
			throw new DaoException("idRole obligatoire.La valeur NULL est interdite.");
		} else {
			try {
				//On verifie l'egalité du role recu avec clui qu'on n'a en base
				final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleDO.class);
				criteria.add(Restrictions.eq("codeRole", pCodeRole));
				
				roleDO = (RoleDO) criteria.uniqueResult();
				 
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete getProjet().");
				// TODO
				throw new DaoException("Connexion échoué : XXXX");
			}
		}
		
		lLOGGER.info("Fin méthode : findRoleByCodeRole");
		return roleDO;
			
	}
	
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	@SuppressWarnings("unchecked")
	public List<RoleDO> listerRoles() throws DaoException {
		
		lLOGGER.info("Début méthode : listerRoles");
		
		List<RoleDO> lListeRoles = new ArrayList<RoleDO>();
		
		try {
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(RoleDO.class);
			lListeRoles = criteria.list();
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete listerRoles().");
			// TODO
			throw new DaoException("Connexion échoué : XXXXXXX");
		}
		
		lLOGGER.info("Fin méthode : listerRoles");
		return lListeRoles;
	}
		
}
