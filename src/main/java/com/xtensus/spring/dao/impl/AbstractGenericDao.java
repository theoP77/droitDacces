package com.xtensus.spring.dao.impl;

import java.util.List;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;


import com.xtensus.hibernate.model.Entite;
import com.xtensus.hibernate.model.XteButon;
import com.xtensus.hibernate.model.XteInterface;
import com.xtensus.qualifiers.Dao;
import com.xtensus.spring.dao.IGenericDao;

@SuppressWarnings("deprecation")
@Dao(type = Entite.class)
public class AbstractGenericDao implements IGenericDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @return the sessiionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	/**
	 * @param sessiionFactory the sessiionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void insert(Entite entity) throws HibernateException {
		getSession().persist(entity);
	}

	@Override
	public void update(Entite entity) throws HibernateException {
		getSession().saveOrUpdate(entity);
	}
	
	@Override
	public void find(Entite entity) throws HibernateException {
		getSession().find(Entite.class, entity);	
	}

	@Override
	public void delete(Entite entity) throws HibernateException {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entite> List<T> getList(Class<T> classe) throws HibernateException {
		Criteria criteria = getSession().createCriteria(classe);
		return criteria.list();
	}
	
	@Override
	public <T extends Entite> void find(Class<T> t, Object primaryKey) throws HibernateException {
		getSession().find(Entite.class, primaryKey);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Entite> getListWithNamedQuery(String namedQueryName) throws HibernateException {
		Query query = getSession().getNamedQuery(namedQueryName);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Entite> getListWithNamedQuery(String namedQueryName, Map<String, Object> parameters)
			throws HibernateException {
		Query query = getSession().getNamedQuery(namedQueryName);
		query.setProperties(parameters);
		return query.list();
	}

	@Override
	public void deleteWithNamedQuery(String namedQueryName) throws HibernateException {
		Query query = getSession().createQuery(namedQueryName);
		query.executeUpdate();
	}

	@Override
	public void deleteWithNamedQuery(String namedQueryName, Map<String, Object> parameters) throws HibernateException {
		Query query = getSession().getNamedQuery(namedQueryName);
		query.setProperties(parameters);
		query.executeUpdate();
	}

	@Override
	public void updateWithNamedQuery(String namedQueryName) throws HibernateException {
		Query query = getSession().createQuery(namedQueryName);
		query.executeUpdate();

	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public void updateWithNamedQuery(String namedQueryName, Map<String, Object> parameters) throws HibernateException {
		Query query = getSession().getNamedQuery(namedQueryName);
		query.setProperties(parameters);
		query.executeUpdate();
	}

	@Override
	public void findWithNamedQuery(String namedQueryName) throws HibernateException {
		Query query = getSession().createQuery(namedQueryName);
		query.executeUpdate();	
	}

	@Override
	public void findWithNamedQuery(String namedQueryName, Map<String, Object> parameters) throws HibernateException {
		Query query = getSession().getNamedQuery(namedQueryName);
		query.setProperties(parameters);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entite> void findWithNamedQuery(Class<T> t, Object namedQueryName) throws HibernateException {
		Query query = getSession().getNamedQuery(namedQueryName);  
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entite> void findWithNamedQuery(Class<T> t, Object namedQueryName, Map<String, Object> parameters)
			throws HibernateException {
		Query query = getSession().getNamedQuery(namedQueryName);
		query.setProperties(parameters);
		query.executeUpdate();
		
	}
	
	@Override
	public XteInterface findObject(Integer id)  {
		 Query query = getSession().createQuery("from XteInterface d where d.xteInterfaceRef = :id")
         .setInteger("id", id);
		return (XteInterface) query.uniqueResult();
	}
	
}