package com.sogeti.bo;

import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 */

public interface IAffectProjetToMembreBO {
	
	/**
	 * Elle permet d'ajouter l'affectation d'un membre sur un projet
	 * @param pProjetDTO l'objet ProjetDTO
	 * @throws DaoException exception
	 */
	public void create(final ProjetDTO pProjetDTO) throws DaoException;
	
	/**
	 * Elle permet de modifier l'affectation d'un membre sur un projet
	 * @param pProjetDTO l'objet ProjetDTO
	 * @throws DaoException excetpion
	 */
	public void update(final ProjetDTO pProjetDTO) throws DaoException;
	
	/**
	 * Elle permet de supprimer l'affectation d'un membre sur un projet
	 * @param pProjetDTO l'objet ProjetDTO
	 * @throws DaoException exception
	 */
	public void delete(final ProjetDTO pProjetDTO) throws DaoException;
	
	

}
