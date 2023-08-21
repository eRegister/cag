package org.openmrs.module.cag.api.db.hibernate;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.cag.api.db.CagDao;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.cag.CagPatient;

import java.util.List;

public class HibernateCagDao implements CagDao {
	
	private DbSessionFactory sessionFactory;
	
	public DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Cag getCagById(Integer cagId) {
		Transaction tx = getSession().beginTransaction();
		
		Query query = getSession().createQuery("from cag c where c.id=:id and c.voided=:voided");
		query.setInteger("voided", 0);
		query.setInteger("id", cagId);
		Cag cag = (Cag) query.uniqueResult();
		
		if (!tx.wasCommitted())
			tx.commit();
		
		return cag;
	}
	
	@Override
	public List<Cag> getCagList() {
		Transaction tx = getSession().beginTransaction();
		
		Query query = getSession().createQuery("from cag c where c.voided= :voided");
		query.setInteger("voided", 0);
		List<Cag> cagList = query.list();
		
		if (!tx.wasCommitted())
			tx.commit();
		
		return cagList;
	}
	
	@Override
	public void saveCag(Cag cag) {
		Transaction tx = getSession().beginTransaction();
		getSession().save(cag);
		if (!tx.wasCommitted())
			tx.commit();
	}
	
	@Override
	public Cag getCagByUuid(String uuid) {
		Transaction tx = getSession().beginTransaction();
		
		Query query = getSession().createQuery("from cag c where c.uuid=:uuid and c.voided=:voided");
		query.setInteger("voided", 0);
		query.setString("uuid", uuid);
		Cag cag = (Cag) query.uniqueResult();
		
		if (!tx.wasCommitted())
			tx.commit();
		return cag;
	}
	
	@Override
	public void updateCag(Cag cag) {
		Transaction tx = getSession().beginTransaction();
		
		Query query = getSession()
		        .createQuery(
		            "update cag c set c.name=:name, c.description=:description, c.village=:village, c.constituency=:constituency, c.district=:district, c.dateChanged=:date_changed, c.changedBy=:changed_by where c.uuid=:uuid and c.voided=:voided");
		query.setString("name", cag.getName());
		query.setString("description", cag.getDescription());
		query.setString("village", cag.getVillage());
		query.setString("constituency", cag.getConstituency());
		query.setString("district", cag.getDistrict());
		query.setDate("date_changed", cag.getDateChanged());
		query.setParameter("changed_by", cag.getChangedBy());
		//		query.setParameter("creator",cag.getCreator());
		query.setInteger("voided", 0);
		query.setString("uuid", cag.getUuid());
		query.executeUpdate();
		
		if (!tx.wasCommitted())
			tx.commit();
	}
	
	@Override
	public void deleteCag(String uuid) {
		Transaction tx = getSession().beginTransaction();
		
		Query query = getSession().createQuery("update cag c set c.voided=:voided where uuid=:uuid");
		query.setInteger("voided", 1);
		query.setString("uuid", uuid);
		query.executeUpdate();
		
		if (!tx.wasCommitted())
			tx.commit();
	}
	
	@Override
	public CagPatient getCagPatientById(Integer cagPatientId) {
		Transaction tx = getSession().beginTransaction();
		
		Query query = getSession().createQuery("from cag_patient cp where cp.cagPatientId=:id and cp.status=:status");
		query.setBoolean("status", true);
		query.setInteger("id", cagPatientId);
		CagPatient cagPatient = (CagPatient) query.uniqueResult();
		
		if (!tx.wasCommitted())
			tx.commit();
		return cagPatient;
	}
	
	@Override
	public List<CagPatient> getCagPatientList(Integer id) {
		Transaction tx = getSession().beginTransaction();
		
		Query query = getSession().createQuery("from cag_patient cp where cp.status=:status and cp.cag_id=:id");
		query.setBoolean("status", true);
		query.setInteger("id", id);
		List<CagPatient> cagPatientList = query.list();
		
		if (!tx.wasCommitted())
			tx.commit();
		
		return cagPatientList;
	}
	
	@Override
	public void saveCagPatient(CagPatient cag_patient) {
		Transaction tx = getSession().beginTransaction();
		
		getSession().save(cag_patient);
		if (!tx.wasCommitted())
			tx.commit();
	}
	
	@Override
	public void deletePatientFromCag(CagPatient cagPatient) {
		
	}
}
