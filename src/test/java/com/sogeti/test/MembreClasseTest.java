package com.sogeti.test;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sogeti.bo.IMembreBO;
import com.sogeti.controller.MembreController;
import com.sogeti.dao.IClientDAO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dao.impl.MembreDAOImpl;
import com.sogeti.dto.MembreDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.model.ClientDO;
import com.sogeti.model.MembreDO;
import com.sogeti.model.RoleDO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
@Transactional
@Rollback(value=true)
public class MembreClasseTest {
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IClientDAO clientDAO;
	
	@Autowired
	private IMembreBO membreBO;
	
	@Autowired
	private MembreController membreController;
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(MembreClasseTest.class);
	
	@Rollback(value=true)
	@Test
	public void testSaveMembre() throws DaoException{
		
		//logger
				LOGGER.info("Début méthode  : testSaveMembre");
		int idMembre = 0;
		//On instancie l'objet DTO
		MembreDTO membreDTO = new MembreDTO();
		//On instancie la methode MembreDAOImpl
		MembreDAOImpl membreDAO = new MembreDAOImpl();
		//On set les infos du membre dans l'objet DTO
		membreDTO.setUsername("ddddddeddd");
		membreDTO.setIdClient(1);
		membreDTO.setIdRole(1);
		membreDTO.setPassword("1234");
		membreDTO.setStatus(true);
		
		try {
			
			MembreDO membreDO = membreDAO.findMembreByNameAndPass("ddddddeddd", "1234");
			
			if (membreDO != null){
				LOGGER.warn("MembreDO "+membreDO +" existe donc ne sera pas créé");
				throw new DaoException("Création échoué : Le Membre existe déjà.");
			} else {
				try {
					membreBO.create(membreDTO);
					membreDO = membreDAO.findMembreByNameAndPass("ddddddeddd", "1234");
					idMembre = membreDO.getIdMembre();
					
						if (idMembre == 0 ){
							LOGGER.warn("idMembre "+idMembre +"L'identifiant est obligatoire. ");
							throw new DaoException("L'identifiant est obligatoire. Valeur zéro est interdit.");
						} else {
							//On recuepre le membre via son Id
							MembreDO membreDOs = membreDAO.find(idMembre);
							//On recuepre le usernale du membre
							String username = membreDOs.getUsername();
							//On verifie l'egaliter du username
							assertEquals("ddddddeddd",username);
						}
					
				} catch (HibernateException ex) {
					// Critical errors : database unreachable, etc.
					LOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete findMembreById().");
					throw new DaoException("Connexion échoué : le test creation n'est pas concluant");
				}
			}
		 } finally {
			 LOGGER.info("Fin méthode  : testSaveMembre");
		}
	}
	@Rollback(value=true)
	@Test
	public void testUpdateMembre() throws DaoException{

		//logger
		LOGGER.info("Début méthode  : testUpdateMembre");
		//On instancie l'objet DTO
		MembreDTO membreDTO = new MembreDTO();
		
		//On set les nouvelles infos du membre dans l'objet DTO
		membreDTO.setUsername("lenew");
		membreDTO.setIdClient(1);
		membreDTO.setIdRole(1);
		membreDTO.setPassword("12345");
		membreDTO.setStatus(true);
		membreDTO.setIdMembre(13);
		//On instancie la methode MembreDAOImpl
		MembreDAOImpl membreDAO = new MembreDAOImpl();

		try {
			//On recupere le membre
			MembreDO membreDO = membreDAO.find(membreDTO.getIdMembre());
			
			if (membreDO == null){
				LOGGER.warn("MembreDO "+membreDO +" Le membre n'existe pas");
				throw new DaoException("Modification échoué : Le membre n'existe pas.");
			} else {
					// on recupere l'objet RoleDO via son id
					RoleDO roleDO = roleDAO.find(membreDTO.getIdRole());
					//On recupere l'objet ClientDO via son id
					ClientDO clientDO = clientDAO.find(membreDTO.getIdClient());
					
					//On set les nouvelles infos du membre dans l'objet DTO
					membreDO.setIdMembre(membreDTO.getIdMembre());
					membreDO.setClient(clientDO);
					membreDO.setRoleMembre(roleDO);
					membreDO.setPassword(membreDTO.getPassword());
					membreDO.setUsername(membreDTO.getUsername());
					membreDO.setStatus(true);
					
					//On interoge le service update
					membreBO.update(membreDTO);
					//On recupere le username du membre
					String username = membreDO.getUsername();
					//On verifie l'egaliter du username
					assertEquals("lenew",username);
				
			}
	 } catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " + ex.getMessage() + " on complete findMembreById().");
				throw new DaoException("Modification échoué: le membre n'est pas connu");
		}
		 LOGGER.info("Fin méthode  : testUpdateMembre");
	}
	
	@Rollback(value=true)
	@SuppressWarnings("unused")
	@Test
	public void testListeMembre() throws DaoException{
		
		Iterable<MembreDTO> membreDTO =	membreBO.listeObjects();
	
		int size = 0;
		 for (MembreDTO membreDTO2 : membreDTO) {
			 size ++;
			
		}
			assertEquals(21, size);
		
	}

}
