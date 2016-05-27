package com.sogeti.boImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sogeti.bo.IProjetBO;
import com.sogeti.dao.IClientDAO;
import com.sogeti.dao.IMembreDAO;
import com.sogeti.dao.IProjetDAO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dao.IRoleProjetDAO;
import com.sogeti.dao.model.ClientDO;
import com.sogeti.dao.model.MembreDO;
import com.sogeti.dao.model.ProjetDO;
import com.sogeti.dao.model.RoleDO;
import com.sogeti.dao.model.RoleProjetDO;
import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author moissa
 *
 */

@Component
public class ProjetBOImpl implements IProjetBO {
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IRoleProjetDAO roleProjetDAO;
	
	@Autowired 
	private IMembreDAO membreDAO;
	
	@Autowired
	private IProjetDAO projetDAO;
	
	@Autowired
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
	public void addProjet(final ProjetDTO pProjetDTO) throws DaoException {
		
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
	public void updateProjet(final ProjetDTO pProjetDTO) throws DaoException {
			
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
	public void deleteProjet(final int pIdProjet) throws DaoException {
		
		// on récupere le projet via son id pour vérifie s'il existe
		final ProjetDO projetDO = getProjetDAO().find(pIdProjet);
		
		//On interoge le service deleteProjet
		getProjetDAO().delete(projetDO);
			
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<ProjetDTO> listerProjets(final int pIdClient) throws DaoException {
		
		// on récuperer l'objet clientDO
		final ClientDO clientDO = getClientDAO().find(pIdClient);
		
		
		// on instance la liste des projets
		final List<ProjetDTO> lListeProjetDTO = new ArrayList<ProjetDTO>();
		
		// on interroge le service listerProjet
		final List<ProjetDO> listeProjetDO = getProjetDAO().listeObjects(clientDO);
		
		// on convertit la liste<ProjetDO> vers la liste<ProjetDTO>
		for(ProjetDO projetDO : listeProjetDO){
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
			
			lListeProjetDTO.add(projetDTO);
			
		}
		
		return lListeProjetDTO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<ProjetDTO> listerProjetsByMembre(int pIdMembre) throws DaoException {
		
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
	
	/**
	 * {@inheritDoc} 
	 */
	public void addAffectProjectToMembre(final ProjetDTO pProjetDTO) throws DaoException {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// on récupère l'objet ProjetDO via son id
		final ProjetDO projetDO =  getProjetDAO().find(idProjet);
		
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		// on récupère l'objet MembreDO via son id
		final MembreDO membreDO = getMembreDAO().find(idMembre);
		
		// l'id Role sélectionné
		final int idRole = pProjetDTO.getIdRole();
		// on récupère l'objet RoleDO via son id
		final RoleDO roleDO = getRoleDAO().find(idRole);
		
		// on instancie l'objet RoleProjetDO
		final RoleProjetDO roleProjetDO = new RoleProjetDO();
		roleProjetDO.setMembre(membreDO);
		roleProjetDO.setProjet(projetDO);
		roleProjetDO.setRole(roleDO);
		
		// on ajout l'objet RoleProjet dans la table
		getRoleProjetDAO().create(roleProjetDO);
		
	}
	
	
	/**
	 * {@inheritDoc} 
	 */
	public void updateAffectProjectToMembre(final ProjetDTO pProjetDTO) throws DaoException {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// on récupère l'objet ProjetDO via son id
		final ProjetDO projetDO =  getProjetDAO().find(idProjet);
		
		// on récupère l'objet RoleProjet via l'objet ProjetDO
		final RoleProjetDO roleProjetDO = getRoleProjetDAO().findRoleProjet(projetDO);
		
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		// on récupère l'objet MembreDO via son id
		final MembreDO membreDO = getMembreDAO().find(idMembre);
		// setter l'objet MembreDO dans RoleProjetDO
		roleProjetDO.setMembre(membreDO);
		
		// l'id Role sélectionné
		final int idRole = pProjetDTO.getIdRole();
		// on récupère l'objet RoleDO via son id
		final RoleDO roleDO = getRoleDAO().find(idRole);
		// setter l'objet MembreDO dans RoleProjetDO
		roleProjetDO.setRole(roleDO);
				
		// on ajout l'objet RoleProjet dans la table
		getRoleProjetDAO().update(roleProjetDO);
	}

	/**
	 * {@inheritDoc} 
	 */
	public void deleteAffectProjectToMembre(final ProjetDTO pProjetDTO) throws DaoException {
		
		// l'id Projet sélectionné
		final int idProjet = pProjetDTO.getIdProjet();
		// on récupère l'objet ProjetDO via son id
		final ProjetDO projetDO =  getProjetDAO().find(idProjet);
		
		// l'id Membre sélectionné
		final int idMembre = pProjetDTO.getIdMembre();
		// on récupère l'objet MembreDO via son id
		final MembreDO membreDO = getMembreDAO().find(idMembre);
		
		// on récupère l'objet RoleProjet via l'objet ProjetDO
		final RoleProjetDO roleProjetDO = getRoleProjetDAO().findRoleProjetByProjetAndMembre(projetDO, membreDO);
				
		// on ajout l'objet RoleProjet dans la table
		getRoleProjetDAO().delete(roleProjetDO);
	}
}
