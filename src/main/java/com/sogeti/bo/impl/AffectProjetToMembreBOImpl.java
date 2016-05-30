package com.sogeti.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sogeti.bo.IAffectProjetToMembreBO;
import com.sogeti.dao.IMembreDAO;
import com.sogeti.dao.IProjetDAO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dao.IRoleProjetDAO;
import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericBO;
import com.sogeti.model.MembreDO;
import com.sogeti.model.ProjetDO;
import com.sogeti.model.RoleDO;
import com.sogeti.model.RoleProjetDO;

/**
 * 
 * @author syahiaou
 *
 */

public class AffectProjetToMembreBOImpl extends GenericBO<ProjetDTO> implements IAffectProjetToMembreBO {
	
	@Autowired 
	private IMembreDAO membreDAO;
	
	@Autowired
	private IProjetDAO projetDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IRoleProjetDAO roleProjetDAO;
	
	

	/**
	 * @return the roleProjetDAO
	 */
	public IRoleProjetDAO getRoleProjetDAO() {
		return roleProjetDAO;
	}

	/**
	 * @param roleProjetDAO the roleProjetDAO to set
	 */
	public void setRoleProjetDAO(IRoleProjetDAO roleProjetDAO) {
		this.roleProjetDAO = roleProjetDAO;
	}

	/**
	 * @return the membreDAO
	 */
	public IMembreDAO getMembreDAO() {
		return membreDAO;
	}

	/**
	 * @param membreDAO the membreDAO to set
	 */
	public void setMembreDAO(IMembreDAO membreDAO) {
		this.membreDAO = membreDAO;
	}

	/**
	 * @return the projetDAO
	 */
	public IProjetDAO getProjetDAO() {
		return projetDAO;
	}

	/**
	 * @param projetDAO the projetDAO to set
	 */
	public void setProjetDAO(IProjetDAO projetDAO) {
		this.projetDAO = projetDAO;
	}

	/**
	 * @return the roleDAO
	 */
	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	/**
	 * @param roleDAO the roleDAO to set
	 */
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void create(final ProjetDTO pProjetDTO) throws DaoException {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// on récupère l'objet ProjetDO via son id
		final ProjetDO projetDO =  getProjetDAO().find(idProjet);
		
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		// on récupère l'objet MembreDO via son id
		final MembreDO membreDO = getMembreDAO().find(idMembre);
		
		// l'id Role sélectionné
		final int idRole = pProjetDTO.getIdRole();
		// on récupère l'objet RoleDO via son id
		final RoleDO roleDO = getRoleDAO().find(idRole);
		
		// on instancie l'objet RoleProjetDO
		final RoleProjetDO roleProjetDO = new RoleProjetDO();
		roleProjetDO.setMembre(membreDO);
		roleProjetDO.setProjet(projetDO);
		roleProjetDO.setRole(roleDO);
		
		// on ajout l'objet RoleProjet dans la table
		getRoleProjetDAO().create(roleProjetDO);
		
	}
	
	
	/**
	 * {@inheritDoc} 
	 */
	public void update(final ProjetDTO pProjetDTO) throws DaoException {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// on récupère l'objet ProjetDO via son id
		final ProjetDO projetDO =  getProjetDAO().find(idProjet);
		
		// on récupère l'objet RoleProjet via l'objet ProjetDO
		final RoleProjetDO roleProjetDO = getRoleProjetDAO().findRoleProjet(projetDO);
		
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		// on récupère l'objet MembreDO via son id
		final MembreDO membreDO = getMembreDAO().find(idMembre);
		// setter l'objet MembreDO dans RoleProjetDO
		roleProjetDO.setMembre(membreDO);
		
		// l'id Role sélectionné
		final int idRole = pProjetDTO.getIdRole();
		// on récupère l'objet RoleDO via son id
		final RoleDO roleDO = getRoleDAO().find(idRole);
		// setter l'objet MembreDO dans RoleProjetDO
		roleProjetDO.setRole(roleDO);
				
		// on ajout l'objet RoleProjet dans la table
		getRoleProjetDAO().update(roleProjetDO);
	}

	/**
	 * {@inheritDoc} 
	 */
	public void delete(final ProjetDTO pProjetDTO) throws DaoException {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// on récupère l'objet ProjetDO via son id
		final ProjetDO projetDO =  getProjetDAO().find(idProjet);
		
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		// on récupère l'objet MembreDO via son id
		final MembreDO membreDO = getMembreDAO().find(idMembre);
		
		// on récupère l'objet RoleProjet via l'objet ProjetDO
		final RoleProjetDO roleProjetDO = getRoleProjetDAO().findRoleProjetByProjetAndMembre(projetDO, membreDO);
				
		// on ajout l'objet RoleProjet dans la table
		getRoleProjetDAO().delete(roleProjetDO);
	}

	/**
	 * {@inheritDoc} 
	 */
	public void delete(int pId) throws DaoException {
		throw new DaoException("La méthode delete via l'Id n'est pas implementée");
		
	}

	/**
	 * {@inheritDoc} 
	 */
	public List<ProjetDTO> listeObjects() throws DaoException {
		throw new DaoException("La méthode ListeObjects n'est pas implementée");
	}
	

}
