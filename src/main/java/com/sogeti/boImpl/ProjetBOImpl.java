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
	public ProjetDTO addProjet(final ProjetDTO pProjetDTO) throws DaoException {
		
		// on instancie l'objet ProjetDTO
		final ProjetDTO projetDTO = new ProjetDTO();
		
		// on recupere le membre via l'id
		final MembreDO membreDO = getMembreDAO().findMembreById(pProjetDTO.getIdMembre());
				
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
		projetDONew.setClient(membreDO.getClient());
		
		// on ajoute le projet	 
		final ProjetDO projetDO = getProjetDAO().addProjet(projetDONew);
		
		// on r�cupere l'id du projet et on passe dans l'objet projetDTO
		projetDTO.setIdProjet(projetDO.getIdProjet());
		
		// on instancie l'objet RoleProjetDO
		final RoleProjetDO roleProjetDO = new RoleProjetDO();
		roleProjetDO.setMembre(membreDO);
		roleProjetDO.setProjet(projetDO);
		roleProjetDO.setRole(membreDO.getRoleMembre());
		 
		// on ajoute le roleProjetDO
		getRoleProjetDAO().addRoleProjet(roleProjetDO);
		 
				
		return projetDTO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void updateProjet(final ProjetDTO pProjetDTO) throws DaoException {
		
		// on r�cupere le roleProjet via l'id projet
		RoleProjetDO roleProjet = getRoleProjetDAO().findRoleProjetByIdProjet(pProjetDTO.getIdProjet());
			
		// on ajoute le projet dans la table
		final ProjetDO projetDOUpdate = new ProjetDO();
		
		projetDOUpdate.setIdProjet(pProjetDTO.getIdProjet());
		projetDOUpdate.setActif(pProjetDTO.isActif());
		projetDOUpdate.setBranche(pProjetDTO.getBranche());
		projetDOUpdate.setCredential(pProjetDTO.getCredential());
		projetDOUpdate.setDescription(pProjetDTO.getDescription());
		projetDOUpdate.setFrequence(pProjetDTO.getFrequence());
		projetDOUpdate.setNomProjet(pProjetDTO.getNomProjet());
		projetDOUpdate.setStatus(pProjetDTO.getStatus());
		projetDOUpdate.setUrl(pProjetDTO.getUrl());
		
		final ClientDO clientDO = getClientDAO().findClientById(pProjetDTO.getIdClient());
		projetDOUpdate.setClient(clientDO);
		
		// Mise � jour un projet		
		getProjetDAO().updateProjet(projetDOUpdate);
		
		
		//On recuepre le Role via son Id
		final RoleDO roleDO = getRoleDAO().findRoleById(pProjetDTO.getIdRole());
		roleProjet.setRole(roleDO);
		
		// on met � jour le roleProjet
		getRoleProjetDAO().updateRoleProjet(roleProjet);
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void deleteProjet(final int pIdProjet) throws DaoException {
		
		//On interoge le service deleteProjet
		getProjetDAO().deleteProjet(pIdProjet);
			
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<ProjetDTO> listerProjets(final int pIdClient) throws DaoException {
		
		final ClientDO lClientDO = getClientDAO().findClientById(pIdClient);
		
		
		// on instance la liste des projets
		final List<ProjetDTO> lListeProjetDTO = new ArrayList<ProjetDTO>();
		
		// on interroge le service listerProjet
		final List<ProjetDO> listeProjetDO = getProjetDAO().listerProjets(lClientDO);
		
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
		
		// on r�cupere le membre via son identifiant
		final MembreDO membreDO = getMembreDAO().findMembreById(pIdMembre);
		
		// on obtient la liste RoleProjet
		final Set<RoleProjetDO> lListeRoleProjet = membreDO.getRoleProjet();
		
		// on boucle sur la liste RoleProjet
		for (RoleProjetDO roleProjetDO : lListeRoleProjet) {
			// on instancie le projetDTO
			final ProjetDTO projetDTO = new ProjetDTO();
			
			// on r�cupere l'objet ProjetDO
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
			
			// on ajoute l'ojet lProjetDTO dans la liste lListeProjetDTO
			lListeProjetDTO.add(projetDTO);
		}
		return lListeProjetDTO;
	}

}
