package com.sogeti.bo;

import java.util.List;

import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;

public interface IProjetBO {
	
	/**
	 * Elle permet de créer un projet
	 * @param projetDTO le projet DTO
	 * @param idMembre id membre
	 * @return projetDTO qui contient l'idProjet
	 * @throws DaoException exception
	 */
	public ProjetDTO addProjet(ProjetDTO projetDTO, int idMembre) throws DaoException;
	
	/**
	 * Elle permet de modifier un projet
	 * @param projetDTO
	 * @throws DaoException exception
	 */
	public void updateProjet(ProjetDTO projetDTO, int idRole, int idMembre) throws DaoException;
	
	/**
	 * Elle permet de supprimer un projet
	 * @param idProjet
	 * @throws DaoException
	 */
	public void deleteProjet(int idProjet) throws DaoException;
	
	/**
	 * Elle permet de retourner la liste des projets
	 * @return
	 * @throws DaoException
	 */
	public List<ProjetDTO> listerProjets(int idClient) throws DaoException;
	

}
