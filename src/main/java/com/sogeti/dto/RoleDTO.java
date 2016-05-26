package com.sogeti.dto;


/**
 * 
 * @author moissa
 *
 */
public class RoleDTO {
	
	private int idRole;
	private String codeRole;
	private String libelleRole;
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
	 * @param idRole l'id du role
	 * @param codeRole le code du role
	 * @param libelleRole le libelle du role
	 */
	public RoleDTO(int idRole, String codeRole, String libelleRole) {
		this.idRole = idRole;
		this.codeRole = codeRole;
		this.libelleRole = libelleRole;
	}
	
	/**
	 * constructeur par défaut
	 */
	public RoleDTO() {
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleDTO [idRole=" + idRole + ", codeRole=" + codeRole
				+ ", libelleRole=" + libelleRole + "]";
	}
	
	
}
