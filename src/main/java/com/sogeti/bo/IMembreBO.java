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
	 * Elle permet de vérifier si le membre existe et générer le token
	 * @param PUsername le username d'un membre
	 * @param pPassword le password d'un membre
	 * @return l'objet AuthentificationDTO
	 * @throws DaoException exception
	 */
	public AuthentificationDTO Authentification(final String PUsername, final String pPassword) throws DaoException;
	
	/**
	 * Elle permet de créer un membre.
	 * @param pUsername le username d'un membre
	 * @param pPassword le password d'un memebre
	 * @param pStatus le status d'un membre
	 * @return le membreDTO
	 * @throws DaoException exception
	 */
	public MembreDTO addMembre (final String pUsername, final String pPassword, final boolean pStatus, final int pIdClient,
			final int pIdRole) throws DaoException;
	
	/**
	 * Elle permet de modifier un membre.
	 * @param pIdMembre l'identifiant d'un membre
	 * @param pUsername le username d'un membre
	 * @param pPassword le password d'un membre
	 * @param role le role d'un membre
	 * @param pStatus le status d'un membre
	 * @throws DaoException exception
	 */
	public void updateMembre (final int pIdMembre, final String pUsername, final String pPassword, final boolean pStatus, 
			final int pIdClient, final int pIdRole) throws DaoException;
	
	
	/**
	 * Elle permet de supprimer un membre.
	 * @param pIdMembre l'identifiant d'un membre
	 * @throws DaoException exception
	 */
	public void deleteMembre (final int pIdMembre) throws DaoException;
	
	
	/**
	 * Elle permet de retourner une liste des membres.
	 * 
	 * @return la liste des membres
	 * @throws DaoException exception
	 */
	public List<MembreDTO> listerMembres () throws DaoException;
}
