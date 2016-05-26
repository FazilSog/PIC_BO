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
	 * Elle permet de cr�er un projet
	 * @param pProjetDTO le projet DTO
	 * @return projetDTO qui contient l'idProjet
	 * @throws DaoException exception
	 */
	public ProjetDTO addProjet(final ProjetDTO pProjetDTO) throws DaoException;
	
	/**
	 * Elle permet de modifier un projet
	 * @param pProjetDTO le projet DTO
	 * @param pIdRole l'id role
	 * @param pIdMembre l'id membre
	 * @throws DaoException exception
	 */
	public void updateProjet(final ProjetDTO pProjetDTO) throws DaoException;
	
	/**
	 * Elle permet de supprimer un projet
	 * @param pIdProjet l'id projet
	 * @throws DaoException exception
	 */
	public void deleteProjet(final int pIdProjet) throws DaoException;
	
	/**
	 * Elle permet de retourner la liste des projets
	 * @param pIdClient l'id client
	 * @return la liste des projets 
	 * @throws DaoException exception
	 */
	public List<ProjetDTO> listerProjets(final int pIdClient) throws DaoException;
	
	/**
	 * Elle permet de retourner la liste des projets d'un membre
	 * @param pIdMembre l'id membre
	 * @return la liste des projets 
	 * @throws DaoException exception
	 */
	public List<ProjetDTO> listerProjetsByMembre(final int pIdMembre) throws DaoException;
	
	/**
	 * Elle permet d'ajouter l'affectation d'un membre sur un projet
	 * @param pProjetDTO l'objet ProjetDTO
	 * @throws DaoException exception
	 */
	public void addAffectProjectToMembre(final ProjetDTO pProjetDTO) throws DaoException;
	
	/**
	 * Elle permet de modifier l'affectation d'un membre sur un projet
	 * @param pProjetDTO l'objet ProjetDTO
	 * @throws DaoException excetpion
	 */
	public void updateAffectProjectToMembre(final ProjetDTO pProjetDTO) throws DaoException;
	
	/**
	 * Elle permet de supprimer l'affectation d'un membre sur un projet
	 * @param pProjetDTO l'objet ProjetDTO
	 * @throws DaoException exception
	 */
	public void deleteAffectProjectToMembre(final ProjetDTO pProjetDTO) throws DaoException;
	

}
