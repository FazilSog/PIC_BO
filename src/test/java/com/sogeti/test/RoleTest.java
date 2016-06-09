package com.sogeti.test;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sogeti.dao.IRoleDAO;
import com.sogeti.exception.DaoException;
import com.sogeti.model.RoleDO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/dispatcher-servlet.xml" )
@WebAppConfiguration
@Transactional
@Rollback(value=true)
public class RoleTest {
	
	@Autowired
	private IRoleDAO roleDAO;
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(ProjetTest.class);
	
	@Rollback(value=true)
	@SuppressWarnings("unused")
	@Test
	public void testListeRole() throws DaoException{
		
		//logger
		LOGGER.info("Début méthode  : testListeRole");
		
		//On affiche le contenu de la liste
		Iterable<RoleDO> roleDO = roleDAO.listeObjects();
		
		int size = 0;
		for (RoleDO roleDO2 : roleDO ) {
			 size ++;
			
		}
			assertEquals(3, size);
			
			LOGGER.info("Fin méthode  : testListeRole");
	}

}
