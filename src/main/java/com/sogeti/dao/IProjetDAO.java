package com.sogeti.dao;

import java.util.List;

import com.sogeti.dao.model.ClientDO;
import com.sogeti.dao.model.ProjetDO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author moissa
 *
 */

public interface IProjetDAO {
	
	/**
	 * Elle permet de créer un projetDO.
	 * La méthode vérifie déjà si le projet existe dans la table, 
	 * si oui, on leve une exception
	 * si non, on crée le projet et on recupère son identifiant
	 * @param pProjetDO projetDO
	 * @return un objet de Type ProjetDO
	 * @throws DaoException execptionDAO
	 */
	public ProjetDO addProjet(final ProjetDO pProjetDO)throws DaoException;
		
	/**
	 * Elle permet de modifier un projet.
	 * La méthode vérifie déjà si le projet existe dans la table via son id, 
	 * si oui, on modifie le membre
	 * si non, on leve une exception
	 * @param pProjetDO objet projetDO
	 * @throws DaoException execptionDAO
	 */
	public void updateProjet(final ProjetDO pProjetDO) throws DaoException;
	
	/**
	 * Elle permet de supprimer un projet via son id
	 * La méthode vérifie déjà si le projet existe dans la table, 
	 * si oui, on supprime le projet
	 * si non, on leve une exception
	 * @param pIdProjet id du projet
	 * @throws DaoException execptionDAO
	 */
	public void deleteProjet(final int pIdProjet) throws DaoException;
	
	/**
	 * Elle permet de supprimer un projet par objet
	 * La méthode vérifie déjà si le projet existe dans la table, 
	 * si oui, on supprime le projet
	 * si non, on leve une exception
	 * @param pIdProjet id du projet
	 * @throws DaoException execptionDAO
	 */
	public void deleteProjet(final ProjetDO pProjetDO) throws DaoException;

	
	/**
	 * Elle permet de retourner la liste des projets
	 * @return liste de projets
	 * @throws DaoException execptionDAO
	 */
	public List<ProjetDO> listerProjets(final ClientDO pClientDO) throws DaoException;
	
	/**
	 * Elle permet de recuperer un projet via son Id
	 * @param pIdProjet id du projet
	 * @return l'objet Projet
	 * @throws DaoException execptionDAO
	 */
	public ProjetDO findProjetById(final int pIdProjet) throws DaoException;


}
