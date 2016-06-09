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

import com.sogeti.dao.IMembreDAO;
import com.sogeti.dao.IProjetDAO;
import com.sogeti.dao.IRoleDAO;
import com.sogeti.dao.IRoleProjetDAO;
import com.sogeti.exception.DaoException;
import com.sogeti.model.MembreDO;
import com.sogeti.model.ProjetDO;
import com.sogeti.model.RoleDO;
import com.sogeti.model.RoleProjetDO;

/**
 * 
 * @author Syahiaou
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:WebContent/WEB-INF/dispatcher-servlet.xml" )
@WebAppConfiguration
@Transactional
@Rollback(value=true)
public class AffectProjetToMembreBOImplTest {
	
	@Autowired 
	private IMembreDAO membreDAO;
	
	@Autowired
	private IProjetDAO projetDAO;
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IRoleProjetDAO roleProjetDAO;
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(ProjetTest.class);
	
	@Rollback(value=true)
	@Test
	public void testCreateRoleProjet() throws DaoException{
		
		//logger
		LOGGER.info("Début méthode  : testCreateRoleProjet");
		int idProjet = 19;
		int idMembre = 1;
		int idRole = 1;
		//On recupere le role via son Id
		RoleDO roleDO = roleDAO.find(idRole);
		//On recuepre le membre via son Id
		MembreDO membreDO = membreDAO.find(idMembre);
		//On recupeper le Projet via son Id
		ProjetDO projetDO = projetDAO.find(idProjet);
		
		// on instancie l'objet RoleProjetDO
		RoleProjetDO roleProjetDO = new RoleProjetDO();
		//On set les info dans l'objet roleProjetDO
		roleProjetDO.setMembre(membreDO);
		roleProjetDO.setProjet(projetDO);
		roleProjetDO.setRole(roleDO);
		
		//On appel le service create
		roleProjetDAO.create(roleProjetDO);
		
		LOGGER.info("Fin méthode  : testCreateRoleProjet");
	}
	
	@Rollback(value=true)
	@Test
	public void testUpdateRoleProjet() throws DaoException{
		
		//logger
		LOGGER.info("Début méthode  : testUpdateRoleProjet");
		int idProjet = 14;
		int idMembre = 1;
		int idRole = 1;
		
		//On recupeper le Projet via son Id
		ProjetDO projetDO = projetDAO.find(idProjet);
		//On recuepre le roleProjet via le projetDO
		RoleProjetDO roleProjetDO = roleProjetDAO.findRoleProjet(projetDO);
		
		//On recupere le role via son Id
		RoleDO roleDO = roleDAO.find(idRole);
		//On set le nouveaux idRole dans l'objet RoleProjetDO
		roleProjetDO.setRole(roleDO);
		//On recuepre le membre via son Id
		MembreDO membreDO = membreDAO.find(idMembre);
		//On set le nouveaux idMembre dans l'objet RoleProjetDO
		roleProjetDO.setMembre(membreDO);
		
		//On appel le service update 
		roleProjetDAO.update(roleProjetDO);
		
		LOGGER.info("Fin méthode  : testUpdateRoleProjet");
	}
	
	@Rollback(value=true)
	@Test
	public void testDeleteRoleProjet() throws DaoException{
		
		//logger
		LOGGER.info("Début méthode  : testDeleteRoleProjet");
		int idProjet = 14;
		int idMembre = 1;
		
		//On recupeper le Projet via son Id
		ProjetDO projetDO = projetDAO.find(idProjet);
		//On recuepre le membre via son Id
		MembreDO membreDO = membreDAO.find(idMembre);
		//On recuepre le roleProjet via le projetDO et le membreDO
		RoleProjetDO roleProjetDO = roleProjetDAO.findRoleProjetByProjetAndMembre(projetDO, membreDO);
		//On appel le service update 
		roleProjetDAO.delete(roleProjetDO);
		
		LOGGER.info("Fin méthode  : testDeleteRoleProjet");
	}
}