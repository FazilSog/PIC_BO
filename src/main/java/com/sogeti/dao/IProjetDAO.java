package com.sogeti.dao;

import java.util.List;

import com.sogeti.exception.DaoException;
import com.sogeti.model.ClientDO;
import com.sogeti.model.ProjetDO;

/**
 * 
 * @author moissa
 *
 */

public interface IProjetDAO {
	
	/**
	 * Elle permet de cr�er un projetDO.
	 * La m�thode v�rifie d�j� si le projet existe dans la table, 
	 * si oui, on leve une exception
	 * si non, on cr�e le projet et on recup�re son identifiant
	 * @param pProjetDO projetDO
	 * @throws DaoException execptionDAO
	 */
	public void create(final ProjetDO pProjetDO)throws DaoException;
		
	/**
	 * Elle permet de modifier un projet.
	 * La m�thode v�rifie d�j� si le projet existe dans la table via son id, 
	 * si oui, on modifie le membre
	 * si non, on leve une exception
	 * @param pProjetDO objet projetDO
	 * @throws DaoException execptionDAO
	 */
	public void update(final ProjetDO pProjetDO) throws DaoException;
	
	/**
	 * Elle permet de supprimer un projet par objet
	 * La m�thode v�rifie d�j� si le projet existe dans la table, 
	 * si oui, on supprime le projet
	 * si non, on leve une exception
	 * @param pIdProjet id du projet
	 * @throws DaoException execptionDAO
	 */
	public void delete(final ProjetDO pProjetDO) throws DaoException;

	/**
	 * Elle permet de retourner la liste des projets
	 * @return liste de projets
	 * @throws DaoException execptionDAO
	 */
	public List<ProjetDO> listeObjects() throws DaoException;
		
	/**
	 * Elle permet de retourner la liste des projets d'un client
	 * @param pClientDO l'objet Client
	 * @return liste de projets
	 * @throws DaoException execptionDAO
	 */
	public List<ProjetDO> listeObjects(final ClientDO pClientDO) throws DaoException;
	
	/**
	 * Elle permet de recuperer un projet via son Id
	 * @param pIdProjet id du projet
	 * @return l'objet Projet
	 * @throws DaoException execptionDAO
	 */
	public ProjetDO find(final int pIdProjet) throws DaoException;


}
