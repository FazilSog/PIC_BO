package com.sogeti.dao;

import java.util.List;

import com.sogeti.exception.DaoException;
import com.sogeti.model.RoleDO;

/**
 * 
 * @author moissa
 *
 */

public interface IRoleDAO {
	
	/**
	 * Elle permet de recuperer le role via son Id
	 * @param pIdRole l'id role
	 * @return Objet de type RoleDO 
	 * @throws DaoException exception
	 */
	public RoleDO find(final int pIdRole) throws DaoException;
	
	/**
	 *  Elle permet de recuperer le role via son codeRole
	 * @param pCodeRole le code du role
	 * @return Objet de type RoleDO 
	 * @throws DaoException exception
	 */
	public RoleDO findRoleByCodeRole(final String pCodeRole) throws DaoException;
	
	/**
	 * Elle permet de récuperer la liste des roles
	 * @return la liste des roles
	 * @throws DaoException exception
	 */
	public List<RoleDO> listeObjects() throws DaoException;
	
	/**
	 * Permet de créer un role
	 * @param pRoleDO le roleDO
	 * @throws DaoException exception
	 */
	public void create(final RoleDO pRoleDO) throws DaoException;
	
	/**
	 * Permet de modifier un role
	 * @param pRoleDO le roleDO
	 * @throws DaoException exception
	 */
	public void update(final RoleDO pRoleDO) throws DaoException;
	
	/**
	 * Permet de supprimer un role
	 * @param pRoleDO le roleDO
	 * @throws DaoException exception
	 */
	public void delete(final RoleDO pRoleDO) throws DaoException;
}
