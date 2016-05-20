package com.sogeti.dao;

import java.util.List;

import com.sogeti.dao.model.RoleDO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 */

public interface IRoleDAO {
	
	/**
	 * Elle permet de recuperer le role via son Id
	 * @param pIdRole l'id role
	 * @return Objet de type RoleDO 
	 * @throws DaoException exception
	 */
	public RoleDO findRoleById(final int pIdRole) throws DaoException;
	
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
	public List<RoleDO> listerRoles() throws DaoException;
}
