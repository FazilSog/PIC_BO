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
		
		aLOGGER.info("D�but m�thode : getMembre");
		
		ProjetDO ProjetDO = null;
		
		if (pNomProjet == null || pUrl == null || pBranche == null) {
			throw new DaoException(" Valeur vide et/ou z�ro est interdit.");
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
				throw new DaoException("Connexion �chou� : Projet inconnu");
			}
		}
		aLOGGER.info("Fin m�thode : getProjet");
		
		return ProjetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO findProjetById(int pIdProjet) throws DaoException {
		
		//initialaisation du logger
		aLOGGER.info("D�but m�thode : getProjet");
		
		ProjetDO projetDO = null;
		
		if (pIdProjet == 0) {
			throw new DaoException("L'identifiant est obligatoire. Valeur z�ro est interdit.");
		} else {
			try {
				aLOGGER.info("On recup�re le projet � partie de son Id: " + pIdProjet);
				
				// create a new criteria
				final Criteria criteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
				criteria.add(Restrictions.eq("idProjet", pIdProjet));
				projetDO = (ProjetDO) criteria.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				aLOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion �chou� : Identifiant inconnu");
			}
		}
		// on v�rifie si le projetDO existe ou pas.
		if (projetDO == null) {
			throw new DaoException("Le projet n'existe pas.");
		} 
		aLOGGER.info("Fin m�thode : getMembre");
		return projetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO addProjet(final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		aLOGGER.info("D�but m�thode : addProjet");
		
		//On instancie l'objet PrjetDO
		final ProjetDO projetDO = pProjetDO;
		
		int idProjet = 0;
		
		// on v�rifie si le projet existe
		final ProjetDO projetDOExiste = findProjet(pProjetDO.getNomProjet(), pProjetDO.getUrl(), pProjetDO.getBranche());
		
		if (projetDOExiste != null) {
			
			throw new DaoException("Cr�ation �chou� : Le projet existe d�j�.");
		} else {
			
			try {
				idProjet = (int) HibernateSessionFactory.getSession().save(pProjetDO);
				//On recupere l'Id du projet
				projetDO.setIdProjet(idProjet);
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				aLOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete addProjet().");
				throw new DaoException("Connexion �chou� : Impossible de cr�er le projet");

			}
			aLOGGER.info("Fin m�thode : addProjet");
			return projetDO;
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void updateProjet(final ProjetDO pProjetDO) throws DaoException{
		
		//On initialise le LOGGER
		aLOGGER.info("D�but m�thode : updateProjet");
			
		try {
			//On met a jour le projet
			HibernateSessionFactory.getSession().update(pProjetDO);
		
		} catch (HibernateException ex) {
			
			// Critical errors : database unreachable, etc.
			aLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete updateProjet().");
			throw new DaoException("connexion �chou� : Impossible de mettre � jour le projet");
		}
			
		aLOGGER.info("Fin m�thode : updateProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteProjet (final int pIdProjet) throws DaoException {
		
		//On initialise le LOGGER
		aLOGGER.info("D�but m�thode : deleteMembre");
		
		//On recuepre le projet via son Id
		final ProjetDO projetDO = findProjetById(pIdProjet);

		try {
			// la m�thode delete permet de supprimer le projet dans la table
			HibernateSessionFactory.getSession().delete(projetDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			aLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete deleteMembre().");
			throw new DaoException("Connexion �chou� : Impossible de supprimer le projet");
		}
		
		aLOGGER.info("Fin m�thode : deleteProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteProjet (final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		aLOGGER.info("D�but m�thode : deleteMembre");
		
		try {
			// la m�thode delete permet de supprimer le projet dans la table
			HibernateSessionFactory.getSession().delete(pProjetDO);
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			aLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete deleteMembre().");
			throw new DaoException("Connexion �chou� : Impossible de supprimer le projet");
		}
		
		aLOGGER.info("Fin m�thode : deleteProjet");
	}
	

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	@SuppressWarnings("unchecked")
	public List<ProjetDO> listerProjets(final ClientDO pClientDO) throws DaoException {
		
		//On initialise le LOGGER
		aLOGGER.info("D�but m�thode : listerProjet");
		
		//ON recupere la liste des projets
		List<ProjetDO> lListeProjet = new ArrayList<ProjetDO>();
		try {
			// La m�thode CreateCriteria permet de cr�er une instance de la classe projetDO
			final Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class)
					.add(Restrictions.eq("client", pClientDO));
			// on recup�re la lise des objects de type projetDO
			lListeProjet = lCriteria.list();
			
		} catch (HibernateException ex) {
			// Critical errors : database unreachable, etc.
			aLOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerMembres().");
			throw new DaoException("Connexion �chou� : Impossible de r�cup�rer la liste des projets");
		}
		
		aLOGGER.info("Fin m�thode : listerProjet");
		return lListeProjet;
	}
		
}
