package com.sogeti.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sogeti.bo.IProjetBO;
import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.utils.Token;
/**
 * 
 * @author syahiaou
 *
 */

@Controller
@RequestMapping("PIC_BO/projet")
public class ProjetController {
	
	//Initialisation du logger
	private static final Logger LOGGER = Logger.getLogger(ProjetController.class);
	
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
	
	public ProjetController() {
		LOGGER.info("init ProjetController");
	}
	
	/**
	 * Elle permet de créer le projet
	 * @param pProjetDTO l'objet projetDTO json envoyé par le front
	 * @param pHttpHeaders le http headers
	 * @return un responseEntity qui contient (soit un objet projetDTO (avec id seulement) avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.POST)
	@RequestMapping(value="/projet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addProjet( @RequestBody ProjetDTO pProjetDTO, @RequestHeader HttpHeaders pHttpHeaders) {   
		final ProjetDTO  projetDTO = pProjetDTO;
		
		// on récupère le token via le header
		final String token = Token.obtenirTokenByhttpHeaders(pHttpHeaders);

		// Décrypter le token pour obtenir l'id Client
		final int idClient = Token.obtenirIdClient(token);
		projetDTO.setIdClient(idClient);
		
		final String branche = projetDTO.getBranche();
		final int idProjet = projetDTO.getIdProjet();
		final String frequence = projetDTO.getFrequence();
		final char status = projetDTO.getStatus();
		final String nomProjet = projetDTO.getNomProjet();
		final String credential = projetDTO.getCredential();
		final boolean actif = true;
		final String description = projetDTO.getDescription();
		final String url = projetDTO.getUrl();
		
		LOGGER.info("The idProjet is : " + idProjet + " , The branche is : " + branche 
				+ " , The frequance is : " + frequence + " , The Status is : " + status + " , The nomProjet is : " + nomProjet
				+ " , The credentiel is : " + credential + " , The actif is : " + actif + " , The description is : " + description
				+ " , The url is : " + url);
		
		//TODO a verifier la regle metier et quelles sont les champs obligatoires 
		// on vérifie si le username, le password et le role sont différents de null ou vide
		if (StringUtils.isNotBlank(branche) && StringUtils.isNotBlank(url) && StringUtils.isNotBlank(nomProjet) 
				&& StringUtils.isNotBlank(frequence) && StringUtils.isNotBlank(credential) 
				&& StringUtils.isNotBlank(String.valueOf(status)) && StringUtils.isNotBlank(description))
		{
			try {
				final ProjetDTO lProjetDTOAdd = getProjetBO().addProjet(projetDTO);
				
				return new ResponseEntity<Object>(lProjetDTOAdd, HttpStatus.CREATED);
			} catch (DaoException ex) {
				LOGGER.warn(ex.getMessage());
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("L'ajout a échoué", HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * Elle permet de modifier le projet
	 * @param pProjetDTO l'objet projetDTO json envoyé par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.PUT)
	@RequestMapping(value="/updateprojet", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateProjet( @RequestBody ProjetDTO pProjetDTO) {  

		final String branche = pProjetDTO.getBranche();
		final int idProjet = pProjetDTO.getIdProjet();
		final String frequence = pProjetDTO.getFrequence();
		final char status = pProjetDTO.getStatus();
		final String nomProjet = pProjetDTO.getNomProjet();
		final String credential = pProjetDTO.getCredential();
		final boolean actif = true;
		final String description = pProjetDTO.getDescription();
		final String url = pProjetDTO.getUrl();
		
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
				getProjetBO().updateProjet(pProjetDTO);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				LOGGER.warn(ex.getMessage());
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("La modification a échouée", HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * Elle permet de supprimer le projet
	 * @param pIdProjet l'id projet
	 * @return un responseEntity qui contient (le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.DELETE)
	@RequestMapping(value="/projet/{idProjet}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProjet( @PathVariable("idProjet")  int pIdProjet) {  
		
		LOGGER.info("The id is : " + pIdProjet);
		
		// on vérifie si l'id est différent de zéro
		if (pIdProjet != 0 )
		{
			try {
				getProjetBO().deleteProjet(pIdProjet);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				LOGGER.warn(ex.getMessage());
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<Object>("La modification a échouée", HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * Elle permet de récuperer la liste des projets d'un client
	 * @param pHttpHeaders le http headers
	 * @return un responseEntity qui contient (soit liste des membres avec le code status 201,
	 * ou soit un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.GET)
	@RequestMapping(value="/projets", method = RequestMethod.GET)
	public ResponseEntity<Object> listeProjets(@RequestHeader HttpHeaders pHttpHeaders) {  
		// on récupère le token via le header
		final String token = Token.obtenirTokenByhttpHeaders(pHttpHeaders);
		
		// Décrypter le token pour obtenir l'id Client
		final int idClient = Token.obtenirIdClient(token);
		
		try {
			final List<ProjetDTO> lListeprojets = getProjetBO().listerProjets(idClient);
			// on envoi la liste des projets + un http 201
			return new ResponseEntity<Object>(lListeprojets, HttpStatus.CREATED);
		} catch (DaoException ex) {
			LOGGER.warn(ex.getMessage());
			// on envoi le message d'erreur + un http 403
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * Elle permet de récuperer la liste des projets d'un membre
	 * @param pHttpHeaders le http headers
	 * @return un responseEntity qui contient (soit liste des membres avec le code status 201,
	 * ou soit un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.GET)
	@RequestMapping(value="/projetsMembre", method = RequestMethod.GET)
	public ResponseEntity<Object> listeProjetsByMembre(@RequestHeader HttpHeaders pHttpHeaders) {  
		// on récupère le token via le header
		final String token = Token.obtenirTokenByhttpHeaders(pHttpHeaders);
		
		// Décrypter le token pour obtenir l'id Membre
		final int idMembre = Token.obtenirIdMembre(token);
				
		try {
			final List<ProjetDTO> lListeprojets = getProjetBO().listerProjetsByMembre(idMembre);
			// on envoi la liste des projets + un http 201
			return new ResponseEntity<Object>(lListeprojets, HttpStatus.CREATED);
		} catch (DaoException ex) {
			LOGGER.warn(ex.getMessage());
			// on envoi le message d'erreur + un http 403
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	/**
	 * Elle permet d'affecter un membre sur un projet
	 * @param pProjetDTO le projetDTO json envoyé par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou soit un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.POST)
	@RequestMapping(value="/addAffectProjet", method = RequestMethod.POST)
	public ResponseEntity<Object> addAffectProjectToMembre(@RequestBody ProjetDTO pProjetDTO) {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		// l'id Role sélectionné
		final int idRole = pProjetDTO.getIdRole();
		
		if (idProjet != 0 && idMembre != 0 && idRole != 0)
		{
			try {
				// on interroge le service BO pour affecter un projet sur un membre
				getProjetBO().addAffectProjectToMembre(pProjetDTO);
				// on envoi un http status  201
				return new ResponseEntity<Object>(HttpStatus.CREATED);
				
			} catch (DaoException ex) {
				LOGGER.warn(ex.getMessage());
				// on envoi le message d'erreur + un http 403
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("Impossible d'affecter un membre sur un projet", HttpStatus.FORBIDDEN);
		}
	
	}
	
	/**
	 * Elle permet de modifier l'affectation d'un membre sur un projet
	 * @param pProjetDTO le projetDTO json envoyé par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou soit un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.PUT)
	@RequestMapping(value="/updateAffectProjet", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateAffectProjectToMembre(@RequestBody ProjetDTO pProjetDTO) {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		// l'id Role sélectionné
		final int idRole = pProjetDTO.getIdRole();
		
		if (idProjet != 0 && idMembre != 0 && idRole != 0)
		{
			try {
				// on interroge le service BO pour affecter un projet sur un membre
				getProjetBO().updateAffectProjectToMembre(pProjetDTO);
				// on envoi un http status  201
				return new ResponseEntity<Object>(HttpStatus.CREATED);
				
			} catch (DaoException ex) {
				LOGGER.warn(ex.getMessage());
				// on envoi le message d'erreur + un http 403
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("Impossible d'affecter un membre sur un projet", HttpStatus.FORBIDDEN);
		}
	
	}
	
	/**
	 * Elle permet d'affecter un membre sur un projet
	 * @param pProjetDTO le projetDTO json envoyé par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou soit un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.DELETE)
	@RequestMapping(value="/deleteAffectProjet", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAffectProjectToMembre(@RequestBody ProjetDTO pProjetDTO) {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		
		if (idProjet != 0 && idMembre != 0)
		{
			try {
				// on interroge le service BO pour affecter un projet sur un membre
				getProjetBO().deleteAffectProjectToMembre(pProjetDTO);
				// on envoi un http status  201
				return new ResponseEntity<Object>(HttpStatus.CREATED);
				
			} catch (DaoException ex) {
				LOGGER.warn(ex.getMessage());
				// on envoi le message d'erreur + un http 403
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("Impossible d'affecter un membre sur un projet", HttpStatus.FORBIDDEN);
		}
	
	}
}
