package com.sogeti.dto;

import com.sogeti.fwk.GenericDTO;


/**
 * 
 * @author syahiaou
 *
 */

public class ProjetDTO extends GenericDTO<ProjetDTO> {
	
	private int idProjet;
	private String nomProjet;
	private String credential;
	private String frequence;
	private String branche;
	private String description;
	private char status;
	private boolean actif;
	private String url;
	private int idClient;
	private int idMembre;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the idProjet
	 */
	public int getIdProjet() {
		return idProjet;
	}
	
	/**
	 * @param idProjet the idProjet to set
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
	 * @param nomProjet the nomProjet to set
	 */
	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}
	
	/**
	 * @return the credential
	 */
	public String getCredential() {
		return credential;
	}
	
	/**
	 * @param credential the credential to set
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
	 * @param frequence the frequence to set
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
	 * @param branche the branche to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the status
	 */
	public char getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(char status) {
		this.status = status;
	}
	
	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}
	
	/**
	 * @param actif the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	

	/**
	 * Constructeur par défaut.
	 */
	public ProjetDTO() {
	}
	
	/**
	 * @param idProjet l'id projet
	 * @param nomProjet le nom du projet
	 * @param credential le credential
	 * @param frequence la fréquence
	 * @param branche la branche
	 * @param description la description
	 * @param status le status
	 * @param actif (true ou false)
	 * @param url l'url
	 * @param idClient l'id client
	 * @param idMembre
	 * @param idRole
	 */
	public ProjetDTO(final int idProjet, final String nomProjet, final String credential,
			final String frequence, final String branche, final String description, final char status,
			final boolean actif, final String url, final int idClient,
			final int idMembre, final int idRole) {
		super();
		this.idProjet = idProjet;
		this.nomProjet = nomProjet;
		this.credential = credential;
		this.frequence = frequence;
		this.branche = branche;
		this.description = description;
		this.status = status;
		this.actif = actif;
		this.url = url;
		this.idClient = idClient;
		this.idMembre = idMembre;
		this.idRole = idRole;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjetDTO [idProjet=" + idProjet + ", nomProjet=" + nomProjet
				+ ", credential=" + credential + ", frequence=" + frequence
				+ ", branche=" + branche + ", description=" + description
				+ ", status=" + status + ", actif=" + actif + ", url=" + url
				+ ", idClient=" + idClient + ", idMembre=" + idMembre
				+ ", idRole=" + idRole + "]";
	}
	
	
}
