package com.sogeti.dao;

import java.util.List;

import com.sogeti.dao.model.ClientDO;
import com.sogeti.dao.model.MembreDO;
import com.sogeti.dao.model.ProjetDO;
import com.sogeti.exception.DaoException;
/**
 * 
 * @author moissa
 *
 */
public interface IProjetDAO {
	
	/**
	 * Elle permet de créer un projet.
	 * La méthode vérifie déjà si le projet existe dans la table, 
	 * si oui, on leve une exception
	 * si non, on crée le projet et on recupère son identifiant
	 * @param pNomProjet nom du projet
	 * @param pCredential credential du projet 
	 * @param pFrequence frequenance du projet 
	 * @param pBranche branche du projet
	 * @param pDescription description du projet 
	 * @param pStatus status du projet 
	 * @param pActif url du projet 
	 * @param pUrl url du projet 
	 * @return objet un objet de Type ProjetDO
	 * @throws DaoException execptionDAO
	 */
	public ProjetDO addProjet(final String pNomProjet, final String pCredential,
			final String pFrequence, final String pBranche, final String pDescription, final char pStatus,
			final boolean pActif, final String pUrl, final MembreDO pMembre )throws DaoException;
		
	/**
	 * Elle permet de modifier un projet.
	 * La méthode vérifie déjà si le projet existe dans la table via son id, 
	 * si oui, on modifie le membre
	 * si non, on leve une exception
	 * @param pIdProjet id du projet 
	 * @param pNomProjet nom du projet 
	 * @param pCredential credential du projet 
	 * @param pFrequence frequance du projet 
	 * @param pBranche branche du projet 
	 * @param pDescription description du projet 
	 * @param pStatus status du projet 
	 * @param pActif actif du projet 
	 * @param pUrl url du projet 
	 * @throws DaoException execptionDAO
	 */
	public ProjetDO updateProjet(final int pIdProjet, final String pNomProjet, final String pCredential,
			final String pFrequence, final String pBranche, final String pDescription, final char pStatus,
			final boolean pActif, final String pUrl, ClientDO pClientDO) throws DaoException;
	
	/**
	 * lle permet de supprimer un projet.
	 * La méthode vérifie déjà si le projet existe dans la table, 
	 * si oui, on supprime le projet
	 * si non, on leve une exception
	 * @param pIdProjet id du projet
	 * @throws DaoException execptionDAO
	 */
	public void deleteProjet(final int pIdProjet) throws DaoException;
	
	/**
	 * lle permet de supprimer un projet.
	 * La méthode vérifie déjà si le projet existe dans la table, 
	 * si oui, on supprime le projet
	 * si non, on leve une exception
	 * @param idProjet id du projet 
	 * @throws DaoException execptionDAO
	 */
	public void deleteProjet(final ProjetDO pProjetDO) throws DaoException;
	
	/**
	 * Elle permet de retourner la liste des projets
	 * @return
	 * @throws DaoException execptionDAO
	 */
	public List<ProjetDO> listerProjets(final ClientDO pClientDO) throws DaoException;
	
	/**
	 * Elle permet de recuperer un projet via son Id
	 * @param pIdProjet id du projet
	 * @return
	 * @throws DaoException execptionDAO
	 */
	public ProjetDO findProjetById(final int pIdProjet) throws DaoException;
	

}
