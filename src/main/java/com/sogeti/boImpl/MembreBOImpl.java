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
		MembreDO lMembreDO = getMembreDAO().AuthentifierMembre(pUsername, pPassword);

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
	public MembreDTO addMembre (final String pUsername, final String pPassword, final boolean pStatus,
			final int pIdClient, final int pIdRole) throws DaoException
	{
		// on instancie l'objet MembreDTO
		MembreDTO lMembreDTO = new MembreDTO();
		
		// Recuperer le membre via son Id
		ClientDO lClientDO = getClientDAO().findClientById(pIdClient);
		
		// Recuperer le membre via son Id
		RoleDO lRoleDO = getRoleDAO().findRoleById(pIdRole);
		
		// on interroge le service addMembre
		int lIdMembre = getMembreDAO().addMembre(pUsername, pPassword, pStatus, lClientDO, lRoleDO);
		// on remonte seulement l'id
		lMembreDTO.setIdMembre(lIdMembre);
		
		return lMembreDTO;
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void updateMembre (final int pIdMembre, final String pUsername, final String pPassword,
			final boolean pStatus, final int pIdClient, final int pIdRole) throws DaoException
	{
		
		ClientDO lClientDO = getClientDAO().findClientById(pIdClient);
		RoleDO lRoleDO = getRoleDAO().findRoleById(pIdRole);
		
		// on interroge le service updateMembre
		getMembreDAO().updateMembre(pIdMembre, pUsername, pPassword, pStatus, lClientDO, lRoleDO);		
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public void deleteMembre (final int pIdMembre) throws DaoException
	{
		// on interroge le service deleteMembre
		getMembreDAO().deleteMembre(pIdMembre);
	}
	
	/**
	 * {@inheritDoc} 
	 */
	public List<MembreDTO> listerMembres () throws DaoException
	{
		// on instance la liste des membre
		List<MembreDTO> lListeMembresDTOs = new ArrayList<MembreDTO>();
		
		// on interroge le service listerMembres
		List<MembreDO> lListeMembreDOs = getMembreDAO().listerMembres();
		
		// on convertit la liste<MembreDO> vers la liste<MembreDTO>
		for (MembreDO pMembreDO : lListeMembreDOs) {
			// on instancie le membreDTO
			MembreDTO lMembreDTO = new MembreDTO();
		
			lMembreDTO.setIdMembre(pMembreDO.getIdMembre());
			lMembreDTO.setUsername(pMembreDO.getUsername());
			lMembreDTO.setPassword(pMembreDO.getPassword());
			lMembreDTO.setIdRole(pMembreDO.getRoleMembre().getIdRole());
			lMembreDTO.setStatus(pMembreDO.isStatus());
			lMembreDTO.setIdClient(pMembreDO.getClient().getIdClient());
			// on ajoute le membreDTO dans la liste<MembreDTO>
			lListeMembresDTOs.add(lMembreDTO);
		}
		
		return lListeMembresDTOs;
	}
}
