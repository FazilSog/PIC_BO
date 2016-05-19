package com.sogeti.bo;
import java.util.List;

import com.sogeti.dto.AuthentificationDTO;
import com.sogeti.dto.MembreDTO;
import com.sogeti.exception.DaoException;


public interface IMembreBO {
	
	/**
	 * Elle permet de vérifier si le membre existe et générer le token
	 * @param username le username d'un membre
	 * @param password le password d'un membre
	 * @return l'objet AuthentificationDTO
	 * @throws DaoException exception
	 */
	public AuthentificationDTO Authentification(String username, String password) throws DaoException;
	
	/**
	 * Elle permet de créer un membre.
	 * @param username le username d'un membre
	 * @param password le password d'un memebre
	 * @param status le status d'un membre
	 * @return le membreDTO
	 * @throws DaoException exception
	 */
	public MembreDTO addMembre (String username, String password, boolean status, int idClient,
			int idRole) throws DaoException;
	
	/**
	 * Elle permet de modifier un membre.
	 * @param idMembre l'identifiant d'un membre
	 * @param username le username d'un membre
	 * @param password le password d'un membre
	 * @param role le role d'un membre
	 * @param status le status d'un membre
	 * @throws DaoException exception
	 */
	public void updateMembre (int idMembre, String username, String password, boolean status, int idClient, int idRole) throws DaoException;
	
	
	/**
	 * Elle permet de supprimer un membre.
	 * @param idMembre l'identifiant d'un membre
	 * @throws DaoException exception
	 */
	public void deleteMembre (int idMembre) throws DaoException;
	
	
	/**
	 * Elle permet de retourner une liste des membres.
	 * 
	 * @return la liste des membres
	 * @throws DaoException exception
	 */
	public List<MembreDTO> listerMembres () throws DaoException;
}
