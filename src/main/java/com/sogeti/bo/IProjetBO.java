package com.sogeti.bo;

import java.util.List;

import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author moissa
 *
 */

public interface IProjetBO {
	
	/**
	 * Elle permet de créer un projet
	 * @param pProjetDTO le projet DTO
	 * @throws DaoException exception
	 */
	public void create(final ProjetDTO pProjetDTO) throws DaoException;
	
	/**
	 * Elle permet de modifier un projet
	 * @param pProjetDTO le projet DTO
	 * @param pIdRole l'id role
	 * @param pIdMembre l'id membre
	 * @throws DaoException exception
	 */
	public void update(final ProjetDTO pProjetDTO) throws DaoException;
	
	/**
	 * Elle permet de supprimer un projet
	 * @param pIdProjet l'id projet
	 * @throws DaoException exception
	 */
	public void delete(final int pIdProjet) throws DaoException;
	
	/**
	 * Elle permet de retourner la liste des projets
	 * @return la liste des projets
	 * @throws DaoException
	 */
	public List<ProjetDTO> listeObjects() throws DaoException;
	
	/**
	 * Elle permet de retourner la liste des projets d'un client
	 * @param pIdClient l'id client
	 * @return la liste des projets 
	 * @throws DaoException exception
	 */
	public List<ProjetDTO> listerProjetsByClient(final int pIdClient) throws DaoException;
	
	/**
	 * Elle permet de retourner la liste des projets d'un membre
	 * @param pIdMembre l'id membre
	 * @return la liste des projets 
	 * @throws DaoException exception
	 */
	public List<ProjetDTO> listerProjetsByMembre(final int pIdMembre) throws DaoException;
	

	

}
