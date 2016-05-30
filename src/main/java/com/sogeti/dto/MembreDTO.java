/**
 * 
 */
package com.sogeti.dto;

import com.sogeti.fwk.GenericDTO;

/**
 * @author moissa
 *
 */
public class MembreDTO extends GenericDTO<MembreDTO> {

	private int idMembre;
	private String username;
	private String password;
	private boolean status;
	private int idClient;
	private int idRole;
	
	
	/**
	 * @return the idRole
	 */
	public int getIdRole() {
		return idRole;
	}
	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	/**
	 * @return the idClient
	 */
	public int getIdClient() {
		return idClient;
	}
	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	/**
	 * @return the idMembre
	 */
	public int getIdMembre() {
		return idMembre;
	}
	/**
	 * @param idMembre the idMembre to set
	 */
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Construteur MembreDTO
	 * @param idMembre l'id membre
	 * @param username le username
	 * @param password le password
	 * @param role le role
	 * @param status le status
	 * @param idClient l'id client
	 * @param idRole l'id Role
	 */
	public MembreDTO(int idMembre, String username, String password,
			boolean status, int idClient, int idRole) {
		this.idMembre = idMembre;
		this.username = username;
		this.password = password;
		this.status = status;
		this.idClient = idClient;
		this.idRole = idRole;
	}
	
	
	/**
	 * Construteur MembreDTO par défaut
	 */
	public MembreDTO() {
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MembreDTO [idMembre=" + idMembre + ", username=" + username
				+ ", password=" + password + ", status=" + status
				+ ", idClient=" + idClient + ", idRole=" + idRole + "]";
	}
		
	
}
