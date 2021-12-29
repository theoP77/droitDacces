package com.xtensus.spring.service.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtensus.hibernate.model.Entite;
import com.xtensus.hibernate.model.XteButon;
import com.xtensus.hibernate.model.XteInterface;
import com.xtensus.qualifiers.Dao;
import com.xtensus.spring.dao.IGenericDao;
import com.xtensus.spring.service.IGenericManager;
import static com.xtensus.spring.dao.impl.QueryParameterBuilder.with;

@Service
public class ApplicationManager implements IGenericManager {

	@Autowired
	protected IGenericDao dao;
	
	/**                              
	 * @return the dao
	 */
	public IGenericDao getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(@Dao(type = Entite.class) IGenericDao dao) {
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
	
	public void find(Entite entity) throws Exception {
		dao.find(entity);
	}	
	@Override
	public <T extends Entite> List<T> getList(Class<T> t) throws Exception {
		return dao.getList(t);
	}
	
	@Override 
	public <T extends Entite> void find(Class<T> entityClass, Object primaryKey) throws Exception {
		dao.find(entityClass, primaryKey);
	}
	
	private static final String LIST_BOUTTON_BY_INTERFACE = "listeBoutonParInterface";
	public List<XteButon> getListeBoutonParRefInterface(int xteRef) {
		return (List<XteButon>)dao.getListWithNamedQuery(LIST_BOUTTON_BY_INTERFACE, with("idi", xteRef).parameters());
	}
// supp
	public void deleteXteButonById(Integer xteButonRef) throws Exception {
		dao.deleteWithNamedQuery(LIST_BOUTTON_BY_INTERFACE);        
		
	}

	@Override
	public XteInterface findObject(Integer id) throws Exception {
		 return dao.findObject(id);
	}
}
