package com.sogeti.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author syahiaou
 *
 */
@Entity
@Table(name = "ROLESPROJETS")

public class RoleProjetDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDROLEPROJET", nullable = false)
	private int idRoleProjet;

	@ManyToOne(targetEntity = MembreDO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMEMBRE")
	private MembreDO membre;

	@ManyToOne(targetEntity = ProjetDO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPROJET")
	private ProjetDO projet;

	@ManyToOne(targetEntity = RoleDO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDROLE")
	private RoleDO role;

	/**
	 * 
	 */
	public RoleProjetDO() {

	}

	/**
	 * @param idRoleProjet id du RoleProjet
	 * @param membre l'objet MembreDO
	 * @param projet l'objet ProjetDO
	 * @param role l'objet RoleDO
	 */
	public RoleProjetDO(final int idRoleProjet, final MembreDO membre, final ProjetDO projet,
			final RoleDO role) {
		this.idRoleProjet = idRoleProjet;
		this.membre = membre;
		this.projet = projet;
		this.role = role;
	}

	/**
	 * @return the idRoleProjet
	 */
	public int getIdRoleProjet() {
		return idRoleProjet;
	}

	/**
	 * @param idRoleProjet
	 *            the idRoleProjet to set
	 */
	public void setIdRoleProjet(int idRoleProjet) {
		this.idRoleProjet = idRoleProjet;
	}

	/**
	 * @return the membre
	 */
	public MembreDO getMembre() {
		return membre;
	}

	/**
	 * @param membre
	 *            the membre to set
	 */
	public void setMembre(MembreDO membre) {
		this.membre = membre;
	}

	/**
	 * @return the projet
	 */
	public ProjetDO getProjet() {
		return projet;
	}

	/**
	 * @param projet
	 *            the projet to set
	 */
	public void setProjet(ProjetDO projet) {
		this.projet = projet;
	}

	/**
	 * @return the role
	 */
	public RoleDO getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(RoleDO role) {
		this.role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleProjetDO [idRoleProjet=" + idRoleProjet + ", membre="
				+ membre + ", projet=" + projet + ", role=" + role + "]";
	}
	
}
