package org.openmrs.module.cag.api.impl;

import java.util.List;

import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.api.dao.CagDao;

public class CagServiceImpl implements CagService {
	
	private CagDao dao;
	
	public CagDao getDao() {
		return dao;
	}
	
	public void setDao(CagDao dao) {
		this.dao = dao;
	}
	
	public Cag getCagById(Integer cagId) {
		return dao.getCagById(cagId);
	}
	
	@Override
	public Cag getCagByUuid(String uuid) {
		return dao.getCagByUuid(uuid);
	}
	
	public List<Cag> getCagList() {
		return dao.getCagList();
	}
	
	public void saveCag(Cag cag) {
		dao.saveCag(cag);
	}
	
	public void voidCag(Cag cag) {
		cag.setVoided(true);
		dao.saveCag(cag);
	}
	
	public void onStartup() {
		
	}
	
	public void onShutdown() {
		
	}
}
