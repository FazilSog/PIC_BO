package com.sogeti.fwk;

import org.springframework.http.ResponseEntity;

import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 * @param <T>
 * @param <P>
 */
public abstract class GenericController<T, P> {
	

	/**
	 * Elle permet de créer un objet.
	 * @param pObject l'objet à créer
	 * @param pObjet2 l'objet2 à créer
	 * @throws DaoException exception
	 * @return ResponseEntity
	 */
	public abstract ResponseEntity<Object> create(final T pObject, final P pObjet2) throws DaoException;
	
	/**
	 * Elle permet de modifier un objet.
	 * @param pObject l'objet à modifier
	 * @throws DaoException exception
	 * @return ResponseEntity
	 */
	public abstract ResponseEntity<Object> update(final T pObject) throws DaoException;
	
	/**
	 * Elle permet de supprimer un objet.
	 * @param pId l'id à supprimer
	 * @throws DaoException exception
	 * @return ResponseEntity
	 */
	public abstract ResponseEntity<Object> delete(final int pId) throws DaoException;
	
	/**
	 * Elle permet de récuperer une liste des objects.
	 * @return la liste des objets
	 * @throws DaoException exception
	 */
	public abstract  ResponseEntity<Object> listeObjects() throws DaoException;

	
	
	
	

}
