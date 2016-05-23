package com.sogeti.daoImpl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sogeti.dao.IClientDAO;
import com.sogeti.dao.model.ClientDO;
import com.sogeti.exception.DaoException;
import com.sogeti.utils.HibernateSessionFactory;

/**
 * 
 * @author moissa
 *
 */

@Service
@Transactional
public class ClientDAOImpl implements IClientDAO {
	
	// Initialisation du LOGGER
	private Logger lLOGGER = Logger.getLogger(ClientDAOImpl.class);
	
	/**
	 * {@inheritDoc}
	 * @throws DaoException 
	 */
	public ClientDO findClientById(final int pIdClient) throws DaoException {
		
		//on initilalise le clientDO a null
		ClientDO clientDO = null;
	
		//On initialise le LOGGER
		lLOGGER.info("D�but m�thode : findClientById");
	
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
				lLOGGER.error("Exception - DataAccessException occurs : " 
				+ ex.getMessage() + " on complete getProjet().");
				throw new DaoException("Connexion �chou� : Identifiant inconnu");
			}
		}
		lLOGGER.info("Fin m�thode : findClientById");
		return clientDO;
	}
}
