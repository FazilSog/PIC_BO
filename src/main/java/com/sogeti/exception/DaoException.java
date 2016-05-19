package com.sogeti.exception;

/**
 * 
 * @author moissa
 *
 */
public class DaoException extends Exception {

	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Crée une nouvelle instance d'DaoException
	 */
	public DaoException() {
		super();		
	}
	

	/**
	 * Crée une nouvelle instance d'DaoException
	 * @param message le message détaillant l'exception
	 */
	public DaoException(String message) {
		super(message);		
	}
	
	
	/**
	 * Crée une nouvelle instance d'DaoException
	 * @param cause l'exception à l'origine de cette exception
	 */
	public DaoException(Throwable cause) {
		super(cause);		
	}
	
	/**
	 * Crée une nouvelle instance d'DaoException
	 * @param message le message détaillant l'exception
	 * @param cause l'exception à l'origine de cette exception
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	
}
