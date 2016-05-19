package com.sogeti.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sogeti.bo.IMembreBO;
import com.sogeti.dto.MembreDTO;
import com.sogeti.exception.DaoException;

@Controller
@CrossOrigin
@RequestMapping("PIC_BO/membre")
public class MembreController {
	
	private Logger LOGGER = Logger.getLogger(MembreController.class);
	
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

	public MembreController(){
		System.out.println("init MembreController");
	}
	 
	/**
	 * Elle permet de créer le membre
	 * @param membreDTO l'objet membreDTO json envoyé par le front
	 * @return un responseEntity qui contient (soit un objet MembreDTO (avec id seulement) avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/membre", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addMembre( @RequestBody MembreDTO membreDTO) 
	{  

		String username = membreDTO.getUsername();
		String password = membreDTO.getPassword();
		int idClient = 1;
		int idRole = 1;
		
		boolean status = true; 
		
		LOGGER.info("The username is : " + username + " , The password is : " + password 
				+ " , The Status is : " + status);
		
		// on vérifie si le username, le password et le role sont différents de null ou vide
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password))
		{
			try {
				MembreDTO membreDTOAdd = getMembreBO().addMembre(username, password, status, idClient, idRole);
				
				return new ResponseEntity<Object>(membreDTOAdd, HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("L'ajout a échoué", HttpStatus.FORBIDDEN);
		}

	}
	
	/**
	 * Elle permet de modifier le membre
	 * @param membreDTO l'objet membreDTO json envoyé par le front
	 * @return un responseEntity qui contient (soit un objet null avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/Membre", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateMembre( @RequestBody MembreDTO membreDTO) 
	{  

		int idMembre = membreDTO.getIdMembre();
		String username = membreDTO.getUsername();
		String password = membreDTO.getPassword();
		int idClient = membreDTO.getIdClient();
		int idRole = membreDTO.getIdRole();
		boolean status = true; 
		
		LOGGER.info("The username is : " + username + " , The password is : " + password 
				+ " ,  The Status is : " + status + " , The id is : " + idMembre);
		
		// on vérifie si le username, le password, role et le status sont différents de null ou vide
		if (idMembre != 0 && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password))
		{
			try {
				getMembreBO().updateMembre(idMembre, username, password, status, idClient, idRole);
				
				return new ResponseEntity<Object>(null, HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("La modification a échouée", HttpStatus.FORBIDDEN);
		}

	}

	/**
	 * Elle permet de supprimer le membre
	 * @param membreDTO l'objet membreDTO json envoyé par le front
	 * @return un responseEntity qui contient (soit un objet null avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/membre/{idMembre}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteMembre( @PathVariable("idMembre")  int idMembre) 
	{  
		
		LOGGER.info("The id is : " + idMembre);
		
		// on vérifie si l'id est différent de zéro
		if (idMembre != 0 )
		{
			try {
				getMembreBO().deleteMembre(idMembre);
				
				return new ResponseEntity<Object>(null, HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("La modification a échouée", HttpStatus.FORBIDDEN);
		}

	}
	
	/**
	 * Elle permet de récuperer la liste des Membres
	 * 
	 * @return un responseEntity qui contient (soit liste des membres avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/membres", method = RequestMethod.GET)
	public ResponseEntity<Object> listeMembres() 
	{  

		try {
			List<MembreDTO> listeMembres = getMembreBO().listerMembres();
			
			return new ResponseEntity<Object>(listeMembres, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}

	}

}
