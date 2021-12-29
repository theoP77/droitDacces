package com.xtensus.spring.service;

import java.util.List;

import com.xtensus.hibernate.model.Entite;
import com.xtensus.hibernate.model.XteInterface;


public interface IGenericManager {

	public void insert(Entite entity) throws Exception;

	public void update(Entite entity) throws Exception;

	public void delete(Entite entity) throws Exception;
	
	public void find(Entite entity) throws Exception;

	public <T extends Entite> List<T> getList(Class<T> t) throws Exception;
	
	public <T extends Entite> void find(Class<T> t, Object primaryKey) throws Exception;
	
	public XteInterface findObject(Integer id) throws Exception;
	
}
