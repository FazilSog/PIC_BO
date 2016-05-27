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
	 * Elle permet de créer un objet
	 * @param object l'objet à créer
	 * @throws DaoException exception
	 */
	public abstract void create(T pObject) throws DaoException;
	
	/**
	 * Elle permet de modifier un objet
	 * @param object l'objet à modifier
	 * @throws DaoException exception
	 */
	public abstract void update(T pObject) throws DaoException;
	
	/**
	 * Elle permet de supprimer un objet
	 * @param object l'objet à supprimer
	 * @throws DaoException exception
	 */
	public abstract void delete(T pObject) throws DaoException;
	
	/**
	 * Elle permet de récuperer un objet via l'id
	 * @param id l'id d'un objet
	 * @return l'objet
	 * @throws DaoException exception
	 */
	public abstract T find(int pId) throws DaoException;
	
	/**
	 * Elle permet de récuperer une liste des objects
	 * @return la liste des objets
	 * @throws DaoException exception
	 */
	public abstract List<T> listeObjects() throws DaoException;
	
}
