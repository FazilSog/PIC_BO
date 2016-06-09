package com.sogeti.exception;

/**
 * 
 * @author moissa
 *
 */
public class DaoException extends Exception {

	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Cr�e une nouvelle instance d'DaoException.
	 */
	public DaoException() {
		super();		
	}
	

	/**
	 * Cr�e une nouvelle instance d'DaoException.
	 * @param message le message d�taillant l'exception
	 */
	public DaoException(final String message) {
		super(message);		
	}
	
	
	/**
	 * Cr�e une nouvelle instance d'DaoException.
	 * @param cause l'exception � l'origine de cette exception
	 */
	public DaoException(final Throwable cause) {
		super(cause);		
	}
	
	/**
	 * Cr�e une nouvelle instance d'DaoException.
	 * @param message le message d�taillant l'exception
	 * @param cause l'exception � l'origine de cette exception
	 */
	public DaoException(final String message, final Throwable cause) {
		super(message, cause);		
	}
	
	
}
