package com.sogeti.dto;

import com.sogeti.fwk.GenericDTO;

/**
 * 
 * @author moissa
 *
 */
public class AuthentificationDTO extends GenericDTO<AuthentificationDTO> {
	

	private String tokenMembre;
	
	
	/**
	 * @return the tokenMembre
	 */
	public String getTokenMembre() {
		return tokenMembre;
	}

	/**
	 * @param tokenMembre the tokenMembre to set
	 */
	public void setTokenMembre(String tokenMembre) {
		this.tokenMembre = tokenMembre;
	}

	/**
	 * contructeur AuthentificationDTO.
	 * @param tokenMembre le token du membre
	 */
	public AuthentificationDTO(final String tokenMembre) {
		this.tokenMembre = tokenMembre;
	}

	/**
	 * Constructeur par défaut.
	 */
	public AuthentificationDTO() {
			
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthentificationDTO [tokenMembre=" + tokenMembre + "]";
	}
	
}
