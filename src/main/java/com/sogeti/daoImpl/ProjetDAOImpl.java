package com.sogeti.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IProjetDAO;
import com.sogeti.dao.model.ClientDO;
import com.sogeti.dao.model.ProjetDO;
import com.sogeti.exception.DaoException;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author syahiaou
 *
 */

@Service
@Transactional
public class ProjetDAOImpl implements IProjetDAO {
	
	// Initialisation du LOGGER
	private static final Logger LOGGER =Logger.getLogger(ProjetDAOImpl.class);

	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	private ProjetDO findProjet(final String pNomProjet, final String pUrl, final String pBranche) throws DaoException {
		
		LOGGER.info("Début méthode : findProjet");
		
		ProjetDO projetDO = null;
		
		if (pNomProjet == null || pUrl == null || pBranche == null) {
			throw new DaoException(" Valeur vide et/ou zéro est interdit.");
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
				throw new DaoException("Connexion échoué : Projet inconnu");
			}
		}
		LOGGER.info("Fin méthode : findProjet");
		
		return projetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO findProjetById(int pIdProjet) throws DaoException {
		
		//initialaisation du logger
		LOGGER.info("Début méthode : findProjetById");
		
		ProjetDO projetDO = null;
		
		if (pIdProjet == 0) {
			throw new DaoException("L'identifiant est obligatoire. Valeur zéro est interdit.");
		} else {
			try {
				LOGGER.info("On recupére le projet à partie de son Id: " + pIdProjet);
				
				// create a new criteria
				final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
				criteria.add(Restrictions.eq("idProjet", pIdProjet));
				projetDO = (ProjetDO) criteria.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete findProjetById().");
				throw new DaoException("Connexion échoué : Identifiant inconnu");
			}
		}
		// on vérifie si le projetDO existe ou pas.
		if (projetDO == null) {
			throw new DaoException("Le projet n'existe pas.");
		} 
		LOGGER.info("Fin méthode : findProjetById");
		return projetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO addProjet(final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("Début méthode : addProjet");
		
		//On instancie l'objet PrjetDO
		final ProjetDO projetDO = pProjetDO;
		
		int idProjet = 0;
		
		// on vérifie si le projet existe
		final ProjetDO projetDOExiste = findProjet(pProjetDO.getNomProjet(), pProjetDO.getUrl(), pProjetDO.getBranche());
		
		if (projetDOExiste != null) {
			
			throw new DaoException("Création échoué : Le projet existe déjà.");
		} else {
			
			try {
				idProjet = (int) HibernateSessionFactory.getSession().save(pProjetDO);
				//On recupere l'Id du projet
				projetDO.setIdProjet(idProjet);
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete addProjet().");
				throw new DaoException("Connexion échoué : Impossible de créer le projet");

			}
			LOGGER.info("Fin méthode : addProjet");
			return projetDO;
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void updateProjet(final ProjetDO pProjetDO) throws DaoException{
		
		//On initialise le LOGGER
		LOGGER.info("Début méthode : updateProjet");
			
		try {
			//On met a jour le projet
			HibernateSessionFactory.getSession().update(pProjetDO);
		
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete updateProjet().");
			throw new DaoException("connexion échoué : Impossible de mettre à jour le projet");
		}
			
		LOGGER.info("Fin méthode : updateProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteProjet (final int pIdProjet) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("Début méthode : deleteProjet");
		
		//On recuepre le projet via son Id
		final ProjetDO projetDO = findProjetById(pIdProjet);

		try {
			// la méthode delete permet de supprimer le projet dans la table
			HibernateSessionFactory.getSession().delete(projetDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete deleteMembre().");
			throw new DaoException("Connexion échoué : Impossible de supprimer le projet");
		}
		
		LOGGER.info("Fin méthode : deleteProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteProjet (final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("Début méthode : deleteProjet");
		
		try {
			// la méthode delete permet de supprimer le projet dans la table
			HibernateSessionFactory.getSession().delete(pProjetDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete deleteMembre().");
			throw new DaoException("Connexion échoué : Impossible de supprimer le projet");
		}
		
		LOGGER.info("Fin méthode : deleteProjet");
	}
	

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	@SuppressWarnings("unchecked")
	public List<ProjetDO> listerProjets(final ClientDO pClientDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("Début méthode : listerProjets");
		
		//ON recupere la liste des projets
		List<ProjetDO> lListeProjet = new ArrayList<ProjetDO>();
		try {
			// La méthode CreateCriteria permet de créer une instance de la classe projetDO
			final Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class)
					.add(Restrictions.eq("client", pClientDO));
			// on recupère la lise des objects de type projetDO
			lListeProjet = lCriteria.list();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerProjets().");
			throw new DaoException("Connexion échoué : Impossible de récupérer la liste des projets");
		}
		
		LOGGER.info("Fin méthode : listerProjets");
		return lListeProjet;
	}
		
}
