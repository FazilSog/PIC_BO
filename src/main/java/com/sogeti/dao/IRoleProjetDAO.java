package com.sogeti.dao;

import com.sogeti.dao.model.RoleProjetDO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 */

public interface IRoleProjetDAO {
	
	/**
	 * Elle permet d'ajouter un role dans la table ROLEPROJET
	 * @param pRoleProjetDO l'objet roleProjetDO
	 * @throws DaoException exception
	 */
	public void addRoleProjet(final RoleProjetDO pRoleProjetDO) throws DaoException;
	
	/**
	 * Elle permet de modifier un role dans la table ROLEPROJET
	 *@param pRoleProjet objet role projet
	 *@throws DaoException exception
	 */
	public void updateRoleProjet(final RoleProjetDO pRoleProjet) throws DaoException;
	
	
	/**
	 * Elle permet de supprimer une ligne dans la table RoleProjetDO
	 * @param pRoleProjetDO objet RoleProjetDO
	 * @throws DaoException
	 */
	public void deleteRoleProjet(final RoleProjetDO pRoleProjetDO) throws DaoException;
	
	/**
	 * Elle permet de recuperer un RoleProjet via idRoleProjet
	 * @param pIdRoleProjet
	 * @return objet de type  RoleProjetDO
	 * @throws DaoException exception
	 */
	public RoleProjetDO findRoleProjetByID (final int pIdRoleProjet) throws DaoException;
	
	/**
	 * Elle permet de recuperer un RoleProjet via l'id projet
	 * @param pIdProjet id projet
	 * @return objet de type  RoleProjetDO
	 * @throws DaoException exception
	 */
	public RoleProjetDO findRoleProjetByIdProjet (final int pIdProjet) throws DaoException;
	

}
