package com.sogeti.boImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sogeti.bo.IRoleBO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dao.model.RoleDO;
import com.sogeti.dto.RoleDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author moissa
 *
 */

@Component
public class RoleBOImpl implements IRoleBO {

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
	public List<RoleDTO> listerRoles() throws DaoException {
		
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

}
