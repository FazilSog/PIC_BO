package com.sogeti.dao;

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
	 * @param pIdRole
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
}
