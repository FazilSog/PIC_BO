package com.sogeti.dto;

/**
 * 
 * @author moissa
 *
 */
public class AuthentificationDTO {
	

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

	
	public AuthentificationDTO(String tokenMembre) {
		this.tokenMembre = tokenMembre;
	}


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
