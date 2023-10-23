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
import java.text.ParseException;
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
	public CagVisit openCagVisit(CagVisit cagVisit) {
		
		//		cagVisit.setDateStarted(new Date());
		
		Cag cag = Context.getService(CagService.class).getCagByUuid(cagVisit.getCagUuid());
		Integer cagId = cag.getId();
		cagVisit.setCagId(cagId);
		cagVisit.setCreator(Context.getAuthenticatedUser());
		
		Patient patient = Context.getPatientService().getPatientByUuid(cagVisit.getAttenderUuid());
		Integer attenderId = patient.getPatientId();
		cagVisit.setPatientId(attenderId);
		CagVisit savedCagVisit = dao.saveCagVisit(cagVisit);
		
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
		
		String loggedInLocation = cagVisit.getLocationName();
		Date dateStarted = cagVisit.getDateStarted();
		
		createCagPatientVisitList(absentees.keySet(), loggedInLocation, cagId, dateStarted);
		
		String displayed = dao.getCagById(cagVisit.getCagId()).getName() + " @ " + cagVisit.getLocationName() + " - "
		        + savedCagVisit.getDateStarted();
		savedCagVisit.setDisplay(displayed);
		
		return getCagVisitByUuid(savedCagVisit.getUuid());
	}
	
	public List<Visit> createCagPatientVisitList(Set<String> absentees, String locationName, Integer cagId, Date dateStarted) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		VisitType visitType = Context.getVisitService().getVisitType(10);
		
		List<Patient> presentPatients = getPresentPatients(absentees, cagId);
		
		for (Patient currentPatient : presentPatients) {
			Visit visit = new Visit(currentPatient, visitType, dateStarted);
			visit.setLocation(Context.getLocationService().getLocation(locationName));
			Visit savedVisit = Context.getVisitService().saveVisit(visit);
			
			visitList.add(savedVisit);
		}
		
		return visitList;
	}
	
	@Override
	public CagVisit getCagVisitByUuid(String uuid) {
		CagVisit retrievedCagVisit = dao.getCagVisitByUuid(uuid);
		
		System.out.println("\n\n" + retrievedCagVisit + "\n\n");
		
		String displayed = dao.getCagById(retrievedCagVisit.getCagId()).getName() + " @ "
		        + retrievedCagVisit.getLocationName() + " - " + retrievedCagVisit.getDateStarted();
		retrievedCagVisit.setDisplay(displayed);
		
		Patient attender = Context.getPatientService().getPatient(retrievedCagVisit.getPatientId());
		List<Visit> attenderActiveVisitList = Context.getVisitService().getActiveVisitsByPatient(attender).subList(0, 1);
		retrievedCagVisit.setVisitList(attenderActiveVisitList);
		
		Map<String, String> absentees = getAbsentees(retrievedCagVisit);
		retrievedCagVisit.setAbsentees(absentees);
		
		retrievedCagVisit.setPresentPatients(getPresentPatients(absentees.keySet(), retrievedCagVisit.getCagId()));
		
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
	
	public List<Patient> getPresentPatients(Set<String> absentees, Integer cagId) {
		
		List<Patient> cagPatientList = getCagPatientList(cagId);
		
		List<Patient> presentPatients = new ArrayList<Patient>();
		for (Patient currentPatient : cagPatientList) {
			if (!absentees.contains(currentPatient.getUuid())) {
				presentPatients.add(Context.getPatientService().getPatientByUuid(currentPatient.getUuid()));
			}
		}
		
		return presentPatients;
	}
	
	@Override
	public CagVisit closeCagVisit(String uuid, String dateStopped) {
		
		Date stopDate = new Date();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			stopDate = simpleDateFormat.parse(dateStopped);
		}
		catch (Exception e) {
			stopDate = new Date();
			System.out.println("Cought ParseException Exception!!!");
			throw new ParseException("Date ParseException encountered!", 1);
		}
		finally {
			System.out.println("dateStopped=================== " + stopDate);
			
			CagVisit cagVisit = dao.closeCagVisit(uuid, dateStopped);
			
			Map<String, String> absentees = getAbsentees(cagVisit);
			
			Set<String> absenteeUuidSet = absentees.keySet();
			Integer cagId = cagVisit.getCagId();
			Date visitStartDate = cagVisit.getDateStarted();
			
			List<Visit> presentPatientVisits = closePatientVisits(absenteeUuidSet, cagId, visitStartDate, dateStopped);
			
			return cagVisit;
		}
	}
	
	public List<Visit> closePatientVisits(Set<String> absenteeUuidSet, Integer cagId, Date visitDate, String dateStopped) {
		List<Visit> visitList = new ArrayList<Visit>();
		VisitType visitType = Context.getVisitService().getVisitType(10);
		
		List<Patient> cagPatientList = getCagPatientList(cagId);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String VisitTime = simpleDateFormat.format(visitDate);
		
		for (Patient presentPatient : cagPatientList) {
			if (!absenteeUuidSet.contains(presentPatient.getUuid())) {
				dao.closeCagPatientVisit(presentPatient, VisitTime, dateStopped);
			}
		}
		
		return visitList;
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
		
		cagEncounter.setCagId(cagId);
		cagEncounter.setCagVisitId(cagVisit.getId());
		cagEncounter.setCreator(Context.getAuthenticatedUser());
		dao.saveCagEncounter(cagEncounter);
		
		Encounter attenderEncounter = cagEncounter.getEncounter();
		dao.saveCagPatientEncounter(attenderEncounter);
		
		//		System.out.println(cagVisit);
		//		===========================================================
		//		List<Visit> presentPatientVisits = getPresentPatientVisits(cagVisit);
		//		===========================================================
		
		for (Obs currentObs : cagEncounter.getEncounter().getObs()) {
			currentObs.setEncounter(attenderEncounter);
			Context.getObsService().saveObs(currentObs, "Saving Cag Obs");
		}
		attenderEncounter.setObs(cagEncounter.getEncounter().getObs());
		
		System.out.println("\n\nattenderEncounter :\n" + attenderEncounter);
		
		//		dao.saveCagPatientEncounter(attenderEncounter);
		List<Visit> visitList1 = cagVisit.getVisitList();
		System.out.println("Visit UUID List");
		for (Visit visit : visitList1) {
			System.out.println(visit.getUuid());
			//			Encounter encounter = new Encounter();
			
		}
		
		return getCagEncounterByUuid(cagEncounter.getUuid());
		
	}
	
	public List<Visit> getPresentPatientVisits(CagVisit cagVisit) {
		List<Visit> presentPatientVisits = new ArrayList<Visit>();
		
		Set<String> absenteeUuidSet = getAbsentees(cagVisit).keySet();
		//		List<Patient> presentPatients = getPresentPatients(absentees.keySet(), cagVisit.getCagId());
		
		List<Patient> cagPatientList = getCagPatientList(cagVisit.getCagId());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String VisitTime = simpleDateFormat.format(cagVisit.getDateStarted());
		
		for (Patient presentPatient : cagPatientList) {
			if (!absenteeUuidSet.contains(presentPatient.getUuid())) {
				//				dao.closeCagPatientVisit(presentPatient, VisitTime, dateStopped);
				presentPatientVisits.add(Context.getVisitService().getActiveVisitsByPatient(presentPatient).get(0));
			}
		}
		return presentPatientVisits;
	}
	
	//	public List<Visit> getCagPatientVisitList(CagVisit cagVisit) {
	//		List<Visit> visitList = new ArrayList<Visit>();
	//
	//		List<Integer> visitIdList = dao.getVisitIdList(cagVisit);
	//
	//		for (Integer visitId : visitIdList) {
	//
	//			visitList.add(Context.getVisitService().getVisit(visitId));
	//
	//		}
	//		return visitList;
	//	}
	
	@Override
	public void deleteCagEncounter(String uuid) {
		
	}
}
