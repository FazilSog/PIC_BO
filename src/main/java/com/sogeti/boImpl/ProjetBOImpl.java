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
	public ProjetDTO addProjet(final ProjetDTO pProjetDTO, final int pIdMembre) throws DaoException{
		
		// on recupere le membre via l'id
		final MembreDO lMembreDO = getMembreDAO().findMembreById(pIdMembre);
		
				
		// on ajoute le projet dans la table
		final ProjetDO lProjetDO = getProjetDAO().addProjet(pProjetDTO.getNomProjet(), pProjetDTO.getCredential(),
				pProjetDTO.getFrequence(), pProjetDTO.getBranche(), pProjetDTO.getDescription(), pProjetDTO.getStatus(),
				pProjetDTO.isActif(), pProjetDTO.getUrl(), lMembreDO);
		 
		 pProjetDTO.setIdProjet(lProjetDO.getIdProjet());
		 
		 getRoleProjetDAO().addRoleProjet(lMembreDO, lMembreDO.getRoleMembre(), lProjetDO);
		 
				
		return pProjetDTO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void updateProjet(final ProjetDTO pProjetDTO, final int pIdRole, final int pIdMembre) throws DaoException{
		
		//On recupere le membre via Son Id
		final MembreDO lMembreDO = getMembreDAO().findMembreById(pIdMembre);
		
		final ProjetDO lProjetDO = getProjetDAO().updateProjet(pProjetDTO.getIdProjet(), pProjetDTO.getNomProjet(), pProjetDTO.getCredential(),
				pProjetDTO.getFrequence(), pProjetDTO.getBranche(), pProjetDTO.getDescription(), pProjetDTO.getStatus(),
				pProjetDTO.isActif(), pProjetDTO.getUrl(), lMembreDO.getClient());
		
		//On recuepre le Role via son Id
		final RoleDO lRoleDO = getRoleDAO().findRoleById(pIdRole);
		
		//TODO la methode update permet de mettre a jour la table projet et aussi RoleProjet
		//On met à jour la table ROLEPROJET
		getRoleProjetDAO().updateRoleProjet(lMembreDO, lRoleDO, lProjetDO);
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
			ProjetDTO lProjetDTO = new ProjetDTO();
			
			lProjetDTO.setActif(projetDO.isActif());
			lProjetDTO.setNomProjet(projetDO.getNomProjet());
			lProjetDTO.setBranche(projetDO.getBranche());
			lProjetDTO.setDescription(projetDO.getDescription());
			lProjetDTO.setCredential(projetDO.getCredential());
			lProjetDTO.setFrequence(projetDO.getFrequence());
			lProjetDTO.setIdProjet(projetDO.getIdProjet());
			lProjetDTO.setStatus(projetDO.getStatus());
			lProjetDTO.setUrl(projetDO.getUrl());
			
			//projetDTO.setClient(projetDO.getClient());
			// on ajoute le projetDTO dans la liste<ProjetDTO>
			
			lListeProjetDTO.add(lProjetDTO);
			
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
		final MembreDO lMembreDO = getMembreDAO().findMembreById(pIdMembre);
		
		// on obtient la liste RoleProjet
		final Set<RoleProjetDO> lRoleProjet = lMembreDO.getRoleProjet();
		
		// on boucle sur la liste RoleProjet
		for (RoleProjetDO roleProjetDO : lRoleProjet) {
			// on instancie le projetDTO
			final ProjetDTO lProjetDTO = new ProjetDTO();
			
			// on récupere l'objet ProjetDO
			final ProjetDO lProjetDO = roleProjetDO.getProjet();
			
			lProjetDTO.setActif(lProjetDO.isActif());
			lProjetDTO.setNomProjet(lProjetDO.getNomProjet());
			lProjetDTO.setBranche(lProjetDO.getBranche());
			lProjetDTO.setDescription(lProjetDO.getDescription());
			lProjetDTO.setCredential(lProjetDO.getCredential());
			lProjetDTO.setFrequence(lProjetDO.getFrequence());
			lProjetDTO.setIdProjet(lProjetDO.getIdProjet());
			lProjetDTO.setStatus(lProjetDO.getStatus());
			lProjetDTO.setUrl(lProjetDO.getUrl());
			
			// on ajoute l'ojet lProjetDTO dans la liste lListeProjetDTO
			lListeProjetDTO.add(lProjetDTO);
		}
		return lListeProjetDTO;
	}

}
