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
/**
 * 
 * @author syahiaou
 *
 */

@Controller
@CrossOrigin
@RequestMapping("PIC_BO/projet")
public class ProjetController {
	
	//Initialisation du logger
	private Logger lLOGGER = Logger.getLogger(ProjetController.class);
	
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
	public ResponseEntity<Object> addProjet( @RequestBody ProjetDTO pProjetDTO) 
	{   
		String lBranche = pProjetDTO.getBranche();
		int lIdProjet = pProjetDTO.getIdProjet();
		String lFrequence = pProjetDTO.getFrequence();
		char lStatus = pProjetDTO.getStatus();
		String lNomProjet = pProjetDTO.getNomProjet();
		String lCredential = pProjetDTO.getCredential();
		boolean lActif = true;
		String lDescription = pProjetDTO.getDescription();
		String lUrl = pProjetDTO.getUrl();
		
		lLOGGER.info("The idProjet is : " + lIdProjet + " , The branche is : " + lBranche 
				+ " , The frequance is : " + lFrequence + " , The Status is : " + lStatus + " , The nomProjet is : " + lNomProjet
				+ " , The credentiel is : " + lCredential + " , The actif is : " + lActif + " , The description is : " + lDescription
				+ " , The url is : " + lUrl);
		
		// on vérifie si le username, le password et le role sont différents de null ou vide
		if (StringUtils.isNotBlank(lBranche) && StringUtils.isNotBlank(lUrl) && StringUtils.isNotBlank(lNomProjet) 
				&& StringUtils.isNotBlank(lFrequence) && StringUtils.isNotBlank(lCredential) 
				&& StringUtils.isNotBlank(String.valueOf(lStatus)) && StringUtils.isNotBlank(lDescription))
		{
			//TODO a verifier la regle metier et quels sont les champs obligatoire 
			try {
				final ProjetDTO lProjetDTOAdd = getProjetBO().addProjet(pProjetDTO);
				
				return new ResponseEntity<Object>(lProjetDTOAdd, HttpStatus.CREATED);
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
	public ResponseEntity<Object> updateProjet( @RequestBody ProjetDTO pProjetDTO) 
	{  

		String lBranche = pProjetDTO.getBranche();
		int lIdProjet = pProjetDTO.getIdProjet();
		String lFrequence = pProjetDTO.getFrequence();
		char lStatus = pProjetDTO.getStatus();
		String lNomProjet = pProjetDTO.getNomProjet();
		String lCredential = pProjetDTO.getCredential();
		boolean lActif = true;
		String lDescription = pProjetDTO.getDescription();
		String url = pProjetDTO.getUrl();
		
		lLOGGER.info("The idProjet is : " + lIdProjet + " , The branche is : " + lBranche 
				+ " , The frequance is : " + lFrequence + " , The Status is : " + lStatus + " , The nomProjet is : " + lNomProjet
				+ " , The credentiel is : " + lCredential + " , The actif is : " + lActif + " , The description is : " + lDescription
				+ " , The url is : " + url);
		
		// on vérifie si le username, le password et le role sont différents de null ou vide
		if (StringUtils.isNotBlank(lBranche) && StringUtils.isNotBlank(url) && StringUtils.isNotBlank(lNomProjet) 
				&& StringUtils.isNotBlank(String.valueOf(lIdProjet)) 
				&& StringUtils.isNotBlank(lFrequence) && StringUtils.isNotBlank(lCredential) 
				&& StringUtils.isNotBlank(String.valueOf(lStatus)) && StringUtils.isNotBlank(lDescription))
		{
			try {
				getProjetBO().updateProjet(pProjetDTO);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
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
	public ResponseEntity<Object> deleteProjet( @PathVariable("idProjet")  int lIdProjet) 
	{  
		
		lLOGGER.info("The id is : " + lIdProjet);
		
		// on vérifie si l'id est différent de zéro
		if (lIdProjet != 0 )
		{
			try {
				getProjetBO().deleteProjet(lIdProjet);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("La modification a échouée", HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * Elle permet de récuperer la liste des projets
	 * 
	 * @return un responseEntity qui contient (soit liste des membres avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/projets", method = RequestMethod.GET)
	public ResponseEntity<Object> listeProjets() 
	{  
		try {
			// TODO à récuper l'id client via le token
			List<ProjetDTO> lListeprojets = getProjetBO().listerProjets(1);
			
			return new ResponseEntity<Object>(lListeprojets, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * Elle permet de récuperer la liste des Membres
	 * 
	 * @return un responseEntity qui contient (soit liste des membres avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@RequestMapping(value="/projetsMembre", method = RequestMethod.GET)
	public ResponseEntity<Object> listeProjetsByMembre() 
	{  
		try {
			// TODO à récuper l'id Membre via le token
			List<ProjetDTO> lListeprojets = getProjetBO().listerProjetsByMembre(1);
			
			return new ResponseEntity<Object>(lListeprojets, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
}
