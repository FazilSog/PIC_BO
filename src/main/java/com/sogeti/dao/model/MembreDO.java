package com.sogeti.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author syahiaou
 *
 */
@Entity
@Table(name = "MEMBERS")
public class MembreDO {

	@Id
	@GeneratedValue
	@Column(name = "IDMEMBRE", nullable = false)
	private int idMembre;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "STATUS", nullable = false)
	private boolean status;

	@Column(name = "TOKEN", nullable = true)
	private String token;

	@OneToOne(targetEntity = ClientDO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCLIENT")
	private ClientDO client;

	@OneToOne(targetEntity = RoleDO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDROLE")
	private RoleDO roleMembre;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="membre")
	private Set<RoleProjetDO> roleProjet = new HashSet<RoleProjetDO>();
	
	/**
	 * @return the roleProjet
	 */
	public Set<RoleProjetDO> getRoleProjet() {
		return roleProjet;
	}

	/**
	 * @param roleProjet the roleProjet to set
	 */
	public void setRoleProjet(Set<RoleProjetDO> roleProjet) {
		this.roleProjet = roleProjet;
	}

	/**
	 * @return the idMembre
	 */
	public int getIdMembre() {
		return idMembre;
	}

	/**
	 * @param idMembre
	 *            the idMembre to set
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
	 * @param username
	 *            the username to set
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
	 * @param password
	 *            the password to set
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
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the client
	 */
	public ClientDO getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(ClientDO client) {
		this.client = client;
	}

	/**
	 * @return the roleMembre
	 */
	public RoleDO getRoleMembre() {
		return roleMembre;
	}

	/**
	 * @param roleMembre the roleMembre to set
	 */
	public void setRoleMembre(RoleDO roleMembre) {
		this.roleMembre = roleMembre;
	}
	

	/**
	 * @param idMembre id du membre
	 * @param username username du membre
	 * @param password mot de passe du membre
	 * @param status  status du membre
	 * @param token token du membre
	 * @param client l'objet clientDO
	 * @param roleMembre l'objet roleDO
	 */
	public MembreDO(final int idMembre, final String username, final String password,
			final boolean status, final String token, final ClientDO client, final RoleDO roleMembre) {
		this.idMembre = idMembre;
		this.username = username;
		this.password = password;
		this.status = status;
		this.token = token;
		this.client = client;
		this.roleMembre = roleMembre;
	}
	
	/**
	 * 
	 */
	public MembreDO() {

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MembreDO [idMembre=" + idMembre + ", username=" + username
				+ ", password=" + password + ", status=" + status + ", token="
				+ token + ", client=" + client + ", roleMembre=" + roleMembre
				+ ", roleProjet=" + roleProjet + "]";
	}
	


}