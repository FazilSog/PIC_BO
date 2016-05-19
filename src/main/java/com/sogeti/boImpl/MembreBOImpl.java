package com.sogeti.boImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sogeti.bo.IMembreBO;
import com.sogeti.dao.IClientDAO;
import com.sogeti.dao.IMembreDAO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dao.model.ClientDO;
import com.sogeti.dao.model.MembreDO;
import com.sogeti.dao.model.RoleDO;
import com.sogeti.dto.AuthentificationDTO;
import com.sogeti.dto.MembreDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.utils.Token;

@Component
public class MembreBOImpl implements IMembreBO {

	@Autowired
	private IMembreDAO membreDAO;
	
	@Autowired
	private IClientDAO clientDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
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
	 * Getter membreDAO
	 * @return
	 */
	public IMembreDAO getMembreDAO() {
		return membreDAO;
	}

	/**
	 * setter le membreDAO
	 * @param membreDAO
	 */
	public void setMembreDAO(IMembreDAO membreDAO) {
		this.membreDAO = membreDAO;
	}

	/**
	 * {@inheritDoc} 
	 */
	public AuthentificationDTO Authentification(String username, String password) throws DaoException {
		// on instance l'objet AuthentificationDTO
		AuthentificationDTO authentification = new AuthentificationDTO();
		
		// on interroge le service AuthentifierMembre
		MembreDO membreDO = getMembreDAO().AuthentifierMembre(username, password);

		if (membreDO != null) {
		
			// on génère le token
			String tokenMembre = Token.generateToken(membreDO.getIdMembre(), membreDO.getClient().getIdClient());
			// on affecte la valeur du token au membre
			authentification.setTokenMembre(tokenMembre);
			membreDO.setToken(tokenMembre);
			// on met à jour le membre
			membreDO = getMembreDAO().setMembre(membreDO);
		}
		return authentification;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public MembreDTO addMembre (String username, String password, boolean status, int idClient, int idRole) throws DaoException
	{
		// on instancie l'objet MembreDTO
		MembreDTO membreDTO = new MembreDTO();
		
		// Recuperer le membre via son Id
		ClientDO clientDO = getClientDAO().findClientById(idClient);
		
		// Recuperer le membre via son Id
		RoleDO roleDO = getRoleDAO().findRoleById(idRole);
		
		// on interroge le service addMembre
		int idMembre = getMembreDAO().addMembre(username, password, status, clientDO, roleDO);
		// on remonte seulement l'id
		membreDTO.setIdMembre(idMembre);
		
		return membreDTO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void updateMembre (int idMembre, String username, String password, boolean status, int idClient, int idRole) throws DaoException
	{
		
		ClientDO clientDO = getClientDAO().findClientById(idClient);
		RoleDO roleDO = getRoleDAO().findRoleById(idRole);
		
		// on interroge le service updateMembre
		getMembreDAO().updateMembre(idMembre, username, password, status, clientDO, roleDO);		
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void deleteMembre (int idMembre) throws DaoException
	{
		// on interroge le service deleteMembre
		getMembreDAO().deleteMembre(idMembre);
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<MembreDTO> listerMembres () throws DaoException
	{
		// on instance la liste des membre
		List<MembreDTO> listeMembresDTOs = new ArrayList<MembreDTO>();
		
		// on interroge le service listerMembres
		List<MembreDO> listeMembreDOs = getMembreDAO().listerMembres();
		
		// on convertit la liste<MembreDO> vers la liste<MembreDTO>
		for (MembreDO membreDO : listeMembreDOs) {
			// on instancie le membreDTO
			MembreDTO membreDTO = new MembreDTO();
		
			membreDTO.setIdMembre(membreDO.getIdMembre());
			membreDTO.setUsername(membreDO.getUsername());
			membreDTO.setPassword(membreDO.getPassword());
			membreDTO.setIdRole(membreDO.getRoleMembre().getIdRole());
			membreDTO.setStatus(membreDO.isStatus());
			membreDTO.setIdClient(membreDO.getClient().getIdClient());
			// on ajoute le membreDTO dans la liste<MembreDTO>
			listeMembresDTOs.add(membreDTO);
		}
		
		return listeMembresDTOs;
	}
}
