package com.sogeti.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author syahiaou
 *
 */
@Entity
@Table(name = "CLIENTS")

public class ClientDO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCLIENT", nullable = false)
	private int idClient;
	
	@Column(name = "LIBELLECLIENT", nullable = false)
	private String nomClient;
	
	
	@OneToMany(targetEntity = MembreDO.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<MembreDO> membres = new HashSet<MembreDO>();
	
	
	@OneToMany(targetEntity = ProjetDO.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProjetDO> projets = new HashSet<ProjetDO>();
		
	
	/**
	 * @return the projets
	 */
	public Set<ProjetDO> getProjets() {
		return projets;
	}

	/**
	 * @param projets the projets to set
	 */
	public void setProjets(Set<ProjetDO> projets) {
		this.projets = projets;
	}

	/**
	 * @return the idClient
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * @return the membres
	 */

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	
	/**
	 * @return the nomClient
	 */
	public String getNomClient() {
		return nomClient;
	}

	/**
	 * @return the membres
	 */
	
	public Set<MembreDO> getMembres() {
		return membres;
	}

	/**
	 * @param membres the membres to set
	 */
	public void setMembres(Set<MembreDO> membres) {
		this.membres = membres;
	}
	
	/**
	 * @param nomClient the nomClient to set
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	

	

	/**
	 * 
	 */
	public ClientDO() {
	}

	/**
	 * @param idClient id du client
	 * @param nomClient nom du client
	 * @param membres l'objet MembreDO
	 */
	public ClientDO(final int idClient, final String nomClient, final Set<MembreDO> membres) {
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.membres = membres;
	}

	

	


}
