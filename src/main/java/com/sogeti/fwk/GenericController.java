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
public abstract class GenericController <T,P> {
	

	/**
	 * Elle permet de cr�er un objet
	 * @param object l'objet � cr�er
	 * @throws DaoException exception
	 */
	public abstract ResponseEntity<Object> create(T pObject, P pObjet2) throws DaoException;
	
	/**
	 * Elle permet de modifier un objet
	 * @param object l'objet � modifier
	 * @throws DaoException exception
	 */
	public abstract ResponseEntity<Object> update(T pObject) throws DaoException;
	
	/**
	 * Elle permet de supprimer un objet
	 * @param object l'objet � supprimer
	 * @throws DaoException exception
	 */
	public abstract ResponseEntity<Object> delete(int pId) throws DaoException;
	
	/**
	 * Elle permet de r�cuperer une liste des objects
	 * @return la liste des objets
	 * @throws DaoException exception
	 */
	public abstract  ResponseEntity<Object> listeObjects() throws DaoException;

	
	
	
	

}
