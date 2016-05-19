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

import com.sogeti.bo.IProjetBO;
import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;


@Controller
@CrossOrigin
@RequestMapping("PIC_BO/projet")
public class ProjetController {
	
	//Initialisation du logger
	private Logger LOGGER = Logger.getLogger(ProjetController.class);
	
	@Autowired
	private IProjetBO projetBO;

	/**
	 * @return the projetBO
	 */
	public IProjetBO getProjetBO() {
		return projetBO;
	}

	/**
	 * @param projetBO the projetBO to set
	 */
	public void setProjetBO(IProjetBO projetBO) {
		this.projetBO = projetBO;
	}
	
	public ProjetController(){
		System.out.println("init ProjetController");
	}
	
	/**
	 * Elle permet de créer le projet
	 * @param membreDTO l'objet projetDTO json envoyé par le front
	 * @return un responseEntity qui contient (soit un objet projetDTO (avec id seulement) avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/projet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addProjet( @RequestBody ProjetDTO projetDTO) 
	{   
		String branche = projetDTO.getBranche();
		int idProjet = projetDTO.getIdProjet();
		String frequence = projetDTO.getFrequence();
		char status = projetDTO.getStatus();
		String nomProjet = projetDTO.getNomProjet();
		String credential = projetDTO.getCredential();
		boolean actif = true;
		String description = projetDTO.getDescription();
		String url = projetDTO.getUrl();
		
		LOGGER.info("The idProjet is : " + idProjet + " , The branche is : " + branche 
				+ " , The frequance is : " + frequence + " , The Status is : " + status + " , The nomProjet is : " + nomProjet
				+ " , The credentiel is : " + credential + " , The actif is : " + actif + " , The description is : " + description
				+ " , The url is : " + url);
		
		// on vérifie si le username, le password et le role sont différents de null ou vide
		if (StringUtils.isNotBlank(branche) && StringUtils.isNotBlank(url) && StringUtils.isNotBlank(nomProjet) 
				&& StringUtils.isNotBlank(frequence) && StringUtils.isNotBlank(credential) 
				&& StringUtils.isNotBlank(String.valueOf(status)) && StringUtils.isNotBlank(description))
		{
		//TODO a verifier la regle metier et quels sont les champs obligatoire 
			try {
				// TODO id client à récuperer via le token
				ProjetDTO projetDTOAdd = getProjetBO().addProjet(projetDTO, 1);
				
				return new ResponseEntity<Object>(projetDTOAdd, HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("L'ajout a échoué", HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * Elle permet de modifier le projet
	 * @param membreDTO l'objet projetDTO json envoyé par le front
	 * @return un responseEntity qui contient (soit un objet null avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/updateprojet", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateProjet( @RequestBody ProjetDTO projetDTO) 
	{  

		String branche = projetDTO.getBranche();
		int idProjet = projetDTO.getIdProjet();
		String frequence = projetDTO.getFrequence();
		char status = projetDTO.getStatus();
		String nomProjet = projetDTO.getNomProjet();
		String credential = projetDTO.getCredential();
		boolean actif = true;
		String description = projetDTO.getDescription();
		String url = projetDTO.getUrl();
		
		LOGGER.info("The idProjet is : " + idProjet + " , The branche is : " + branche 
				+ " , The frequance is : " + frequence + " , The Status is : " + status + " , The nomProjet is : " + nomProjet
				+ " , The credentiel is : " + credential + " , The actif is : " + actif + " , The description is : " + description
				+ " , The url is : " + url);
		
		// on vérifie si le username, le password et le role sont différents de null ou vide
		if (StringUtils.isNotBlank(branche) && StringUtils.isNotBlank(url) && StringUtils.isNotBlank(nomProjet) 
				&& StringUtils.isNotBlank(String.valueOf(idProjet)) 
				&& StringUtils.isNotBlank(frequence) && StringUtils.isNotBlank(credential) 
				&& StringUtils.isNotBlank(String.valueOf(status)) && StringUtils.isNotBlank(description))
		{
			try {
				getProjetBO().updateProjet(projetDTO, 2, 1);
				
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
	 * Elle permet de supprimer le projet
	 * @param membreDTO l'objet projetDTO json envoyé par le front
	 * @return un responseEntity qui contient (soit un objet null avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/projet/{idProjet}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProjet( @PathVariable("idProjet")  int idProjet) 
	{  
		
		LOGGER.info("The id is : " + idProjet);
		
		// on vérifie si l'id est différent de zéro
		if (idProjet != 0 )
		{
			try {
				getProjetBO().deleteProjet(idProjet);
				
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
	@RequestMapping(value="/projets", method = RequestMethod.GET)
	public ResponseEntity<Object> listeProjets() 
	{  
		try {
			List<ProjetDTO> listeprojets = getProjetBO().listerProjets(1);
			
			return new ResponseEntity<Object>(listeprojets, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
}
