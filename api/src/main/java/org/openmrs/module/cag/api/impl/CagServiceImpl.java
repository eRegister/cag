/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.cag.api.impl;

import org.openmrs.Patient;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.api.db.CagDao;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.cag.CagPatient;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CagServiceImpl extends BaseOpenmrsService implements CagService {
	
	private CagDao dao;
	
	public CagDao getDao() {
		return dao;
	}
	
	public void setDao(CagDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Cag getCagById(Integer cagId) {
		return dao.getCagById(cagId);
	}
	
	@Override
	public Cag getCagByUuid(String uuid) {
		return dao.getCagByUuid(uuid);
	}
	
	@Override
	public List<Cag> getCagList() {
		return dao.getCagList();
	}
	
	@Override
	public void saveCag(Cag cag) {
		
		cag.setUuid(UUID.randomUUID().toString());
		cag.setCreator(Context.getAuthenticatedUser());
		dao.saveCag(cag);
	}
	
	@Override
	public void voidCag(Cag cag) {
		cag.setVoided(true);
		dao.saveCag(cag);
	}
	
	@Override
	public void onStartup() {
	}
	
	@Override
	public void onShutdown() {
	}
	
	@Override
	public CagPatient getCagPatientById(Integer cagPatientId) {
		return this.dao.getCagPatientById(cagPatientId);
	}
	
	@Override
	public List<Patient> getCagPatientList(Integer cagId) {
		return dao.getCagPatientList(cagId);
	}
	
	@Override
	public void saveCagPatient(CagPatient cagPatient) {
		dao.saveCagPatient(cagPatient);
	}
	
	@Override
	public void deletePatientFromCag(CagPatient cagPatient) {
		cagPatient.setStatus(false);
		
	}
	
	@Override
	public void deleteCag(String uuid) {
		dao.deleteCag(uuid);
	}
	
	@Override
	public Cag updateCag(Cag cag) {
		
		cag.setDateChanged(new Date());
		cag.setChangedBy(Context.getAuthenticatedUser());
		return dao.updateCag(cag);
	}
	
}
