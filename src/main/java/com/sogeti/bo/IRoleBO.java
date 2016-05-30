package com.sogeti.bo;

import java.util.List;

import com.sogeti.dto.RoleDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 */

public interface IRoleBO {

	/**
	 * Elle permet de remonter la liste des roles
	 * @return la liste des roles
	 * @throws DaoException exception
	 */
	public List<RoleDTO> listeObjects() throws DaoException;
	
	/**
	 * Elle n'est pas implementée
	 * @param pObject
	 * @throws DaoException
	 */
	public void create(RoleDTO pObject) throws DaoException;
	
	/**
	 * Elle n'est pas implementée
	 * @param pObject
	 * @throws DaoException
	 */
	public void update(RoleDTO pObject) throws DaoException;
	
	/**
	 * Elle n'est pas implementée
	 * @param pId
	 * @throws DaoException
	 */
	public void delete(int pId) throws DaoException;
}
