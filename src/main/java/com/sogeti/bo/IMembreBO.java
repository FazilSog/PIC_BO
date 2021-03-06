package com.sogeti.bo;
import java.util.List;

import com.sogeti.dto.AuthentificationDTO;
import com.sogeti.dto.MembreDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 */

public interface IMembreBO {
	
	/**
	 * Elle permet de v�rifier si le membre existe et g�n�rer le token.
	 * @param pUsername le username d'un membre
	 * @param pPassword le password d'un membre
	 * @return l'objet AuthentificationDTO
	 * @throws DaoException exception
	 */
	public AuthentificationDTO authentification(final String pUsername, final String pPassword) throws DaoException;
	
	/**
	 * Elle permet de cr�er un membre.
	 * @param pMembreDTO le membreDTO
	 * @throws DaoException exception
	 */
	public void create(final MembreDTO pMembreDTO) throws DaoException;
	
	/**
	 * Elle permet de modifier un membre.
	 * @param pMembreDTO le membre DTO
	 * @throws DaoException exception
	 */
	public void update(final MembreDTO pMembreDTO) throws DaoException;
	
	
	/**
	 * Elle permet de supprimer un membre.
	 * @param pIdMembre l'identifiant d'un membre
	 * @throws DaoException exception
	 */
	public void delete(final int pIdMembre) throws DaoException;
	
	
	/**
	 * Elle permet de retourner une liste des membres.
	 * @return la liste des membres
	 * @throws DaoException exception
	 */
	public List<MembreDTO> listeObjects() throws DaoException;
	
}
