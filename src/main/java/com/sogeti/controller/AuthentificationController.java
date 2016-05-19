package com.sogeti.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sogeti.bo.IMembreBO;
import com.sogeti.dto.AuthentificationDTO;
import com.sogeti.dto.MembreDTO;
import com.sogeti.exception.DaoException;

@Controller
@CrossOrigin
@RequestMapping("PIC_BO/authentification")
public class AuthentificationController {
	
	private Logger LOGGER = Logger.getLogger(AuthentificationController.class);
	
	@Autowired
	private IMembreBO membreBO;
	
	 /**O
	 * @return the membreBO
	 */
	public IMembreBO getMembreBO() {
		return membreBO;
	}

	/**
	 * @param membreBO the membreBO to set
	 */
	public void setMembreBO(IMembreBO membreBO) {
		this.membreBO = membreBO;
	}

	public AuthentificationController(){
		System.out.println("init AuthentificationController");
	}
	 
	@RequestMapping(value="/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> authentifier( @RequestBody MembreDTO membreDTO) {  

		String username = membreDTO.getUsername();
		String password = membreDTO.getPassword();
		
		LOGGER.info("The username is: " + username + ", The password is: " + password);
		
		// on vérifie si le username et le password sont différents de null ou vide
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password))
		{
			try {
				AuthentificationDTO authentifier = getMembreBO().Authentification(username, password);
				return new ResponseEntity<Object>(authentifier, HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("Le login / mdp invalide ou user désactivé", HttpStatus.FORBIDDEN);
		}

	}


}
