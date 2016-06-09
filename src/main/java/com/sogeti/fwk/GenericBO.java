package com.sogeti.fwk;

import java.util.List;

import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 * @param <T>
 */

public abstract class GenericBO<T> {
	
	/**
	 * Elle permet de créer un objet.
	 * @param pObject l'objet à créer
	 * @throws DaoException exception
	 */
	public abstract void create(final T pObject) throws DaoException;
	
	/**
	 * Elle permet de modifier un objet.
	 * @param pObject l'objet à modifier
	 * @throws DaoException exception
	 */
	public abstract void update(final T pObject) throws DaoException;
	
	/**
	 * Elle permet de supprimer un objet.
	 * @param pId id à supprimer
	 * @throws DaoException exception
	 */
	public abstract void delete(final int pId) throws DaoException;
	
	/**
	 * Elle permet de récuperer une liste des objects.
	 * @return la liste des objets
	 * @throws DaoException exception
	 */
	public abstract List<T> listeObjects() throws DaoException;

}
