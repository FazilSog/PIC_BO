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
	 * Elle permet de cr�er un objet.
	 * @param pObject l'objet � cr�er
	 * @param pObjet2 l'objet2 � cr�er
	 * @throws DaoException exception
	 * @return ResponseEntity
	 */
	public abstract ResponseEntity<Object> create(final T pObject, final P pObjet2) throws DaoException;
	
	/**
	 * Elle permet de modifier un objet.
	 * @param pObject l'objet � modifier
	 * @throws DaoException exception
	 * @return ResponseEntity
	 */
	public abstract ResponseEntity<Object> update(final T pObject) throws DaoException;
	
	/**
	 * Elle permet de supprimer un objet.
	 * @param pId l'id � supprimer
	 * @throws DaoException exception
	 * @return ResponseEntity
	 */
	public abstract ResponseEntity<Object> delete(final int pId) throws DaoException;
	
	/**
	 * Elle permet de r�cuperer une liste des objects.
	 * @return la liste des objets
	 * @throws DaoException exception
	 */
	public abstract  ResponseEntity<Object> listeObjects() throws DaoException;

	
	
	
	

}
