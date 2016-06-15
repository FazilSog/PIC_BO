package com.sogeti.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import com.sogeti.dao.IClientDAO;
import com.sogeti.dao.IMembreDAO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dto.MembreDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.model.ClientDO;
import com.sogeti.model.MembreDO;
import com.sogeti.model.RoleDO;
/**
 * 
 * @author syahiaou
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
@Transactional
@Rollback(value = true)
public class MembreTest {
	
	@Autowired
	private IMembreDAO membreDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IClientDAO clientDAO;
	
	@Autowired
	private IMembreBO membreBO;
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(MembreTest.class);
	
	@Rollback(value = true)
	@Test
	public void testCreateMembre() throws DaoException {
		
		//logger
		LOGGER.info("Début méthode  : testSaveMembre");
		int idMembre = 0;
		//On instancie l'objet DO
		MembreDO membreDO = new MembreDO();
		//On instancie l'objet DTO
		MembreDTO membreDTO = new MembreDTO();
		//On set les infos du membre dans l'objet DTO
		membreDTO.setUsername("lenovo");
		membreDTO.setIdClient(1);
		membreDTO.setIdRole(1);
		membreDTO.setPassword("1234");
		membreDTO.setStatus(true);
		
		try {
			
			 membreDO = membreDAO.findMembreByNameAndPass("lenovo", "1234");
			
			if (membreDO != null) {
				fail("Création échoué : Le Membre existe déjà.");
			} else {
				try {
					membreBO.create(membreDTO);
					membreDO = membreDAO.findMembreByNameAndPass("lenovo", "1234");
					idMembre = membreDO.getIdMembre();
					
						if (idMembre == 0) {
							LOGGER.warn("idMembre " + idMembre  + "L'identifiant est obligatoire. ");
							fail("L'identifiant est obligatoire. Valeur zéro est interdit.");
						} else {
							//On recuepre le membre via son Id
							 membreDO = membreDAO.find(idMembre);
							//On recuepre le usernale du membre
							String username = membreDO.getUsername();
							//On verifie l'egaliter du username
							assertEquals("lenovo", username);
						}
					
				  } catch (DaoException ex) {
					    assert (ex.getMessage().contains("Création échoué : Le Membre existe déjà."));
					    assert (ex.getMessage().contains("L'identifiant est obligatoire. Valeur zéro est interdit."));
				  }
			}
		 } finally {
			 LOGGER.info("Fin méthode  : testSaveMembre");
		 }
	}
	@Rollback(value=true)
	@Test
	public void testUpdateMembre() throws DaoException {

		//logger
		LOGGER.info("Début méthode  : testUpdateMembre");
		int idClient = 1;
		int idRole = 1;
		int idMembre = 19;
	

		try {
			//On recupere le membre
			MembreDO membreDO = membreDAO.find(idMembre);
			
			if (membreDO == null) {
				LOGGER.warn("MembreDO " + membreDO  + " Le membre n'existe pas");
				fail("Modification échoué : Le membre n'existe pas.");
			} else {
					// on recupere l'objet RoleDO via son id
					RoleDO roleDO = roleDAO.find(idRole);
					//On recupere l'objet ClientDO via son id
					ClientDO clientDO = clientDAO.find(idClient);
					
					//On set les nouvelles infos du membre dans l'objet DO
					membreDO.setIdMembre(idMembre);
					membreDO.setClient(clientDO);
					membreDO.setRoleMembre(roleDO);
					membreDO.setPassword("12345");
					membreDO.setUsername("lenew");
					membreDO.setStatus(true);
					
					//On interoge le service update
					membreDAO.update(membreDO);
					//On recupere le username du membre
					String username = membreDO.getUsername();
					//On verifie l'egaliter du username
					assertEquals("lenew", username);
				
			}
		} catch (DaoException ex) {
		    assert (ex.getMessage().contains("Modification échoué : Le membre n'existe pas."));
		}
		 LOGGER.info("Fin méthode  : testUpdateMembre");
	}
	
	@Rollback(value = true)
	@Test
	public void testDeleteMembre() throws DaoException {
		
		//logger
		 LOGGER.info("Début méthode  : testDeleteMembre");
		
		int idMembre = 19;
		//On recupere le membre via son Id
		MembreDO membreDO = membreDAO.find(idMembre);
		//On appel le service delete membre
		membreDAO.delete(membreDO);
		//Le test passe si l’exception spécifiée dans le try-catch est levée, sinon il échoue à l’exécution de la méthode fail()
		 try {
			 @SuppressWarnings("unused")
			MembreDO membreDOs = membreDAO.find(idMembre);
			    fail("Devrait leve une expetion vu que le membre n'existe pas");
		  } catch (DaoException ex) {
			    assert (ex.getMessage().contains("Le Membre n'est pas connu."));
	      }
		 LOGGER.info("Fin méthode  : deleteListeMembre");
	}
	
	@Rollback(value = true)
	@SuppressWarnings("unused")
	@Test
	public void testListeObjectMembre() throws DaoException {
		
		//logger
		LOGGER.info("Début méthode  : testListeMembre");
		
		//On affiche le contenu de la liste
		Iterable<MembreDTO> membreDTO =	membreBO.listeObjects();
	
		int size = 0;
		 for (MembreDTO membreDTO2 : membreDTO) {
			 size++;
			
		}
			assertEquals(19, size);
			
			LOGGER.info("Fin méthode  : testListeMembre");
	}

}
