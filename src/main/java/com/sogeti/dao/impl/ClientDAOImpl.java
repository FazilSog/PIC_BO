package com.sogeti.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IClientDAO;
import com.sogeti.exception.DaoException;
import com.sogeti.fwk.GenericDAO;
import com.sogeti.model.ClientDO;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author moissa
 *
 */

@Service
@Transactional
public class ClientDAOImpl extends GenericDAO<ClientDO> implements IClientDAO {
	
	// Initialisation du LOGGER
	private static final Logger LOGGER = Logger.getLogger(ClientDAOImpl.class);
	
	/**
	 * {@inheritDoc}
	 */
	public ClientDO find(final int pIdClient) throws DaoException {
		
		//on initilalise le clientDO a null
		ClientDO clientDO = null;
	
		//On initialise le LOGGER
		LOGGER.info("D�but m�thode : findClientById");
	
		if (pIdClient == 0) {
			throw new DaoException("idClient obligatoire.La valeur NULL est interdite.");
		} else {
			try {
				//On verifie l'egalit� du role recu avec clui qu'on n'a en base
				final Criteria criteria = HibernateSessionFactory.getSession()
						.createCriteria(ClientDO.class);
				criteria.add(Restrictions.eq("idClient", pIdClient));
				
				clientDO = (ClientDO) criteria.uniqueResult();
				
			} catch (HibernateException ex) {
				// Critical errors : database unreachable, etc.
				LOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete findClientById().");
				throw new DaoException("Connexion �chou� : Identifiant inconnu");
			}
		}
		
		// on test si l'objet client est == null
		if (clientDO == null) {
			throw new DaoException("Le client n'est pas connu!");
		}
		LOGGER.info("Fin m�thode : findClientById");
		return clientDO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void create(final ClientDO pClient) throws DaoException {
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void update(final ClientDO pClient) throws DaoException {
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final ClientDO pClient) throws DaoException {
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(final int pIdClient) throws DaoException {
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<ClientDO> listeObjects() throws DaoException {
		return null;
	}
	
}
