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

import com.sogeti.bo.IProjetBO;
import com.sogeti.dao.IClientDAO;
import com.sogeti.dao.IMembreDAO;
import com.sogeti.dao.IProjetDAO;
import com.sogeti.dao.impl.ProjetDAOImpl;
import com.sogeti.dto.ProjetDTO;
import com.sogeti.exception.DaoException;
import com.sogeti.model.ClientDO;
import com.sogeti.model.MembreDO;
import com.sogeti.model.ProjetDO;
import com.sogeti.model.RoleProjetDO;

/**
 * 
 * @author syahiaou
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@WebAppConfiguration
@Transactional
@Rollback(value=true)
public class ProjetTest {

	
	@Autowired
	private IProjetDAO projetDAO;
	
	@Autowired
	private IProjetBO projetBO;
	
	@Autowired
	private IClientDAO clientDAO;
	
	@Autowired
	private IMembreDAO membreDAO;
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(ProjetTest.class);
	
	@Rollback(value=true)
	@Test
	public void testCreateProjet() throws DaoException{
		
		//logger
		LOGGER.info("Début méthode  : testCreateProjet");
		int idProjet = 0;
		//On instancie de le ProjetDAOImpl
		ProjetDAOImpl projetDAO = new ProjetDAOImpl();
		//On instancie l'objet DO
		ProjetDO projetDO = new ProjetDO();
		
		//On instancie l'objet DTO
		ProjetDTO projetDTO = new ProjetDTO();
		//On set les nouvelles infos dans l'objet DTO
		projetDTO.setBranche("brancheA");
		projetDTO.setCredential("cred");
		projetDTO.setDescription("BonProjet");
		projetDTO.setFrequence("frequence");
		projetDTO.setActif(true);
		projetDTO.setNomProjet("nomProjet");
		projetDTO.setUrl("url");
		projetDTO.setStatus('V');
		projetDTO.setIdClient(1);
		
		try {
			//On recupere le projet
			 projetDO = projetDAO.findProjet("nomProjet", "url", "brancheA");
			
			 if (projetDO != null){
					LOGGER.warn("projetDO "+projetDO +" existe donc ne sera pas créé");
					fail("Création échoué : Le projet existe déjà.");
				} else {
					try {
						//On appel le servcie create
						projetBO.create(projetDTO);
						//On recupere l'objet Projet via le nom, url, branche 
						projetDO = projetDAO.findProjet("nomProjet", "url", "brancheA");
						//On recupere l'id du projet
						idProjet = projetDO.getIdProjet();
						
							if (idProjet == 0 ){
								LOGGER.warn("idProjet "+idProjet +"L'identifiant est obligatoire. ");
								fail("L'identifiant est obligatoire. Valeur zéro est interdit.");
							} else {
								//On recuepre le projet via son Id
								projetDO = projetDAO.find(idProjet);
								//On recuepre le nom du projet
								String nomProjet = projetDO.getNomProjet();
								//On verifie l'egaliter du username
								assertEquals("nomProjet",nomProjet);
							}
						
					  }catch(DaoException ex){
						    assert(ex.getMessage().contains("Création échoué : Le projet existe déjà."));
						    assert(ex.getMessage().contains("L'identifiant est obligatoire. Valeur zéro est interdit."));
					  }
				}
			 } finally {
				 LOGGER.info("Fin méthode  : testCreateProjet");
			 }
		}
	
	@Rollback(value=true)
	@Test
	public void testUpdateProjet() throws DaoException{

		//logger
		LOGGER.info("Début méthode  : testCreateProjet");
		int idClient = 1;
		int idProjet = 2;
		//On instancie de le ProjetDAOImpl
		ProjetDAOImpl projetDAO = new ProjetDAOImpl();
		//On instancie l'objet DO
		ProjetDO projetDO = new ProjetDO();
				
		try {
			//On recupere le projet via le nom, url, branche
			 projetDO = projetDAO.findProjet("nomProjet", "url", "brancheA");
			
			 if (projetDO != null){
					LOGGER.warn("projetDO "+projetDO +" existe donc ne sera pas créé");
					fail("Création échoué : Le projet existe déjà.");
				} else {
					//On recupere l'objet ClientDO via son id
					ClientDO clientDO = clientDAO.find(idClient);
					//On recupere le projet via son id 
					projetDO = projetDAO.find(idProjet);
					
					//On set les nouvelles infos du membre dans l'objet DTO
					projetDO.setBranche("brancheAB");
					projetDO.setCredential("credential");
					projetDO.setDescription("BonProjetAZ");
					projetDO.setFrequence("frequenceAZ");
					projetDO.setActif(true);
					projetDO.setNomProjet("nomProjetAZ");
					projetDO.setUrl("urlAZ");
					projetDO.setStatus('V');
					projetDO.setClient(clientDO);
					projetDO.setIdProjet(idProjet);
					
					//On interoge le service update
					projetDAO.update(projetDO);
					//On recupere le username du membre
					String nomProjet = projetDO.getNomProjet();
					//On verifie l'egaliter du username
					assertEquals("nomProjetAZ",nomProjet);
				
				}
		}catch(DaoException ex){
		    assert(ex.getMessage().contains("Création échoué : Le projet existe déjà."));
		}
		 LOGGER.info("Fin méthode  : testUpdateProjet");
	}
	@Rollback(value = true)
	@Test
	public void testDeleteProjet() throws DaoException {
		
		//logger
		 LOGGER.info("Début méthode  : testDeleteProjet");
		
		int idProjet = 14;
		//On recupere le projet via son Id
		ProjetDO projetDO = projetDAO.find(idProjet);
		//On appel le service delete projet
		projetDAO.delete(projetDO);
		//Le test passe si l’exception spécifiée dans le try-catch est levée, sinon il échoue à l’exécution de la méthode fail()
		 try {
			 @SuppressWarnings("unused")
			 ProjetDO projetDO1 = projetDAO.find(idProjet); 
		     fail("Le projet n'existe pas.");
		  }catch(DaoException ex){
			    assert(ex.getMessage().contains("Le projet n'existe pas."));
	      }
		 LOGGER.info("Fin méthode  : testDeleteProjet");
	}
	
	@Rollback(value=true)
	@SuppressWarnings("unused")
	@Test
	public void testListeProjet() throws DaoException{
		
		//logger
		LOGGER.info("Début méthode  : testListeProjet");
		
		//On affiche le contenu de la liste
		Iterable<ProjetDO> projetDO =	projetDAO.listeObjects();
	
		int size = 0;
		 for (ProjetDO projetDO2 : projetDO) {
			 size ++;
			
		}
			assertEquals(6, size);
			
			LOGGER.info("Fin méthode  : testListeProjet");
	}
	
	
	@Rollback(value=true)
	@SuppressWarnings("unused")
	@Test
	public void testListeObjectByMembres() throws DaoException{
		
		//logger
		LOGGER.info("Début méthode  : testListeObjectByMembres");
		
		int idMembre = 1;
		//On recuper l'objet via son id
		MembreDO membreDO = membreDAO.find(idMembre);
		// on obtient la liste RoleProjet
		Iterable<RoleProjetDO> lListeRoleProjet = membreDO.getRoleProjet();
	
		int size = 0;
		 for (RoleProjetDO RoleProjetDos : lListeRoleProjet) {
			 size ++;
			
		}
			assertEquals(2, size);
			
			LOGGER.info("Fin méthode  : testListeObjectByMembres");
	}
	
	@Rollback(value=true)
	@SuppressWarnings("unused")
	@Test
	public void testListeObjectByClients() throws DaoException{
		
		//logger
		LOGGER.info("Début méthode  : testListeObjectByClients");
		
		int idClient = 1;
		//On recuper l'objet via son id
		ClientDO ClientDO = clientDAO.find(idClient);
		// on obtient la liste des ProjetDO
		Iterable<ProjetDO> lListeProjet = projetDAO.listeObjects(ClientDO);
	
		int size = 0;
		 for (ProjetDO ProjetDos : lListeProjet) {
			 size ++;
			
		}
			assertEquals(6, size);
			
			LOGGER.info("Fin méthode  : testListeObjectByClients");
	}
}
	
	
