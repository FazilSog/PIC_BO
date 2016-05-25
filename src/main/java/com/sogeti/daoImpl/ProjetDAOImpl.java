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
	private static final Logger aLOGGER =Logger.getLogger(ProjetDAOImpl.class);

	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	private ProjetDO findProjet(final String pNomProjet, final String pUrl, final String pBranche) throws DaoException {
		
		aLOGGER.info("Début méthode : getMembre");
		
		ProjetDO ProjetDO = null;
		
		if (pNomProjet == null || pUrl == null || pBranche == null) {
			throw new DaoException(" Valeur vide et/ou zéro est interdit.");
		} else {

			try {
				// create a new criteria
				final Criteria crit = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
				crit.add(Restrictions.eq("nomProjet", pNomProjet));
				crit.add(Restrictions.eq("url", pUrl));
				crit.add(Restrictions.eq("branche", pBranche));
				ProjetDO = (ProjetDO) crit.uniqueResult();
				
			} catch (HibernateException e) {
				// Critical errors : database unreachable, etc.
				aLOGGER.error("Exception - DataAccessException occurs : " + e.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion échoué : Projet inconnu");
			}
		}
		aLOGGER.info("Fin méthode : getProjet");
		
		return ProjetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO findProjetById(int pIdProjet) throws DaoException {
		
		//initialaisation du logger
		aLOGGER.info("Début méthode : getProjet");
		
		ProjetDO projetDO = null;
		
		if (pIdProjet == 0) {
			throw new DaoException("L'identifiant est obligatoire. Valeur zéro est interdit.");
		} else {
			try {
				aLOGGER.info("On recupére le projet à partie de son Id: " + pIdProjet);
				
				// create a new criteria
				final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
				criteria.add(Restrictions.eq("idProjet", pIdProjet));
				projetDO = (ProjetDO) criteria.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				aLOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion échoué : Identifiant inconnu");
			}
		}
		// on vérifie si le projetDO existe ou pas.
		if (projetDO == null) {
			throw new DaoException("Le projet n'existe pas.");
		} 
		aLOGGER.info("Fin méthode : getMembre");
		return projetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO addProjet(final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		aLOGGER.info("Début méthode : addProjet");
		
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
				aLOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete addProjet().");
				throw new DaoException("Connexion échoué : Impossible de créer le projet");

			}
			aLOGGER.info("Fin méthode : addProjet");
			return projetDO;
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void updateProjet(final ProjetDO pProjetDO) throws DaoException{
		
		//On initialise le LOGGER
		aLOGGER.info("Début méthode : updateProjet");
			
		try {
			//On met a jour le projet
			HibernateSessionFactory.getSession().update(pProjetDO);
		
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			aLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete updateProjet().");
			throw new DaoException("connexion échoué : Impossible de mettre à jour le projet");
		}
			
		aLOGGER.info("Fin méthode : updateProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteProjet (final int pIdProjet) throws DaoException {
		
		//On initialise le LOGGER
		aLOGGER.info("Début méthode : deleteMembre");
		
		//On recuepre le projet via son Id
		final ProjetDO projetDO = findProjetById(pIdProjet);

		try {
			// la méthode delete permet de supprimer le projet dans la table
			HibernateSessionFactory.getSession().delete(projetDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			aLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete deleteMembre().");
			throw new DaoException("Connexion échoué : Impossible de supprimer le projet");
		}
		
		aLOGGER.info("Fin méthode : deleteProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteProjet (final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		aLOGGER.info("Début méthode : deleteMembre");
		
		try {
			// la méthode delete permet de supprimer le projet dans la table
			HibernateSessionFactory.getSession().delete(pProjetDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			aLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete deleteMembre().");
			throw new DaoException("Connexion échoué : Impossible de supprimer le projet");
		}
		
		aLOGGER.info("Fin méthode : deleteProjet");
	}
	

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	@SuppressWarnings("unchecked")
	public List<ProjetDO> listerProjets(final ClientDO pClientDO) throws DaoException {
		
		//On initialise le LOGGER
		aLOGGER.info("Début méthode : listerProjet");
		
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
			aLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerMembres().");
			throw new DaoException("Connexion échoué : Impossible de récupérer la liste des projets");
		}
		
		aLOGGER.info("Fin méthode : listerProjet");
		return lListeProjet;
	}
		
}
