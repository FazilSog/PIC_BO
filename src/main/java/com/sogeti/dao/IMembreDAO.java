package com.sogeti.dao;

import java.util.List;

import com.sogeti.dao.model.MembreDO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 */

public interface IMembreDAO {
	

	/**
	 * Elle permet de vérifier si le membre existe dans la base 
	 * et générer le token
	 * @param pUsername le username d'un membre
	 * @param pPassword le password d'un membre
	 * @return le membreDO
	 * @throws DaoException exception
	 */
	public MembreDO authentifierMembre(final String pUsername, final String pPassword) throws DaoException;
	
	/**
	 * Elle permet de recupèrer le membre à partir de son identifiant
	 * @param pIdMembre l'identifiant d'un membre
	 * @return le membre DO
	 * @throws DaoException exception
	 */
	public MembreDO findMembreById(final int pIdMembre) throws DaoException; 
	
	/**
	 * Elle permet de recupèrer le membre à partir de son username et password
	 * @param pUsername le username d'un membre
	 * @param pPassword le password d'un membre
	 * @return le membreDO
	 * @throws DaoException exception
	 */
	public MembreDO findMembreByNameAndPass(final String pUsername, final String pPassword) throws DaoException; 
	
	/**
	 * Elle permet de mettre à jour le membre dans la base de données
	 * @param pMembreDO le membreDO
	 * @return le membreDO mise à jour avec le token
	 * @throws DaoException exception
	 */
	public MembreDO setMembre(final MembreDO pMembreDO) throws DaoException;
	
	/**
	 * Elle permet de créer un membre.
	 * La méthode vérifie déjà si le membre existe dans la table, 
	 * si oui, on leve une exception
	 * si non, on crée le membre et on recupère son identifiant
	 * @param pMembreDO le membre DO
	 * @return l'id d'un membre
	 * @throws DaoException exception
	 */
	public int addMembre(final MembreDO pMembreDO) throws DaoException;
	
	/**
	 * Elle permet de modifier un membre.
	 * La méthode vérifie déjà si le membre existe dans la table via son id, 
	 * si oui, on modifie le membre
	 * si non, on leve une exception
	 * @param pMembreDO le membreDO
	 * @throws DaoException exception
	 */
	public void updateMembre(final MembreDO pMembreDO) throws DaoException;
	
	
	/**
	 * Elle permet de supprimer un membre.
	 * La méthode vérifie déjà si le membre existe dans la table, 
	 * si oui, on supprime le membre
	 * si non, on leve une exception
	 * @param pIdMembre l'identifiant d'un membre
	 * @throws DaoException exception
	 */
	public void deleteMembre(final int pIdMembre) throws DaoException;
	
	/**
	 * Elle permet de supprimer un membre.
	 * La méthode vérifie déjà si le membre existe dans la table, 
	 * si oui, on supprime le membre
	 * si non, on leve une exception
	 * @param pMembreBO le mebreBO
	 * @throws DaoException exception
	 */
	public void deleteMembre(final MembreDO pMembreBO) throws DaoException;
	
	
	
	/**
	 * Elle permet de retourner une liste des membres.
	 * 
	 * @throws DaoException exception
	 */
	public List<MembreDO> listerMembres() throws DaoException;
}
