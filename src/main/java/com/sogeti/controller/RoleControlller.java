package com.sogeti.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sogeti.bo.IRoleBO;
import com.sogeti.dto.RoleDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author moissa
 *
 */
@Controller
@CrossOrigin
@RequestMapping("PIC_BO/role")
public class RoleControlller {

	//Initialisation du logger
	private Logger lLOGGER = Logger.getLogger(ProjetController.class);
	
	@Autowired
	private IRoleBO roleBO;
	
	
	/**
	 * @return the roleBO
	 */
	public IRoleBO getRoleBO() {
		return roleBO;
	}

	/**
	 * @param roleBO the roleBO to set
	 */
	public void setRoleBO(IRoleBO roleBO) {
		this.roleBO = roleBO;
	}

	/**
	 * Elle permet de récuperer la liste des roles
	 * 
	 * @return un responseEntity qui contient (soit liste des membres avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> listeRoles() 
	{  
		lLOGGER.info("Début méthode listerRoles dans le controlleur : RoleController");
		try {
			final List<RoleDTO> lListeRolesDTO = getRoleBO().listerRoles();
			
			return new ResponseEntity<Object>(lListeRolesDTO, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
}
