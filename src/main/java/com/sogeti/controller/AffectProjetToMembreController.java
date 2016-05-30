package com.sogeti.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sogeti.bo.IAffectProjetToMembreBO;
import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericController;

/**
 * 
 * @author syahiaou
 *
 */

@Controller
@RequestMapping("PIC_BO/projet")
public class AffectProjetToMembreController extends GenericController<ProjetDTO, HttpHeaders> {
	
	//Initialisation du logger
	private static final Logger LOGGER = Logger.getLogger(AffectProjetToMembreController.class);
	
	@Autowired
	private IAffectProjetToMembreBO affectProjetToMembre;

	/**
	 * @return the affectProjetToMembre
	 */
	public IAffectProjetToMembreBO getAffectProjetToMembre() {
		return affectProjetToMembre;
	}

	/**
	 * @param affectProjetToMembre the affectProjetToMembre to set
	 */
	public void setAffectProjetToMembre(IAffectProjetToMembreBO affectProjetToMembre) {
		this.affectProjetToMembre = affectProjetToMembre;
	}
	
	/**
	 * Elle permet d'affecter un membre sur un projet
	 * @param pProjetDTO le projetDTO json envoy� par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou soit un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.POST)
	@RequestMapping(value="/addAffectProjet", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody ProjetDTO pProjetDTO) {
		
		// l'id Projet s�lectionn�
		final int idProjet = pProjetDTO.getIdProjet();
		// l'id Membre s�lectionn�
		final int idMembre = pProjetDTO.getIdMembre();
		// l'id Role s�lectionn�
		final int idRole = pProjetDTO.getIdRole();
		
		if (idProjet != 0 && idMembre != 0 && idRole != 0) {
			try {
				// on interroge le service BO pour affecter un projet sur un membre
				getAffectProjetToMembre().create(pProjetDTO);
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
	 * @param pProjetDTO le projetDTO json envoy� par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou soit un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.PUT)
	@RequestMapping(value="/updateAffectProjet", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody ProjetDTO pProjetDTO) {
		
		// l'id Projet s�lectionn�
		final int idProjet = pProjetDTO.getIdProjet();
		// l'id Membre s�lectionn�
		final int idMembre = pProjetDTO.getIdMembre();
		// l'id Role s�lectionn�
		final int idRole = pProjetDTO.getIdRole();
		
		if (idProjet != 0 && idMembre != 0 && idRole != 0) {
			try {
				// on interroge le service BO pour affecter un projet sur un membre
				getAffectProjetToMembre().update(pProjetDTO);
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
	 * @param pProjetDTO le projetDTO json envoy� par le front
	 * @return un responseEntity qui contient (le code status 201,
	 * ou soit un message d'erreur avec le code status 403)
	 */
	@CrossOrigin(origins="*",methods = RequestMethod.DELETE)
	@RequestMapping(value="/deleteAffectProjet", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@RequestBody ProjetDTO pProjetDTO) {
		
		// l'id Projet s�lectionn�
		final int idProjet = pProjetDTO.getIdProjet();
		// l'id Membre s�lectionn�
		final int idMembre = pProjetDTO.getIdMembre();
		
		if (idProjet != 0 && idMembre != 0) {
			try {
				// on interroge le service BO pour affecter un projet sur un membre
				getAffectProjetToMembre().delete(pProjetDTO);
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

	@Override
	public ResponseEntity<Object> create(ProjetDTO pObject, HttpHeaders pObjet2)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> delete(int pId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> listeObjects() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
