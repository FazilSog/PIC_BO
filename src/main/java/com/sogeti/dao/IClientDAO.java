package com.sogeti.dao;

import java.util.List;

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
	public ClientDO find(final int pIdClient) throws DaoException;
	
	/**
	 * Elle permet de créer un client
	 * @param pClient le client
	 * @throws DaoException exception
	 */
	public void create(final ClientDO pClient)  throws DaoException;
	
	/**
	 * Elle permet de faire un mise à jour d'un client
	 * @param pClient le client
	 * @throws DaoException exception
	 */
	public void update(final ClientDO pClient)  throws DaoException;
	
	/**
	 * Elle permet de supprimer un client
	 * @param pClient le client
	 * @throws DaoException exception
	 */
	public void delete(final ClientDO pClient)  throws DaoException;
	
	/**
	 * Elle permet de récuperer une liste des clients
	 * @return la liste
	 * @throws DaoException exception
	 */
	public List<ClientDO> listeObjects() throws DaoException;
	
}
