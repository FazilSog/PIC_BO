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

/**
 * 
 * @author syahiaou
 *
 */

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
	public AuthentificationDTO Authentification(final String pUsername, final String pPassword) throws DaoException {
		// on instance l'objet AuthentificationDTO
		AuthentificationDTO lAuthentification = new AuthentificationDTO();
		
		// on interroge le service AuthentifierMembre
		MembreDO lMembreDO = getMembreDAO().authentifierMembre(pUsername, pPassword);

		if (lMembreDO != null) {
		
			// on génère le token
			String tokenMembre = Token.generateToken(lMembreDO.getIdMembre(), lMembreDO.getClient().getIdClient());
			// on affecte la valeur du token au membre
			lAuthentification.setTokenMembre(tokenMembre);
			lMembreDO.setToken(tokenMembre);
			// on met à jour le membre
			lMembreDO = getMembreDAO().setMembre(lMembreDO);
		}
		return lAuthentification;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public MembreDTO addMembre (final MembreDTO pMembreDTO) throws DaoException
	{
		// on instancie l'objet MembreDTO
		final MembreDTO membreDTO = pMembreDTO;
		
		// Recuperer le membre via son Id
		final ClientDO clientDO = getClientDAO().findClientById(membreDTO.getIdClient());
		
		// Recuperer le membre via son Id
		final RoleDO roleDO = getRoleDAO().findRoleById(membreDTO.getIdRole());
		
		// on instancie le membreDO
		final MembreDO membreDO = new MembreDO();
		membreDO.setUsername(membreDTO.getUsername());
		membreDO.setPassword(membreDTO.getPassword());
		membreDO.setStatus(membreDTO.isStatus());
		membreDO.setClient(clientDO);
		membreDO.setRoleMembre(roleDO);
		
		// on interroge le service addMembre
		int idMembre = getMembreDAO().addMembre(membreDO);
		// on remonte seulement l'id
		membreDTO.setIdMembre(idMembre);
		
		return membreDTO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void updateMembre (final MembreDTO pMembreDTO) throws DaoException
	{
		// on recupere l'objet ClientDO via son id
		final ClientDO clientDO = getClientDAO().findClientById(pMembreDTO.getIdClient());
		// on recupere l'objet RoleDO via son id
		final RoleDO roleDO = getRoleDAO().findRoleById(pMembreDTO.getIdRole());
		
		// On recupere le membre via son Id pour vérifier s'il existe
		final MembreDO membreDO = getMembreDAO().findMembreById(pMembreDTO.getIdMembre());
				
		// on met à jour le membreDO
		membreDO.setUsername(pMembreDTO.getUsername());
		membreDO.setPassword(pMembreDTO.getPassword());
		membreDO.setStatus(pMembreDTO.isStatus());
		membreDO.setClient(clientDO);
		membreDO.setRoleMembre(roleDO);
		
		// on interroge le service updateMembre
		getMembreDAO().updateMembre(membreDO);		
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void deleteMembre (final int pIdMembre) throws DaoException
	{
		// On recupere le membre via son Id pour vérifier s'il existe
		final MembreDO membreDO = getMembreDAO().findMembreById(pIdMembre);
				
		// on interroge le service deleteMembre
		getMembreDAO().deleteMembre(membreDO);
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<MembreDTO> listerMembres () throws DaoException
	{
		// on instance la liste des membres DTO
		final List<MembreDTO> lListeMembresDTOs = new ArrayList<MembreDTO>();
		
		// on interroge le service listerMembres
		final List<MembreDO> lListeMembreDOs = getMembreDAO().listerMembres();
		
		// on convertit la liste<MembreDO> vers la liste<MembreDTO>
		for (MembreDO membreDO : lListeMembreDOs) {
			// on instancie le membreDTO
			MembreDTO membreDTO = new MembreDTO();
		
			membreDTO.setIdMembre(membreDO.getIdMembre());
			membreDTO.setUsername(membreDO.getUsername());
			membreDTO.setPassword(membreDO.getPassword());
			membreDTO.setIdRole(membreDO.getRoleMembre().getIdRole());
			membreDTO.setStatus(membreDO.isStatus());
			membreDTO.setIdClient(membreDO.getClient().getIdClient());
			
			// on ajoute le membreDTO dans la liste<MembreDTO>
			lListeMembresDTOs.add(membreDTO);
		}
		
		return lListeMembresDTOs;
	}
}
