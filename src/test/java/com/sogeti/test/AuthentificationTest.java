package com.sogeti.test;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sogeti.bo.IMembreBO;
import com.sogeti.dao.IMembreDAO;
import com.sogeti.dto.AuthentificationDTO;
import com.sogeti.exception.DaoException;

/**
 * 
 * @author Syahiaou
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@ContextConfiguration(locations = "file:WebContent/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
@Transactional
@Rollback(value = false)
public class AuthentificationTest {
	
	@Autowired
	private IMembreBO membreBO;
	
	@Autowired
	private IMembreDAO membreDAO;
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(MembreTest.class);
		
		//On cree une instance de l'objet DTO
		AuthentificationDTO tokenGenerate = new AuthentificationDTO();
		/**
		 * username nom.
		 */
		private String username = "sogeti";
		/**
		 * password pwd.
		 */
		private String password = "1234";
		
	
	@Test(expected = DaoException.class)
	public final void testAuthenticatePasswordException() throws DaoException {
		
		//logger
		LOGGER.info("Début méthode  : testAuthenticatePasswordException");
		
	    password = null;
	    tokenGenerate = membreBO.authentification(username, password);
	    assertNotNull(tokenGenerate);
	    
	    LOGGER.info("Fin méthode  : testAuthenticatePasswordException");
	}

	@Test(expected = DaoException.class)
	public final void testAuthenticateUsernameException() throws DaoException {
		
		//Logger
		LOGGER.info("Début méthode  : testAuthenticateUsernameException");
		
	    username = null;
	    tokenGenerate = membreBO.authentification(username, password);
	    assertNotNull(tokenGenerate);
	    
	    LOGGER.info("Fin méthode  : testAuthenticateUsernameException");
	}

	@Test
	public final void testAuthenticateResult() throws DaoException {
		
		//Logger
		LOGGER.info("Début méthode  : testAuthenticateResult");
		
		//On appel le service authentification et on recupere le resultat dans l'objet DTO
		tokenGenerate = membreBO.authentification(username, password);
	    assertNotNull(tokenGenerate);
	  	    
	    LOGGER.info("Fin méthode  : testAuthenticateResult");
	}

}
