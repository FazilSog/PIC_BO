package com.sogeti.test;

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
import com.sogeti.exception.DaoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
@Transactional
@Rollback(value=true)
public class AuthentificationTest {
	
	@Autowired
	private IMembreBO membreBO;
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(MembreTest.class);
	
	@Rollback(value=true)
	@Test
	public void Authentification () throws DaoException {
		
		//logger
		LOGGER.info("Début méthode  : testSaveMembre");
		String username = "sogeti";
		String password = "1234";
		
		
		
		
	}

}
