package org.openmrs.module.cag.api.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.cag.cag.Cag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("cag.CagDao")
public class CagDao {
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Cag getCagById(Integer cagId) {
		Cag cag = (Cag) getSession().createCriteria(Cag.class).add(Restrictions.eq("id", cagId)).uniqueResult();
		return cag;
	}
	
	public List<Cag> getCagList() {
		List<Cag> list = getSession().createCriteria(Cag.class).list();
		return list;
	}
	
	public void saveCag(Cag cag) {
		getSession().saveOrUpdate(cag);
	}
	
	public Cag getCagByUuid(String uuid) {
		return (Cag) getSession().createCriteria(Cag.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
}
