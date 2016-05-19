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
import com.sogeti.dao.model.MembreDO;
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
		Logger LOGGER =Logger.getLogger(MembreDAOImpl.class);

	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	private ProjetDO getProjet(String nomProjet, String url, String branche) throws DaoException {
		
		LOGGER.info("D�but m�thode : getMembre");
		
		ProjetDO ProjetDO = null;
		
		if (nomProjet == null || url == null || branche == null) {
			throw new DaoException(" Valeur z�ro est interdit.");
		} else {

			try {
				// create a new criteria
				Criteria crit = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
				crit.add(Restrictions.eq("nomProjet", nomProjet));
				crit.add(Restrictions.eq("url", url));
				crit.add(Restrictions.eq("branche", branche));
				ProjetDO = (ProjetDO) crit.uniqueResult();
				
			} catch (HibernateException e) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " + e.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion �chou� : Projet inconnu");
			}
		}
		LOGGER.info("Fin m�thode : getProjet");
		
		return ProjetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO findProjetById(int pIdProjet) throws DaoException {
		
		//initialaisation du logger
		LOGGER.info("D�but m�thode : getProjet");
		
		ProjetDO lProjetDO = null;
		
		if (pIdProjet == 0) {
			throw new DaoException("L'identifiant est obligatoire. Valeur z�ro est interdit.");
		} else {
			try {
				LOGGER.info("On recup�re le projet � partie de son Id: " + pIdProjet);
				
				// create a new criteria
				final Criteria lCriteria = HibernateSessionFactory.getSession().createCriteria(ProjetDO.class);
				lCriteria.add(Restrictions.eq("idProjet", pIdProjet));
				lProjetDO = (ProjetDO) lCriteria.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion �chou� : Identifiant inconnu");
			}
		}
		LOGGER.info("Fin m�thode : getMembre");
		return lProjetDO;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO addProjet(final String pNomProjet, final String pCredential,
			final String pFrequence, final String pBranche, final String pDescription, final char pStatus,
			final boolean pActif, final String pUrl, final MembreDO pMembre) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : addProjet");
		
		//On instancie l'objet PrjetDO
		final ProjetDO lProjetDO = new ProjetDO();
		
		int lIdProjet = 0;
		
		//TODO a verifier la regle metier
		ProjetDO lProjetDOExiste = getProjet(pNomProjet, pUrl, pBranche);
		if (lProjetDOExiste != null) {
			
			throw new DaoException("Cr�ation �chou� : Le projet existe d�j�.");
		} else {
			//on set les infos dans le projet qu'on n'a instancie
			lProjetDO.setDescription(pDescription);
			lProjetDO.setBranche(pBranche);
			lProjetDO.setCredential(pCredential);
			lProjetDO.setFrequence(pFrequence);
			lProjetDO.setNomProjet(pNomProjet);
			lProjetDO.setActif(pActif);
			lProjetDO.setStatus(pStatus);
			lProjetDO.setUrl(pUrl);			
			lProjetDO.setClient(pMembre.getClient());		
			
			try {
				lIdProjet = (int) HibernateSessionFactory.getSession().save(lProjetDO);
				//On recupere l'Id du projet
				lProjetDO.setIdProjet(lIdProjet);
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete addProjet().");
				throw new DaoException("Connexion �chou� : Impossible de cr�er le projet");

			}
			LOGGER.info("Fin m�thode : addProjet");
			return lProjetDO;
		}
	}

	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ProjetDO updateProjet(final int pIdProjet, final String pNomProjet, final String pCredential,
			final String pFrequence, final String pBranche, final String pDescription, final char pStatus,
			final boolean pActif, final String pUrl, final ClientDO pClientDO) throws DaoException{
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : updateProjet");
		
		//On recupere le Projet via son Id
		ProjetDO lProjetDOExiste = findProjetById(pIdProjet);
		
		if (lProjetDOExiste == null) {
			throw new DaoException("Update �chou� : Le projet n'existe pas.");
			
		} else {
			lProjetDOExiste.setDescription(pDescription);
			lProjetDOExiste.setBranche(pBranche);
			lProjetDOExiste.setCredential(pCredential);
			lProjetDOExiste.setFrequence(pFrequence);
			lProjetDOExiste.setNomProjet(pNomProjet);
			lProjetDOExiste.setActif(pActif);
			lProjetDOExiste.setStatus(pStatus);
			lProjetDOExiste.setUrl(pUrl);
			lProjetDOExiste.setClient(pClientDO);
			
			try {
				//On met a jour le projet
				HibernateSessionFactory.getSession().update(lProjetDOExiste);
			
			} catch (HibernateException ex) {
				
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete updateProjet().");
				throw new DaoException("connexion �chou� : Impossible de mettre � jour le projet");
			}
		}
		LOGGER.info("Fin m�thode : updateProjet");
		return lProjetDOExiste;
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteProjet (final int pIdProjet) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : deleteMembre");
		
		//On recuepre le projet via son Id
		final ProjetDO lProjetDO = findProjetById(pIdProjet);
		
		// si le membre n'existe pas, on leve une exception
		if (lProjetDO == null) {
			throw new DaoException("Modification �chou� : Le projet n'est pas connu.");
		} else {
			try {
				// la m�thode delete permet de supprimer le projet dans la table
				HibernateSessionFactory.getSession().delete(lProjetDO);
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
						+ ex.getMessage() + " on complete deleteMembre().");
				throw new DaoException("Connexion �chou� : Impossible de supprimer le projet");
			}
		}
		LOGGER.info("Fin m�thode : deleteProjet");
	}
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public void deleteProjet (final ProjetDO pProjetDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : deleteMembre");

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
	 * @throws DaoException 
	 */
	@SuppressWarnings("unchecked")
	public List<ProjetDO> listerProjets(final ClientDO pClientDO) throws DaoException {
		
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : listerProjet");
		
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
			LOGGER.error("Exception - DataAccessException occurs : " 
					+ ex.getMessage() + " on complete listerMembres().");
			throw new DaoException("Connexion �chou� : Impossible de r�cup�rer la liste des projets");
		}
		
		LOGGER.info("Fin m�thode : listerProjet");
		return lListeProjet;
	}
		
}
