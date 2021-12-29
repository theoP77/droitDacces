package com.xtensus.spring.service.impl;

import com.xtensus.hibernate.model.Entite;
import com.xtensus.spring.dao.IGenericDao;
import com.xtensus.spring.service.IGenericManager;

public abstract class AbstractGenericManager implements IGenericManager {

	
	protected IGenericDao dao;
	/**
	 * @return the dao
	 */
	public IGenericDao getDao() {
		return dao;
	}
	/**
	 * @param dao the dao to set
	 */
	public void setDao(IGenericDao dao) {
		this.dao = dao;
	}

	@Override
	public void insert(Entite entity) throws Exception {
		dao.insert(entity);
	}

	@Override
	public void update(Entite entity) throws Exception {
		dao.update(entity);
	}

	@Override
	public void delete(Entite entity) throws Exception {
		dao.delete(entity);
	}

}
