package com.sogeti.dao;

import com.sogeti.dao.model.MembreDO;
import com.sogeti.dao.model.ProjetDO;
import com.sogeti.dao.model.RoleDO;
import com.sogeti.dao.model.RoleProjetDO;
import com.sogeti.exception.DaoException;

public interface IRoleProjetDAO {
	
	/**
	 * Elle permet d'ajouter un role dans la table ROLEPROJET
	 * @param pMembreDO objet MembreDO
	 * @param pRoleDO  objet RoleDO
	 * @param pProjetDO objet ProjetDO
	 * @throws DaoException exception
	 */
	public void addRoleProjet(final MembreDO pMembreDO, final RoleDO pRoleDO, final ProjetDO pProjetDO) throws DaoException;
	
	/**
	 * Elle permet de modifier un role dans la table ROLEPROJET
	 *@param pMembreDO objet MembreDO
	 *@param pRoleDO  objet RoleDO
	 *@param pProjetDO objet ProjetDO
	 *@throws DaoException exception
	 */
	public void updateRoleProjet(final MembreDO pMembreDO, final RoleDO pRoleDO, final ProjetDO pProjetDO) throws DaoException;
	
	/**
	 * 
	 * Elle permet de supprimer un role dans la table ROLEPROJET
	 *@param pMembreDO objet MembreDO
	 *@param pRoleDO  objet RoleDO
	 *@param pProjetDO objet ProjetDO
	 *@throws DaoException exception
	 */
	public void deleteRoleProjet(final MembreDO pMembreDO, final RoleDO pRoleDO, final ProjetDO pProjetDO) throws DaoException;
	
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
	

}
