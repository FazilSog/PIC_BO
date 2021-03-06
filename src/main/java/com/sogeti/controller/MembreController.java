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
import com.sogeti.fwk.GenericController;
import com.sogeti.utils.Token;
import com.sogeti.utils.Utils;
/**
 * 
 * @author moissa
 *
 */
@Controller
@RequestMapping("PIC_BO/membre")
public class MembreController extends GenericController<MembreDTO, HttpHeaders> {
	
	private static final Logger LOGGER = Logger.getLogger(MembreController.class);
	
	@Autowired
	private IMembreBO membreBO;
	
	 /**
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
	 * Constructeur par d�faut.
	 */
	public MembreController() {
		
		LOGGER.info("init MembreController");
	}
	 
	/**
	 * Elle permet de cr�er le membre.
	 * @param pMembreDTO l'objet membreDTO json envoy� par le front
	 * @param pHttpHeaders l'ogjet HttpHeaders envoy� par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins = "*", methods = RequestMethod.POST)
	@RequestMapping(value = "/membre", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<Object> create(@RequestBody final MembreDTO pMembreDTO, @RequestHeader final HttpHeaders pHttpHeaders) {  
		
		// on r�cup�re le token via le header
		final String token = Token.obtenirTokenByhttpHeaders(pHttpHeaders);

		final MembreDTO membreDTO = pMembreDTO;
		
		final String username = membreDTO.getUsername();
		String password = membreDTO.getPassword();
		int idRole = membreDTO.getIdRole();
		boolean status = true; 
		// D�crypter le token pour obtenir l'id Client
		int idClient = Token.obtenirIdClient(token);
		
		// on v�rifie si le username, le password sont diff�rents de null ou vide et id role, id client sont diff�rents de z�ro
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
				getMembreBO().create(membreDTO);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("L'ajout a �chou�", HttpStatus.FORBIDDEN);
		}

	}
	
	/**
	 * Elle permet de modifier le membre.
	 * @param pMembreDTO l'objet membreDTO json envoy� par le front
	 * @return un responseEntity qui contient (soit un objet null avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins = "*", methods = RequestMethod.PUT)
	@RequestMapping(value = "/Membre", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<Object> update(@RequestBody final MembreDTO pMembreDTO) {  
		
		final int idMembre = pMembreDTO.getIdMembre();
		final String username = pMembreDTO.getUsername();
		final String password = pMembreDTO.getPassword();
		final int idClient = pMembreDTO.getIdClient();
		final int idRole = pMembreDTO.getIdRole();
		final boolean status = pMembreDTO.isStatus(); 
		
		LOGGER.info("The username is : " + username + " , The password is : " + password 
				+ " ,  The Status is : " + status + " , The id is : " + idMembre);
		
		// on v�rifie si le username et le password  sont diff�rents de null ou vide
		if (idMembre != 0 && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) 
				&& idClient != 0 && idRole != 0) {
			try {
				getMembreBO().update(pMembreDTO);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("La modification a �chou�e", HttpStatus.FORBIDDEN);
		}

	}

	/**
	 * Elle permet de supprimer le membre.
	 * @param pIdMembre idMembre envoy� par le front
	 * @return un responseEntity qui contient (soit un objet null avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins = "*", methods = RequestMethod.DELETE)
	@RequestMapping(value = "/membre/{idMembre}", method = RequestMethod.DELETE)
	public final ResponseEntity<Object> delete(@PathVariable("idMembre") final int pIdMembre) {
		
		LOGGER.info("The id is : " + pIdMembre);
		
		// on v�rifie si l'id est diff�rent de z�ro
		if (pIdMembre != 0) {
			try {
				getMembreBO().delete(pIdMembre);
				
				return new ResponseEntity<Object>(HttpStatus.CREATED);
			} catch (DaoException ex) {
				return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<Object>("La modification a �chou�e", HttpStatus.FORBIDDEN);
		}

	}
	
	/**
	 * Elle permet de r�cuperer la liste des Membres.
	 * @return un responseEntity qui contient :
	 * (soit liste des membres avec le code status 201,
	 * ou un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins = "*", methods = RequestMethod.GET)
	@RequestMapping(value = "/membres", method = RequestMethod.GET)
	public final ResponseEntity<Object> listeObjects() {  
		
		try {
			List<MembreDTO> lListeMembres = getMembreBO().listeObjects();
			
			return new ResponseEntity<Object>(lListeMembres, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}

	}
	
	@CrossOrigin(origins = "*", methods = RequestMethod.GET)
	@RequestMapping(value = "/membresWith", method = RequestMethod.GET)
	public final ResponseEntity<Object> listeMembreWithOutProject() {  
		
		try {
			List<MembreDTO> lListeMembres = getMembreBO().listeObjects();
			
			return new ResponseEntity<Object>(lListeMembres, HttpStatus.CREATED);
		} catch (DaoException ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FORBIDDEN);
		}

	}


}
