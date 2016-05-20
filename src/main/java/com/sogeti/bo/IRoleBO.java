package com.sogeti.bo;

import java.util.List;

import com.sogeti.dto.RoleDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author moissa
 *
 */

public interface IRoleBO {

	/**
	 * Elle permet de remonter la liste des roles
	 * @return la liste des roles
	 * @throws DaoException exception
	 */
	public List<RoleDTO> listerRoles() throws DaoException;
}
