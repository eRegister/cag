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

import ca.uhn.hl7v2.model.v25.datatype.MA;
import javafx.util.Pair;
import liquibase.servicelocator.LiquibaseService;
import org.openmrs.*;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.logic.op.In;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.api.db.CagDao;
import org.openmrs.module.cag.cag.*;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.*;

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
		Cag cag = dao.getCagByUuid(uuid);
		List<Patient> cagPatientList = getCagPatientList(cag.getId());
		cag.setCagPatientList(cagPatientList);
		return cag;
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
	public CagVisit saveCagVisit(CagVisit cagVisit) {
		
		cagVisit.setUuid(UUID.randomUUID().toString());
		cagVisit.setDate_started(new Date());
		
		Cag cag = Context.getService(CagService.class).getCagByUuid(cagVisit.getCagUuid());
		Integer cagId = cag.getId();
		cagVisit.setCag_id(cagId);
		cagVisit.setCreator(Context.getAuthenticatedUser());
		
		Patient patient = Context.getPatientService().getPatientByUuid(cagVisit.getAttenderUuid());
		Integer attenderId = patient.getPatientId();
		cagVisit.setPatient_id(attenderId);
		
		CagVisit savedCagVisit = dao.saveCagVisit(cagVisit);
		
		List<String> patientUuidList = cagVisit.getPatientUuidList();
		String loggedInLocation = cagVisit.getLocationName();
		List<Visit> visitList = createCagPatientVisitList(patientUuidList, loggedInLocation);
		
		savedCagVisit.setVisitList(visitList);
		
		Map<String, String> absentees = cagVisit.getAbsentees();
		if (!absentees.isEmpty()) {
			
			for (Map.Entry<String, String> absenteeEntry : absentees.entrySet()) {
				Absentee absentee = new Absentee();
				Patient absentPatient = Context.getPatientService().getPatientByUuid(absenteeEntry.getKey());
				absentee.setPatientId(absentPatient.getPatientId());
				absentee.setCagVisitId(savedCagVisit.getId());
				absentee.setReason(absenteeEntry.getValue());
				
				absentees.put(
				    absenteeEntry.getKey(),
				    absentPatient.getPatientIdentifier(3) + " - " + absentPatient.getGivenName() + " "
				            + absentPatient.getFamilyName() + " : " + absenteeEntry.getValue());
				
				dao.saveAbsentCagPatient(absentee);
				
			}
		}
		
		String displayed = dao.getCagById(cagVisit.getCag_id()).getName() + " @ " + cagVisit.getLocationName() + " - "
		        + savedCagVisit.getDate_started();
		savedCagVisit.setDisplay(displayed);
		
		return savedCagVisit;
		
	}
	
	public List<Visit> createCagPatientVisitList(List<String> patientUuidList, String locationName) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		VisitType visitType = Context.getVisitService().getVisitType(10);
		Date startDate = new Date();
		
		PatientService patientService = Context.getPatientService();
		
		for (String patientUuid : patientUuidList) {
			Visit visit = new Visit(patientService.getPatientByUuid(patientUuid), visitType, startDate);
			visit.setLocation(Context.getLocationService().getLocation(locationName));
			Visit savedVisit = Context.getVisitService().saveVisit(visit);
			
			visitList.add(savedVisit);
		}
		
		return visitList;
	}
	
	@Override
	public CagVisit getCagVisitByUuid(String uuid) {
		CagVisit retrievedCagVisit = dao.getCagVisitByUuid(uuid);
		
		String displayed = dao.getCagById(retrievedCagVisit.getCag_id()).getName() + " @ "
		        + retrievedCagVisit.getLocationName() + " - " + retrievedCagVisit.getDate_started();
		retrievedCagVisit.setDisplay(displayed);
		
		Patient attender = Context.getPatientService().getPatient(retrievedCagVisit.getPatient_id());
		
		List<Visit> attenderActiveVisitList = (Context.getVisitService().getActiveVisitsByPatient(attender));
		
		List<Visit> visitList = new ArrayList<Visit>();
		Visit mostRecentVisit = attenderActiveVisitList.get(0);
		visitList.add(mostRecentVisit);
		
		//		retrievedCagVisit.setVisitList(visitList);
		Map<String, String> absentees = getAbsentees(retrievedCagVisit);
		
		retrievedCagVisit.setAbsentees(absentees);
		
		retrievedCagVisit.setVisitList(getPresentPatientVisits(absentees.keySet(), retrievedCagVisit.getCag_id(),
		    retrievedCagVisit.getDate_started()));
		
		return retrievedCagVisit;
	}
	
	public Map<String, String> getAbsentees(CagVisit cagVisit) {
		Map<String, String> absentees = new HashMap<String, String>();
		
		List<Absentee> absenteeList = dao.getAbsenteeList(cagVisit.getId());
		
		if (!absenteeList.isEmpty()) {
			
			for (Absentee absentee : absenteeList) {
				
				Patient absentPatient = Context.getPatientService().getPatient(absentee.getPatientId());
				
				absentees.put(
				    absentPatient.getUuid(),
				    absentPatient.getPatientIdentifier(3) + " - " + absentPatient.getGivenName() + " "
				            + absentPatient.getFamilyName() + " : " + absentee.getReason());
			}
		}
		
		return absentees;
	}
	
	public List<Visit> getPresentPatientVisits(Set<String> absenteeUuidSet, Integer cagId, Date visitDate) {
		List<Visit> visitList = new ArrayList<Visit>();
		VisitType visitType = Context.getVisitService().getVisitType(10);
		
		List<Patient> cagPatientList = getCagPatientList(cagId);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String VisitTime = simpleDateFormat.format(visitDate);
		
		for (Patient presentPatient : cagPatientList) {
			if (!absenteeUuidSet.contains(presentPatient.getUuid())) {
				
				Visit visit = new Visit(presentPatient, visitType, visitDate);
				String visitUuid = dao.getPresentPatientVisitUuid(presentPatient.getPatientId(), VisitTime);
				visit.setUuid(visitUuid);
				visitList.add(visit);
			}
		}
		
		return visitList;
	}
	
	public List<Visit> getCagPatientVisitList(CagVisit cagVisit) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		List<Integer> visitIdList = dao.getVisitIdList(cagVisit);
		
		for (Integer visitId : visitIdList) {
			
			visitList.add(Context.getVisitService().getVisit(visitId));
			
		}
		return visitList;
	}
	
	@Override
	public CagVisit closeCagVisit(String uuid, List<String> visitUuidList) {
		
		CagVisit cagVisit = dao.getCagVisitByUuid(uuid);
		
		cagVisit.setDate_stopped(new Date());
		
		closePatientVisits(visitUuidList);
		return dao.closeCagVisit(cagVisit);
	}
	
	public void closePatientVisits(List<String> visitUuidList) {
		System.out.println(visitUuidList);
		
		for (String visitUuid : visitUuidList) {
			Visit visitToClose = Context.getVisitService().getVisitByUuid(visitUuid);
			Context.getVisitService().endVisit(visitToClose, new Date());
		}
	}
	
	@Override
	public void deleteCagVisit(String uuid) {
		CagVisit cagVisit = getCagVisitByUuid(uuid);
		
		dao.deleteCagVisit(cagVisit.getId());
	}
	
	@Override
	public CagEncounter getCagEncounterByUuid(String uuid) {
		return dao.getCagEncounterByUuid(uuid);
	}
	
	@Override
	public CagEncounter saveCagEncounter(CagEncounter cagEncounter) {
		
		Integer cagId = Context.getService(CagService.class).getCagByUuid(cagEncounter.getCagUuid()).getId();
		CagVisit cagVisit = getCagVisitByUuid(cagEncounter.getCagVisitUuid());
		System.out.println("cagVisit: \n" + cagVisit.toString() + "\n");
		
		cagEncounter.setCagId(cagId);
		cagEncounter.setCagVisitId(cagVisit.getId());
		cagEncounter.setCreator(Context.getAuthenticatedUser());
		dao.saveCagEncounter(cagEncounter);
		
		//		Setting Attender encounter
		Encounter attenderEncounter = new Encounter();
		attenderEncounter.setEncounterDatetime(cagEncounter.getEncounter().getEncounterDatetime());
		attenderEncounter.setPatient(Context.getPatientService().getPatientByUuid(
		    cagEncounter.getEncounter().getPatient().getUuid()));
		attenderEncounter.setLocation(Context.getLocationService().getLocationByUuid(
		    cagEncounter.getEncounter().getLocation().getUuid()));
		attenderEncounter.setForm(Context.getFormService().getFormByUuid(cagEncounter.getEncounter().getForm().getUuid()));
		attenderEncounter.setEncounterType(Context.getEncounterService().getEncounterTypeByUuid(
		    cagEncounter.getEncounter().getEncounterType().getUuid()));
		attenderEncounter.setVisit(Context.getVisitService()
		        .getVisitByUuid(cagEncounter.getEncounter().getVisit().getUuid()));
		
		for (Obs obs : cagEncounter.getEncounter().getObs()) {
			obs.setConcept(Context.getConceptService().getConceptByUuid(obs.getConcept().getUuid()));
			obs.setPerson(Context.getPersonService().getPersonByUuid(obs.getPerson().getUuid()));
			System.out.println("\nobs.getPerson().toString() : " + obs.getPerson().toString() + "\nobs.getConcept() : "
			        + obs.getConcept().getDisplayString() + "\n");
		}
		attenderEncounter.setObs(cagEncounter.getEncounter().getObs());
		
		//		System.out.println();
		
		Context.getEncounterService().saveEncounter(attenderEncounter);
		
		//		Encounter AttenderEncounter = cagEncounter.getEncounter();
		//		System.out.println("encounter.getLocation().getUuid() : " + AttenderEncounter.getLocation().getUuid());
		//		Context.getEncounterService().saveEncounter(AttenderEncounter);
		
		List<Visit> visitList = cagVisit.getVisitList();
		System.out.println("Visit UUID List");
		for (Visit visit : visitList) {
			System.out.println(visit.getUuid());
			//			Encounter encounter = new Encounter();
			
		}
		
		return getCagEncounterByUuid(cagEncounter.getUuid());
		
	}
	
	@Override
	public void deleteCagEncounter(String uuid) {
		
	}
}
