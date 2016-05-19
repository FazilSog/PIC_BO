package com.sogeti.boImpl;

import java.util.ArrayList;
import java.util.List;

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
import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;

@Component
public class ProjetBOImpl implements IProjetBO{
	
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
	public ProjetDTO addProjet(ProjetDTO projetDTO, int idMembre) throws DaoException{
		
		// on recupere le membre via l'id
		MembreDO membreDO = getMembreDAO().findMembreById(idMembre);
		
				
		// on ajoute le projet dans la table
		ProjetDO projetDO = getProjetDAO().addProjet(projetDTO.getNomProjet(), projetDTO.getCredential(),
				projetDTO.getFrequence(), projetDTO.getBranche(), projetDTO.getDescription(), projetDTO.getStatus(),
				projetDTO.isActif(), projetDTO.getUrl(), membreDO);
		 
		 projetDTO.setIdProjet(projetDO.getIdProjet());
		 
		 getRoleProjetDAO().addRoleProjet(membreDO, membreDO.getRoleMembre(), projetDO);
		 
				
		return projetDTO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void updateProjet(ProjetDTO projetDTO, int idRole, int idMembre) throws DaoException{
		
		//On recupere le membre via Son Id
		MembreDO membreDO = getMembreDAO().findMembreById(idMembre);
		
		ProjetDO projetDO = getProjetDAO().updateProjet(projetDTO.getIdProjet(), projetDTO.getNomProjet(), projetDTO.getCredential(),
				projetDTO.getFrequence(), projetDTO.getBranche(), projetDTO.getDescription(), projetDTO.getStatus(),
				projetDTO.isActif(), projetDTO.getUrl(), membreDO.getClient());
		
		//On recuepre le Role via son Id
		RoleDO roleDO = getRoleDAO().findRoleById(idRole);
		
		//TODO la methode update permet de mettre a jour la table projet et aussi RoleProjet
		//On met à jour la table ROLEPROJET
		getRoleProjetDAO().updateRoleProjet(membreDO, roleDO, projetDO);
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void deleteProjet(int idProjet) throws DaoException {
		
			//On interoge le service deleteProjet
			getProjetDAO().deleteProjet(idProjet);
		
			
		}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<ProjetDTO> listerProjets(int idClient) throws DaoException {
		
		ClientDO clientDO = getClientDAO().findClientById(idClient);
		
		// on instance la liste des projet
		List<ProjetDTO> listeProjetDTO = new ArrayList<ProjetDTO>();
		
		// on interroge le service listerProjet
		List<ProjetDO> listeProjetDO = getProjetDAO().listerProjets(clientDO);
		
		// on convertit la liste<ProjetDO> vers la liste<ProjetDTO>
		for(ProjetDO projetDO : listeProjetDO){
			// on instancie le projetDTO
			ProjetDTO projetDTO = new ProjetDTO();
			
			projetDTO.setActif(projetDO.isActif());
			projetDTO.setNomProjet(projetDO.getNomProjet());
			projetDTO.setBranche(projetDO.getBranche());
			projetDTO.setDescription(projetDO.getDescription());
			projetDTO.setCredential(projetDO.getCredential());
			projetDTO.setFrequence(projetDO.getFrequence());
			projetDTO.setIdProjet(projetDO.getIdProjet());
			projetDTO.setStatus(projetDO.getStatus());
			projetDTO.setUrl(projetDO.getUrl());
			//projetDTO.setClient(projetDO.getClient());
			// on ajoute le projetDTO dans la liste<ProjetDTO>
			listeProjetDTO.add(projetDTO);
			
		}
		
		return listeProjetDTO;
	}

}
