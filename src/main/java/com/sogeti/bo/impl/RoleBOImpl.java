package com.sogeti.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sogeti.bo.IRoleBO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dto.RoleDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericBO;
import com.sogeti.model.RoleDO;

/**
 * 
 * @author moissa
 *
 */

@Component
public class RoleBOImpl extends GenericBO<RoleDTO> implements IRoleBO {

	@Autowired
	private IRoleDAO roleDAO;
	
	
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
	public List<RoleDTO> listeObjects() throws DaoException {
		
		// on instancie la liste des Roles DTO à remonter au font office
		final List<RoleDTO> lListeRoleDTO = new ArrayList<RoleDTO>();
		
		// on recupère la liste des Roles
		final List<RoleDO> lListRoleDO = getRoleDAO().listeObjects();
		
		for (RoleDO roleDO : lListRoleDO) {
			// on instancie l'objet RoleDTO
			final RoleDTO lRoleDTO =  new RoleDTO();
			
			lRoleDTO.setIdRole(roleDO.getIdRole());
			lRoleDTO.setCodeRole(roleDO.getCodeRole());
			lRoleDTO.setLibelleRole(roleDO.getLibelleRole());
			
			// on ajoute l'objete lRoleDTO dans la liste lListeRoleDTO
			lListeRoleDTO.add(lRoleDTO);
		}
		
		return lListeRoleDTO;
	}

	/**
	 * {@inheritDoc} 
	 */
	public void create(final RoleDTO pObject) throws DaoException {
		
		throw new DaoException("La méthode create n'est pas implementée");
		
	}

	/**
	 * {@inheritDoc} 
	 */
	public void update(final RoleDTO pObject) throws DaoException {
		throw new DaoException("La méthode update n'est pas implementée");
		
	}

	/**
	 * {@inheritDoc} 
	 */
	public void delete(final int pId) throws DaoException {
		throw new DaoException("La méthode delete n'est pas implementée");
		
	}


}
