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
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.PatientService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.api.db.CagDao;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.cag.CagEncounter;
import org.openmrs.module.cag.cag.CagPatient;
import org.openmrs.module.cag.cag.CagVisit;

import java.util.ArrayList;
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
		cag.setVoided(false);
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
	public Patient saveCagPatient(CagPatient cagPatient) {
		Integer cagId = getCagByUuid(cagPatient.getCagUuid()).getId();
		cagPatient.setCagId(cagId);
		
		Patient patient = Context.getPatientService().getPatientByUuid(cagPatient.getUuid());
		Integer patientId = patient.getPatientId();
		cagPatient.setPatientId(patientId);
		
		cagPatient.setStatus(true);
		
		dao.saveCagPatient(cagPatient);
		
		return patient;
	}
	
	@Override
	public void deletePatientFromCag(String uuid) {
		Integer patientId = Context.getPatientService().getPatientByUuid(uuid).getPatientId();
		dao.deletePatientFromCag(patientId);
		
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
	
	@Override
	public CagPatient getCagPatientByUuid(String uuid) {
		return dao.getCagPatientByUuid(uuid);
	}
	
	@Override
	public void saveCagVisit(CagVisit cagVisit) {
		cagVisit.setUuid(UUID.randomUUID().toString());
		cagVisit.setDate_started(new Date());
		
		Cag cag = Context.getService(CagService.class).getCagByUuid(cagVisit.getCagUuid());
		Integer cagId = cag.getId();
		cagVisit.setCag_id(cagId);
		cagVisit.setCreator(Context.getAuthenticatedUser());
		cagVisit.setVoided(false);
		
		List<Visit> visitList = openCagPatientsVisits(cagVisit);
		
		System.out.println("=============opened Visits=============/n");
		for (Visit visit : visitList) {
			System.out.println(visit);
		}
		
		cagVisit.setVisits(visitList);
		dao.saveCagVisit(cagVisit);
		
	}
	
	public List<Visit> openCagPatientsVisits(CagVisit cagVisit) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		VisitType visitType = Context.getVisitService().getVisitType(10);
		Date startDate = new Date();
		
		PatientService patientService = Context.getPatientService();
		
		for (String patientUuid : cagVisit.getPatientUuidList()) {
			Visit visit = new Visit(patientService.getPatientByUuid(patientUuid), visitType, startDate);
			Visit savedVisit = Context.getVisitService().saveVisit(visit);
			
			visitList.add(savedVisit);
			
		}
		
		return visitList;
		
	}
	
	public void createCagVisitVisitMapping(Integer cagVisitId) {
		
	}
	
	@Override
	public CagVisit getCagVisitByUuid(String uuid) {
		return dao.getCagVisitByUuid(uuid);
	}
	
	@Override
	public CagVisit updateCagVisit(String uuid) {
		CagVisit cagVisit = dao.getCagVisitByUuid(uuid);
		cagVisit.setDate_stopped(new Date());
		
		return dao.updateCagVisit(cagVisit);
	}
	
	@Override
	public void deleteCagVisit(String uuid) {
		CagVisit cagVisit = getCagVisitByUuid(uuid);
		
		System.out.println("=============CAG Visit To Delete=================\n" + cagVisit);
		
		dao.deleteCagVisit(cagVisit.getId());
	}
	
	@Override
	public List<Visit> getCagVisits(Integer cagId) {
		return null;
	}
	
	@Override
	public CagEncounter getCagEncounterByUuid(String uuid) {
		return dao.getCagEncounterByUuid(uuid);
	}
	
	@Override
	public void saveCagEncounter(CagEncounter cagEncounter) {
		
		cagEncounter.setUuid(UUID.randomUUID().toString());
		cagEncounter.setNext_encounter_date(new Date());
		
		Cag cag = Context.getService(CagService.class).getCagByUuid(cagEncounter.getCagUuid());
		Integer cagId = cag.getId();
		cagEncounter.setCag_id(cagId);
		cagEncounter.setCreator(Context.getAuthenticatedUser());
		cagEncounter.setVoided(false);
		dao.saveCagEncounter(cagEncounter);
		
	}
	
	@Override
	public void deleteCagEncounter(String uuid) {
		
	}
}
