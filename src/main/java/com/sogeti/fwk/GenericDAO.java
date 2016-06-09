package com.sogeti.fwk;

import java.util.List;

import com.sogeti.exception.DaoException;

/**
 * 
 * @author moissa
 * @param <T>
 *
 */
public abstract class GenericDAO<T> {

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
	 * @param pObject l'objet à supprimer
	 * @throws DaoException exception
	 */
	public abstract void delete(final T pObject) throws DaoException;
	
	/**
	 * Elle permet de récuperer un objet via l'id.
	 * @param pId l'id d'un objet
	 * @return l'objet
	 * @throws DaoException exception
	 */
	public abstract T find(final int pId) throws DaoException;
	
	/**
	 * Elle permet de récuperer une liste des objects.
	 * @return la liste des objets
	 * @throws DaoException exception
	 */
	public abstract List<T> listeObjects() throws DaoException;
	
}
