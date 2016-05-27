package com.sogeti.controller;

import java.security.NoSuchAlgorithmException;
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

import com.sogeti.bo.IMembreBO;
import com.sogeti.dto.MembreDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.utils.Token;
import com.sogeti.utils.Utils;
/**
 * 
 * @author moissa
 *
 */
@Controller
@RequestMapping("PIC_BO/membre")
public class MembreController {
	
	private static final Logger LOGGER = Logger.getLogger(MembreController.class);
	
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
	public MembreController(){
		LOGGER.info("init MembreController");
	}
	 
	/**
	 * Elle permet de créer le membre
	 * @param pMembreDTO l'objet membreDTO json envoyé par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.POST)
	@RequestMapping(value="/membre", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addMembre( @RequestBody MembreDTO pMembreDTO, @RequestHeader HttpHeaders pHttpHeaders) {  
		// on récupère le token via le header
		final String token = Token.obtenirTokenByhttpHeaders(pHttpHeaders);

		final MembreDTO membreDTO = pMembreDTO;
		
		final String username = membreDTO.getUsername();
		String password = membreDTO.getPassword();
		int idRole = membreDTO.getIdRole();
		boolean status = true; 
		// Décrypter le token pour obtenir l'id Client
		int idClient = Token.obtenirIdClient(token);
		
		// on vérifie si le username, le password sont différents de null ou vide et id role, id client sont différents de zéro
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) && idClient != 0 && idRole != 0) {
			// on set l'id client et le status
			membreDTO.setIdClient(idClient);
			membreDTO.setStatus(status);
			
			// on crypte le mot de pass
			try {
				password = Utils.encryptMdp(password);
				
				membreDTO.setPassword(password);
			} catch (NoSuchAlgorithmException ex) {
				LOGGER.warn(ex.getMessage());
				return new ResponseEntity<Object>("Impossible de crypter le password !", HttpStatus.FORBIDDEN);
			}
			
			LOGGER.info("The username is : " + username + " , The password is : " + password 
					+ " , The Status is : " + status);
			
			try {
				getMembreBO().addMembre(membreDTO);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("L'ajout a échoué", HttpStatus.FORBIDDEN);
		}

	}
	
	/**
	 * Elle permet de modifier le membre
	 * @param pMembreDTO l'objet membreDTO json envoyé par le front
	 * @return un responseEntity qui contient (soit un objet null avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.PUT)
	@RequestMapping(value="/Membre", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateMembre( @RequestBody MembreDTO pMembreDTO) {  

		final int idMembre = pMembreDTO.getIdMembre();
		final String username = pMembreDTO.getUsername();
		final String password = pMembreDTO.getPassword();
		final int idClient = pMembreDTO.getIdClient();
		final int idRole = pMembreDTO.getIdRole();
		final boolean status = pMembreDTO.isStatus(); 
		
		LOGGER.info("The username is : " + username + " , The password is : " + password 
				+ " ,  The Status is : " + status + " , The id is : " + idMembre);
		
		// on vérifie si le username et le password  sont différents de null ou vide
		if (idMembre != 0 && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) 
				&& idClient != 0 && idRole != 0) {
			try {
				getMembreBO().updateMembre(pMembreDTO);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("La modification a échouée", HttpStatus.FORBIDDEN);
		}

	}

	/**
	 * Elle permet de supprimer le membre
	 * @param membreDTO l'objet membreDTO json envoyé par le front
	 * @return un responseEntity qui contient (soit un objet null avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.DELETE)
	@RequestMapping(value="/membre/{idMembre}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteMembre( @PathVariable("idMembre")  int pIdMembre) {  
		
		LOGGER.info("The id is : " + pIdMembre);
		
		// on vérifie si l'id est différent de zéro
		if (pIdMembre != 0 ) {
			try {
				getMembreBO().deleteMembre(pIdMembre);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("La modification a échouée", HttpStatus.FORBIDDEN);
		}

	}
	
	/**
	 * Elle permet de récuperer la liste des Membres
	 * 
	 * @return un responseEntity qui contient (soit liste des membres avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.GET)
	@RequestMapping(value="/membres", method = RequestMethod.GET)
	public ResponseEntity<Object> listeMembres() {  

		try {
			List<MembreDTO> lListeMembres = getMembreBO().listerMembres();
			
			return new ResponseEntity<Object>(lListeMembres, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}

	}

}
