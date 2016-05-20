package com.sogeti.dao;

import com.sogeti.dao.model.ClientDO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 */

public interface IClientDAO {
	
	/**
	 * Elle permet de chercher un client via son id
	 * @param pIdClient l'id client
	 * @return l'objet client
	 * @throws DaoException exception
	 */
	public ClientDO findClientById(final int pIdClient) throws DaoException;

}
