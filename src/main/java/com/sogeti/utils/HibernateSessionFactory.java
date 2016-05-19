package com.sogeti.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author moissa
 *
 */
public class HibernateSessionFactory {

	@Autowired
	private static SessionFactory sessionFactory;
	
	/**
	 * @return the sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		HibernateSessionFactory.sessionFactory = sessionFactory;
	}

	/**
	 * @return the sessionFactory
	 */
	public static Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
}
