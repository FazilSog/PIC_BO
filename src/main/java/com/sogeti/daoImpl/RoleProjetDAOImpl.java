package com.sogeti.daoImpl;

import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IRoleProjetDAO;
import com.sogeti.dao.model.MembreDO;
import com.sogeti.dao.model.ProjetDO;
import com.sogeti.dao.model.RoleDO;
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
	public void addRoleProjet(final MembreDO pMembreDO, final RoleDO pRoleDO, final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : findClientById");
		 try {
			//on affecte a l'objet l'idRole, idProjet, idMembre
			 RoleProjetDO lRoleProjetDO = new RoleProjetDO();
			 lRoleProjetDO.setRole(pRoleDO);
			 lRoleProjetDO.setProjet(pProjetDO);
			 lRoleProjetDO.setMembre(pMembreDO);
			 //On ajoute le projet
			 HibernateSessionFactory.getSession().save(lRoleProjetDO);
			 
		 } catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				lLOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion échoué : Identifiant inconnu");
			}
		 lLOGGER.info("Fin méthode : findClientById");
		}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void updateRoleProjet(final MembreDO pMembreDO, final RoleDO pRoleDO, final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : findClientById");
		
		try {
			//On recuepre la liste RoleProjet 
			Set<RoleProjetDO> lRoleProjetSet = pProjetDO.getRoleProjet();
			
			int lIdRoleProjet = 0;
			
			//TODO ajouter un teste pour ne pas boucler n fois
			for (RoleProjetDO lRoleProjetDO : lRoleProjetSet) {
				lIdRoleProjet = lRoleProjetDO.getIdRoleProjet();
			}
			RoleProjetDO lRoleProjetDO = findRoleProjetByID(lIdRoleProjet);
			if (lRoleProjetDO != null) {
				lRoleProjetDO.setRole(pRoleDO);
				lRoleProjetDO.setProjet(pProjetDO);
				lRoleProjetDO.setMembre(pMembreDO);
				
				HibernateSessionFactory.getSession().update(lRoleProjetDO);
			
			}
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion échoué : Identifiant inconnu");
		}
	 lLOGGER.info("Fin méthode : findClientById");
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
	 return lRoleProjet;
}
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteRoleProjet(final MembreDO pMembreDO, final RoleDO pRoleDO, final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		lLOGGER.info("Début méthode : deleteRoleProjet");
		
		try {
			//on affecte a l'objet l'idRole, idProjet, idMembre
			RoleProjetDO lRoleProjetDO = new RoleProjetDO();
			lRoleProjetDO.setRole(pRoleDO);
			lRoleProjetDO.setProjet(pProjetDO);
			lRoleProjetDO.setMembre(pMembreDO);
			
			//On recupere la session est on supprime l'objet RoleProjetDO
			HibernateSessionFactory.getSession().delete(lRoleProjetDO);
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			lLOGGER.error("Exception - DataAccessException occurs : " 
			+ ex.getMessage() + " on complete getProjet().");
			throw new DaoException("Connexion échoué : Identifiant inconnu");
		}
		lLOGGER.info("Fin méthode : deleteRoleProjet");
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