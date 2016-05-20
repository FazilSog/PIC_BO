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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author moissa
 *
 */

@Entity
@Table(name = "PROJETS")
public class ProjetDO {

	@Id
	@GeneratedValue
	@Column(name = "IDPROJET", nullable = false)
	private int idProjet;

	@Column(name = "URL", nullable = false)
	private String url;
	
	@Column(name = "NOMPROJET", nullable = false)
	private String nomProjet;

	@Column(name = "CREDENTIAL", nullable = false)
	private String credential;

	@Column(name = "FREQUENCE", nullable = false)
	private String frequence;

	@Column(name = "BRANCHE", nullable = false)
	private String branche;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "ACTIF", nullable = false)
	private boolean actif;

	@Column(name = "STATUS", nullable = false)
	private char status;

	@ManyToOne(targetEntity = ClientDO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCLIENT")
	private ClientDO client;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projet")
	private Set<RoleProjetDO> roleProjet = new HashSet<RoleProjetDO>();

	
	/**
	 * @return the client
	 */
	public ClientDO getClient() {
		return client;
	}

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
	 * @param client
	 *            the client to set
	 */
	public void setClient(ClientDO client) {
		this.client = client;
	}

	/**
	 * @return the idProjet
	 */
	public int getIdProjet() {
		return idProjet;
	}

	/**
	 * @param idProjet
	 *            the idProjet to set
	 */
	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}

	/**
	 * @return the nomProjet
	 */
	public String getNomProjet() {
		return nomProjet;
	}

	/**
	 * @param nomProjet
	 *            the nomProjet to set
	 */
	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the credential
	 */
	public String getCredential() {
		return credential;
	}

	/**
	 * @param credential
	 *            the credential to set
	 */
	public void setCredential(String credential) {
		this.credential = credential;
	}

	/**
	 * @return the frequence
	 */
	public String getFrequence() {
		return frequence;
	}

	/**
	 * @param frequence
	 *            the frequence to set
	 */
	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	/**
	 * @return the branche
	 */
	public String getBranche() {
		return branche;
	}

	/**
	 * @param branche
	 *            the branche to set
	 */
	public void setBranche(String branche) {
		this.branche = branche;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * @param actif
	 *            the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

	/**
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}


	/**
	 * @param idProjet id du projet 
	 * @param url  url du projet
	 * @param nomProjet nom du projet
	 * @param credential credential du projet 
	 * @param frequence frequence du projet
	 * @param branche branche du projet
	 * @param description description du projet
	 * @param actif actif du projet
	 * @param status status du projet
	 * @param client L'objet ClientDO
	 * @param roleProjet L'objet RoleProjetDO
	 */
	public ProjetDO(final int idProjet, final String url, final String nomProjet,
			final String credential, final String frequence, final String branche,
			final String description, final boolean actif, final char status, final ClientDO client,
			final Set<RoleProjetDO> roleProjet) {
		
		this.idProjet = idProjet;
		this.url = url;
		this.nomProjet = nomProjet;
		this.credential = credential;
		this.frequence = frequence;
		this.branche = branche;
		this.description = description;
		this.actif = actif;
		this.status = status;
		this.client = client;
		this.roleProjet = roleProjet;
	}

	/**
	 * 
	 */
	public ProjetDO() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjetDO [idProjet=" + idProjet + ", url=" + url
				+ ", nomProjet=" + nomProjet + ", credential=" + credential
				+ ", frequence=" + frequence + ", branche=" + branche
				+ ", description=" + description + ", actif=" + actif
				+ ", status=" + status + ", client=" + client + ", roleProjet="
				+ roleProjet + "]";
	}

	
}
