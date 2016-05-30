package com.sogeti.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sogeti.fwk.GenericDO;

/**
 * 
 * @author syahiaou
 *
 */
@Entity
@Table(name = "ROLES")
public class RoleDO extends GenericDO<RoleDO> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDROLE", nullable = false)
	private int idRole;
	
	@Column(name = "LIBELLEROLE", nullable = false)
	private String libelleRole;
	
	@Column(name = "CODEROLE", nullable = false)
	private String codeRole;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private Set<RoleProjetDO> lRoleProjet = new HashSet<RoleProjetDO>();
	
	@OneToMany(mappedBy = "roleMembre", fetch = FetchType.LAZY)
	private Set<MembreDO> lMembre = new HashSet<MembreDO>();
	


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
	 * @return the libelleRole
	 */
	public String getLibelleRole() {
		return libelleRole;
	}

	/**
	 * @param libelleRole the libelleRole to set
	 */
	public void setLibelleRole(String libelleRole) {
		this.libelleRole = libelleRole;
	}

	/**
	 * @return the codeRole
	 */
	public String getCodeRole() {
		return codeRole;
	}

	/**
	 * @param codeRole the codeRole to set
	 */
	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}

	/**
	 * @param idRole id du role
	 * @param libelleRole libelle du role
	 * @param codeRole code du role
	 */
	public RoleDO(final int idRole, final String libelleRole, final String codeRole) {

		this.idRole = idRole;
		this.libelleRole = libelleRole;
		this.codeRole = codeRole;

	}

	/**
	 * 
	 */
	public RoleDO() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleDO [idRole=" + idRole + ", libelleRole=" + libelleRole
				+ ", codeRole=" + codeRole + ", lRoleProjet=" + lRoleProjet
				+ ", lMembre=" + lMembre + "]";
	}
	
}
