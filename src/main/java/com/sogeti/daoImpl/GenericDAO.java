package com.sogeti.daoImpl;

import java.util.List;

import com.sogeti.exception.DaoException;

/**
 * 
 * @author moissa
 *
 */
public abstract class GenericDAO<T> {

	/**
	 * Elle permet de cr�er un objet
	 * @param object l'objet � cr�er
	 * @throws DaoException exception
	 */
	public abstract void create(T pObject) throws DaoException;
	
	/**
	 * Elle permet de modifier un objet
	 * @param object l'objet � modifier
	 * @throws DaoException exception
	 */
	public abstract void update(T pObject) throws DaoException;
	
	/**
	 * Elle permet de supprimer un objet
	 * @param object l'objet � supprimer
	 * @throws DaoException exception
	 */
	public abstract void delete(T pObject) throws DaoException;
	
	/**
	 * Elle permet de r�cuperer un objet via l'id
	 * @param id l'id d'un objet
	 * @return l'objet
	 * @throws DaoException exception
	 */
	public abstract T find(int pId) throws DaoException;
	
	/**
	 * Elle permet de r�cuperer une liste des objects
	 * @return la liste des objets
	 * @throws DaoException exception
	 */
	public abstract List<T> listeObjects() throws DaoException;
	
}
