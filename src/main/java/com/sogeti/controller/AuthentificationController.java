package com.sogeti.controller;

import java.security.NoSuchAlgorithmException;

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
import com.sogeti.utils.Utils;

/**
 * 
 * @author syahiaou
 *
 */

@Controller
@CrossOrigin
@RequestMapping("PIC_BO/authentification")
public class AuthentificationController {
	
	private static final Logger LOGGER = Logger.getLogger(AuthentificationController.class.getName());
	
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

	/**
	 * Constructeur par défaut
	 */
	public AuthentificationController(){
		
		LOGGER.info("init AuthentificationController");
	}
	 
	@RequestMapping(value="/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> authentifier( @RequestBody MembreDTO pMembreDTO) {  
		
		String lUsername = pMembreDTO.getUsername();
		String lPassword = "";
		try {
			lPassword = Utils.encryptMdp(pMembreDTO.getPassword());
		} catch (NoSuchAlgorithmException ex) {
			LOGGER.warn(ex.getMessage());
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
		
		
		LOGGER.info("The username is: " + lUsername + ", The password is: " + lPassword);
		
		// on vérifie si le username et le password sont différents de null ou vide
		if (StringUtils.isNotBlank(lUsername) && StringUtils.isNotBlank(lPassword)) {
			try {
				AuthentificationDTO lAuthentifier = getMembreBO().authentification(lUsername, lPassword);
				return new ResponseEntity<Object>(lAuthentifier, HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("Le login / mdp invalide ou user désactivé", HttpStatus.FORBIDDEN);
		}

	}


}
