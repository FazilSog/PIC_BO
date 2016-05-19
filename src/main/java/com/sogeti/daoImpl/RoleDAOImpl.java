package com.sogeti.daoImpl;

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
 * @author moissa
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
		
		RoleDO lRoleDO = null;
		
		if (pIdRole == 0) {
			throw new DaoException("idRole obligatoire.La valeur NULL est interdite.");
		} else {
			try {
				//on cree la session 
				final Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(RoleDO.class);
				//On verifie l'egalité du role recu avec clui qu'on n'a en base
				lCriteria.add(Restrictions.eq("idRole",pIdRole));
				 lRoleDO = (RoleDO) lCriteria.uniqueResult();
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion échoué : Identifiant inconnu");
			}
		}
		lLOGGER.info("Fin méthode : findRoleById");
		return lRoleDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public RoleDO findRoleByCodeRole(String pCodeRole) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : findRoleByCodeRole");
		
		RoleDO lRoleDO = null;
		
		if (pCodeRole == null) {
			throw new DaoException("idRole obligatoire.La valeur NULL est interdite.");
		} else {
			try {
				//On verifie l'egalité du role recu avec clui qu'on n'a en base
				final Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(RoleDO.class);
				lCriteria.add(Restrictions.eq("codeRole",pCodeRole));
				 lRoleDO = (RoleDO) lCriteria.uniqueResult();
				 
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion échoué : Identifiant inconnu");
			}
		}
		lLOGGER.info("Fin méthode : findRoleByCodeRole");
		return lRoleDO;
			
	}
		
}
