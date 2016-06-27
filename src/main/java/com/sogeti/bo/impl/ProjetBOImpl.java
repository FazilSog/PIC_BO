package com.sogeti.bo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sogeti.bo.IProjetBO;
import com.sogeti.dao.IClientDAO;
import com.sogeti.dao.IMembreDAO;
import com.sogeti.dao.IProjetDAO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dao.IRoleProjetDAO;
import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericBO;
import com.sogeti.model.ClientDO;
import com.sogeti.model.MembreDO;
import com.sogeti.model.ProjetDO;
import com.sogeti.model.RoleProjetDO;

/**
 * 
 * @author moissa
 *
 */

@Component
public class ProjetBOImpl extends GenericBO<ProjetDTO> implements IProjetBO {
	
	//@Autowired
	private IRoleDAO roleDAO;
	
	//@Autowired
	private IRoleProjetDAO roleProjetDAO;
	
	//@Autowired 
	private IMembreDAO membreDAO;
	
	//@Autowired
	private IProjetDAO projetDAO;
	
	//@Autowired
	private IClientDAO clientDAO;
	
	
	
	
	/**
	 * @return the clientDAO
	 */
	public IClientDAO getClientDAO() {
		return clientDAO;
	}
	/**
	 * @param clientDAO the clientDAO to set
	 */
	public void setClientDAO(IClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
	/**
	 * @return the roleDAO
	 */
	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}
	/**
	 * @param roleDAO the roleDAO to set
	 */
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	/**
	 * @return the roleProjetDAO
	 */
	public IRoleProjetDAO getRoleProjetDAO() {
		return roleProjetDAO;
	}
	/**
	 * @param roleProjetDAO the roleProjetDAO to set
	 */
	public void setRoleProjetDAO(IRoleProjetDAO roleProjetDAO) {
		this.roleProjetDAO = roleProjetDAO;
	}
	/**
	 * @return the membreDAO
	 */
	public IMembreDAO getMembreDAO() {
		return membreDAO;
	}
	/**
	 * @param membreDAO the membreDAO to set
	 */
	public void setMembreDAO(IMembreDAO membreDAO) {
		this.membreDAO = membreDAO;
	}
	
	/**
	 * @return the projetDAO
	 */
	public IProjetDAO getProjetDAO() {
		return projetDAO;
	}
	/**
	 * @param projetDAO the projetDAO to set
	 */
	public void setProjetDAO(IProjetDAO projetDAO) {
		this.projetDAO = projetDAO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void create(final ProjetDTO pProjetDTO) throws DaoException {
		
		// on recupere le client via l'id
		final ClientDO clientDO = getClientDAO().find(pProjetDTO.getIdClient());
				
		// on ajoute le projet dans la table
		final ProjetDO projetDONew = new ProjetDO();
		
		projetDONew.setActif(pProjetDTO.isActif());
		projetDONew.setBranche(pProjetDTO.getBranche());
		projetDONew.setCredential(pProjetDTO.getCredential());
		projetDONew.setDescription(pProjetDTO.getDescription());
		projetDONew.setFrequence(pProjetDTO.getFrequence());
		projetDONew.setNomProjet(pProjetDTO.getNomProjet());
		projetDONew.setStatus(pProjetDTO.getStatus());
		projetDONew.setUrl(pProjetDTO.getUrl());
		projetDONew.setClient(clientDO);
		
		// on ajoute le projet	 
		getProjetDAO().create(projetDONew);
		
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void update(final ProjetDTO pProjetDTO) throws DaoException {
			
		// on récupere le projet via son id pour vérifie s'il existe
		final ProjetDO projetDO = getProjetDAO().find(pProjetDTO.getIdProjet());
		
				
		projetDO.setIdProjet(pProjetDTO.getIdProjet());
		projetDO.setActif(pProjetDTO.isActif());
		projetDO.setBranche(pProjetDTO.getBranche());
		projetDO.setCredential(pProjetDTO.getCredential());
		projetDO.setDescription(pProjetDTO.getDescription());
		projetDO.setFrequence(pProjetDTO.getFrequence());
		projetDO.setNomProjet(pProjetDTO.getNomProjet());
		projetDO.setStatus(pProjetDTO.getStatus());
		projetDO.setUrl(pProjetDTO.getUrl());
		
		final ClientDO clientDO = getClientDAO().find(pProjetDTO.getIdClient());
		projetDO.setClient(clientDO);
		
		// Mise à jour un projet		
		getProjetDAO().update(projetDO);
		
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void delete(final int pIdProjet) throws DaoException {
		
		// on récupere le projet via son id pour vérifie s'il existe
		final ProjetDO projetDO = getProjetDAO().find(pIdProjet);
		
		//On interoge le service deleteProjet
		getProjetDAO().delete(projetDO);
			
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<ProjetDTO> listeObjects() throws DaoException {
		
		//On declate la liste projet DTO
		final List<ProjetDTO> lListeProjetDTOs = new ArrayList<ProjetDTO>();
		//On interoge le service listeObjects
		final List<ProjetDO> lListeProjetDos = getProjetDAO().listeObjects();
		
		// on convertit la liste<ProjetDO> vers la liste<ProjetDTO>
		for (ProjetDO projetDO : lListeProjetDos) {
			ProjetDTO projetDTO = new ProjetDTO();
			
			projetDTO.setActif(projetDO.isActif());
			projetDTO.setActif(projetDO.isActif());
			projetDTO.setNomProjet(projetDO.getNomProjet());
			projetDTO.setBranche(projetDO.getBranche());
			projetDTO.setDescription(projetDO.getDescription());
			projetDTO.setCredential(projetDO.getCredential());
			projetDTO.setFrequence(projetDO.getFrequence());
			projetDTO.setIdProjet(projetDO.getIdProjet());
			projetDTO.setStatus(projetDO.getStatus());
			projetDTO.setUrl(projetDO.getUrl());
			projetDTO.setIdClient(projetDO.getClient().getIdClient());
			
			//On ajoute le projetDTO à la lListeProjetDTOs 
			lListeProjetDTOs.add(projetDTO);
		}
		
		return lListeProjetDTOs;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<ProjetDTO> listerProjetsByClient(final int pIdClient) throws DaoException {
		
		// on récuperer l'objet clientDO
		final ClientDO clientDO = getClientDAO().find(pIdClient);
		
		
		// on instance la liste des projets
		final List<ProjetDTO> lListeProjetDTO = new ArrayList<ProjetDTO>();
		
		// on interroge le service listerProjet
		final List<ProjetDO> listeProjetDO = getProjetDAO().listeObjects(clientDO);
		
		// on convertit la liste<ProjetDO> vers la liste<ProjetDTO>
		for (ProjetDO projetDO : listeProjetDO) {
			// on instancie le projetDTO
			final ProjetDTO projetDTO = new ProjetDTO();
			
			projetDTO.setActif(projetDO.isActif());
			projetDTO.setNomProjet(projetDO.getNomProjet());
			projetDTO.setBranche(projetDO.getBranche());
			projetDTO.setDescription(projetDO.getDescription());
			projetDTO.setCredential(projetDO.getCredential());
			projetDTO.setFrequence(projetDO.getFrequence());
			projetDTO.setIdProjet(projetDO.getIdProjet());
			projetDTO.setStatus(projetDO.getStatus());
			projetDTO.setUrl(projetDO.getUrl());
			projetDTO.setIdClient(projetDO.getClient().getIdClient());
			
			//On ajoute le projetDTO à la lListeProjetDTOs
			lListeProjetDTO.add(projetDTO);
			
		}
		
		return lListeProjetDTO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<ProjetDTO> listerProjetsByMembre(final int pIdMembre) throws DaoException {
		
		// on instance la liste des projets
		final List<ProjetDTO> lListeProjetDTO = new ArrayList<ProjetDTO>();
		
		// on récupere le membre via son identifiant
		final MembreDO membreDO = getMembreDAO().find(pIdMembre);
		
		// on obtient la liste RoleProjet
		final Set<RoleProjetDO> lListeRoleProjet = membreDO.getRoleProjet();
		
		// on boucle sur la liste RoleProjet
		for (RoleProjetDO roleProjetDO : lListeRoleProjet) {
			// on instancie le projetDTO
			final ProjetDTO projetDTO = new ProjetDTO();
			
			// on récupere l'objet ProjetDO
			final ProjetDO projetDO = roleProjetDO.getProjet();
			
			projetDTO.setActif(projetDO.isActif());
			projetDTO.setNomProjet(projetDO.getNomProjet());
			projetDTO.setBranche(projetDO.getBranche());
			projetDTO.setDescription(projetDO.getDescription());
			projetDTO.setCredential(projetDO.getCredential());
			projetDTO.setFrequence(projetDO.getFrequence());
			projetDTO.setIdProjet(projetDO.getIdProjet());
			projetDTO.setStatus(projetDO.getStatus());
			projetDTO.setUrl(projetDO.getUrl());
			projetDTO.setIdClient(projetDO.getClient().getIdClient());
			projetDTO.setIdMembre(pIdMembre);
			
			// on ajoute l'ojet lProjetDTO dans la liste lListeProjetDTO
			lListeProjetDTO.add(projetDTO);
		}
		return lListeProjetDTO;
	}
	
}
