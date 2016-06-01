package com.sogeti.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sogeti.bo.IRoleBO;
import com.sogeti.dto.RoleDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericController;

/**
 * 
 * @author moissa
 *
 */
@Controller
@RequestMapping("PIC_BO/role")
public class RoleController extends GenericController<RoleDTO,HttpHeaders> {

	//Initialisation du logger
	private static final Logger lOGGER = Logger.getLogger(ProjetController.class);
	
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
	 * Constructeur par d�faut
	 */
	public RoleController() {
		
		lOGGER.info("init RoleController");
	}

	/**
	 * Elle permet de r�cuperer la liste des roles
	 * 
	 * @return un responseEntity qui contient (soit liste des membres avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.GET)
	@RequestMapping(value="/roles", method = RequestMethod.GET)
	public ResponseEntity<Object> listeObjects() {  
		
		//Logger
		lOGGER.info("D�but m�thode listerRoles dans le controlleur : RoleController");
		
		try {
			final List<RoleDTO> lListeRolesDTO = getRoleBO().listeObjects();
			
			return new ResponseEntity<Object>(lListeRolesDTO, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * {@inheritDoc} 
	 */
	public ResponseEntity<Object> create(RoleDTO pObject, HttpHeaders pObjet2)
			throws DaoException {
		throw new DaoException("La m�thode create n'est pas implement�e!");
		
	}

	/**
	 * {@inheritDoc} 
	 */
	public ResponseEntity<Object> update(RoleDTO pObject) throws DaoException {
		throw new DaoException("La m�thode update n'est pas implement�e!");
	}

	/**
	 * {@inheritDoc} 
	 */
	public ResponseEntity<Object> delete(int pId) throws DaoException {
		throw new DaoException("La m�thode delete n'est pas implement�e!!");
	}
	
}
