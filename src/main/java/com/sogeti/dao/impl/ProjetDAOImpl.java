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

import com.sogeti.dao.IProjetDAO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericDAO;
import com.sogeti.model.ClientDO;
import com.sogeti.model.ProjetDO;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author syahiaou
 *
 */

@Service
@Transactional
public class ProjetDAOImpl extends GenericDAO<ProjetDO> implements IProjetDAO {
	
	// Initialisation du LOGGER
	private static final Logger LOGGER =Logger.getLogger(ProjetDAOImpl.class);

	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	private ProjetDO findProjet(final String pNomProjet, final String pUrl, final String pBranche) throws DaoException {
		
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.info("D�but m�thode : findProjet");
		
		ProjetDO projetDO = null;
		
		if (pNomProjet == null || pUrl == null || pBranche == null) {
			throw new DaoException(" Valeur vide et/ou z�ro est interdit.");
		} else {

			try {
				// create a new criteria
				final Criteria crit = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
				crit.add(Restrictions.eq("nomProjet", pNomProjet));
				crit.add(Restrictions.eq("url", pUrl));
				crit.add(Restrictions.eq("branche", pBranche));
				projetDO = (ProjetDO) crit.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete findProjet().");
				throw new DaoException("Connexion �chou� : Projet inconnu");
			}
		}
		LOGGER.info("Fin m�thode : findProjet");
		
		return projetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO find(int pIdProjet) throws DaoException {
		
		PropertyConfigurator.configure("log4j.properties");
		//initialaisation du logger
		LOGGER.info("D�but m�thode : findProjetById");
		
		ProjetDO projetDO = null;
		
		if (pIdProjet == 0) {
			throw new DaoException("L'identifiant est obligatoire. Valeur z�ro est interdit.");
		} else {
			try {
				LOGGER.info("On recup�re le projet � partie de son Id: " + pIdProjet);
				
				// create a new criteria
				final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
				criteria.add(Restrictions.eq("idProjet", pIdProjet));
				projetDO = (ProjetDO) criteria.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete findProjetById().");
				throw new DaoException("Connexion �chou� : Identifiant inconnu");
			}
		}
		// on v�rifie si le projetDO existe ou pas.
		if (projetDO == null) {
			throw new DaoException("Le projet n'existe pas.");
		} 
		LOGGER.info("Fin m�thode : findProjetById");
		return projetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void create(final ProjetDO pProjetDO) throws DaoException {
		
		PropertyConfigurator.configure("log4j.properties");
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : addProjet");
		
		// on v�rifie si le projet existe
		final ProjetDO projetDOExiste = findProjet(pProjetDO.getNomProjet(), pProjetDO.getUrl(), pProjetDO.getBranche());
		
		if (projetDOExiste != null) {
			
			throw new DaoException("Cr�ation �chou� : Le projet existe d�j�.");
		} else {
			
			try {
				// la m�thode save va cr�er le projet dans la table
				HibernateSessionFactory.getSession().save(pProjetDO);
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete addProjet().");
				throw new DaoException("Connexion �chou� : Impossible de cr�er le projet");

			}
			LOGGER.info("Fin m�thode : addProjet");
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void update(final ProjetDO pProjetDO) throws DaoException{
		
		PropertyConfigurator.configure("log4j.properties");
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : updateProjet");
			
		try {
			//On met a jour le projet
			HibernateSessionFactory.getSession().update(pProjetDO);
		
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete updateProjet().");
			throw new DaoException("connexion �chou� : Impossible de mettre � jour le projet");
		}
			
		LOGGER.info("Fin m�thode : updateProjet");
	}
		
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void delete (final ProjetDO pProjetDO) throws DaoException {
		
		PropertyConfigurator.configure("log4j.properties");
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : deleteProjet");
		
		try {
			// la m�thode delete permet de supprimer le projet dans la table
			HibernateSessionFactory.getSession().delete(pProjetDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete deleteMembre().");
			throw new DaoException("Connexion �chou� : Impossible de supprimer le projet");
		}
		
		LOGGER.info("Fin m�thode : deleteProjet");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProjetDO> listeObjects() throws DaoException {
		
		PropertyConfigurator.configure("log4j.properties");
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : listerProjets");
		
		//ON recupere la liste des projets
		List<ProjetDO> lListeProjet = new ArrayList<ProjetDO>();
		try {
			// La m�thode CreateCriteria permet de cr�er une instance de la classe projetDO
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
			
			// on recup�re la lise des objects de type projetDO
			lListeProjet = criteria.list();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerProjets().");
			throw new DaoException("Connexion �chou� : Impossible de r�cup�rer la liste des projets");
		}
		
		LOGGER.info("Fin m�thode : listerProjets");
		return lListeProjet;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ProjetDO> listeObjects(final ClientDO pClientDO) throws DaoException {
		
		PropertyConfigurator.configure("log4j.properties");
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : listerProjets");
		
		//ON recupere la liste des projets
		List<ProjetDO> lListeProjet = new ArrayList<ProjetDO>();
		try {
			// La m�thode CreateCriteria permet de cr�er une instance de la classe projetDO
			final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class)
					.add(Restrictions.eq("client", pClientDO));
			// on recup�re la lise des objects de type projetDO
			lListeProjet = criteria.list();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerProjets().");
			throw new DaoException("Connexion �chou� : Impossible de r�cup�rer la liste des projets");
		}
		
		LOGGER.info("Fin m�thode : listerProjets");
		return lListeProjet;
	}
		
}
