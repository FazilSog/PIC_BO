package com.sogeti.dto;

import com.sogeti.dao.model.ClientDO;

public class ProjetDTO {
	
	private int idProjet;
	private String nomProjet;
	private String credential;
	private String frequence;
	private String branche;
	private String description;
	private char status;
	private boolean actif;
	private String url;
	private ClientDO client;
	
	
	
	
	
	/**
	 * @return the client
	 */
	public ClientDO getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(ClientDO client) {
		this.client = client;
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
	 * @param idProjet
	 * @param nomProjet
	 * @param credential
	 * @param frequance
	 * @param branche
	 * @param description
	 * @param status
	 * @param actif
	 */
	public ProjetDTO(int idProjet, String nomProjet, String credential,
			String frequence, String branche, String description, char status,
			boolean actif, String url, ClientDO client) {
		this.idProjet = idProjet;
		this.nomProjet = nomProjet;
		this.credential = credential;
		this.frequence = frequence;
		this.branche = branche;
		this.description = description;
		this.status = status;
		this.actif = actif;
		this.url = url;
		this.client = client;
	}
	/**
	 * 
	 */
	public ProjetDTO() {
	}

	
}
