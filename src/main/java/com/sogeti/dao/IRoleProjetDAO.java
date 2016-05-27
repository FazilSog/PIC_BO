package com.sogeti.dao;

import java.util.List;

import com.sogeti.dao.model.MembreDO;
import com.sogeti.dao.model.ProjetDO;
import com.sogeti.dao.model.RoleProjetDO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author syahiaou
 *
 */

public interface IRoleProjetDAO {
	
	/**
	 * Elle permet d'ajouter un role dans la table ROLEPROJET
	 * @param pRoleProjetDO l'objet roleProjetDO
	 * @throws DaoException exception
	 */
	public void create(final RoleProjetDO pRoleProjetDO) throws DaoException;
	
	/**
	 * Elle permet de modifier un role dans la table ROLEPROJET
	 *@param pRoleProjet objet role projet
	 *@throws DaoException exception
	 */
	public void update(final RoleProjetDO pRoleProjet) throws DaoException;
	
	
	/**
	 * Elle permet de supprimer une ligne dans la table RoleProjetDO
	 * @param pRoleProjetDO objet RoleProjetDO
	 * @throws DaoException
	 */
	public void delete(final RoleProjetDO pRoleProjetDO) throws DaoException;
	
	/**
	 * Elle permet de recuperer un RoleProjet via idRoleProjet
	 * @param pIdRoleProjet
	 * @return objet de type  RoleProjetDO
	 * @throws DaoException exception
	 */
	public RoleProjetDO find (final int pIdRoleProjet) throws DaoException;
	
	/**
	 * Elle permet de recuperer un RoleProjet via l'objet Projet
	 * @param pProjetDO le projet DO
	 * @return objet de type  RoleProjetDO
	 * @throws DaoException exception
	 */
	public RoleProjetDO findRoleProjet (final ProjetDO pProjetDO) throws DaoException;
	
	/**
	 * Elle permet de récuperer un RoleProjet via l'objet projet et l'objet membre
	 * @param pProjetDO l'objet Projet
	 * @param pMembreDO l'objet Membre
	 * @return l'objet RoleProjetDO
	 * @throws DaoException exception
	 */
	public RoleProjetDO findRoleProjetByProjetAndMembre (final ProjetDO pProjetDO, final MembreDO pMembreDO) throws DaoException;
	
	/**
	 * Permet de récuperer la liste de roleProjet
	 * @return la liste
	 * @throws DaoException exception
	 */
	public List<RoleProjetDO> listeObjects() throws DaoException;
	

}
