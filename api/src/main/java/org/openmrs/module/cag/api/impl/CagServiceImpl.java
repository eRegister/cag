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
import liquibase.servicelocator.LiquibaseService;
import org.openmrs.*;
import org.openmrs.api.APIException;
import org.openmrs.api.OrderContext;
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
	
	public static Set<Integer> COPABLE_CONCEPTS = new HashSet<Integer>(Arrays.asList(3843, 2250, 3751, 3752, 4174, 3730,
	    2403, 3753));
	
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
		
		//		cag.setUuid(UUID.randomUUID().toString());
		cag.setCreator(Context.getAuthenticatedUser());
		cag.setVoided(false);
		dao.saveCag(cag);
		System.out.println("CAg ID : " + cag.getId());
		
		List<Patient> patients = cag.getCagPatientList();
		
		for (Patient currentPatient : patients) {
			CagPatient newCagPatient = new CagPatient();
			newCagPatient.setCag(cag);
			newCagPatient.setPatient(currentPatient);
			
			saveCagPatient(newCagPatient);
		}
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
		
		CagPatient retrivedCagPatient = getCagPatientByUuid(cagPatient.getPatient().getUuid());
		
		System.out.println("retrivedCagPatient : " + retrivedCagPatient);
		
		if ((retrivedCagPatient != null)) {
			
			throw new IllegalArgumentException("WARNING!! Patient : " + retrivedCagPatient.getPatient().getNames()
			        + "(uuid=" + retrivedCagPatient.getPatient().getUuid() + ") " + " Already a member of another CAG:"
			        + " " + retrivedCagPatient.getCag().getName());
			
		} else {
			
			cagPatient.setUuid(cagPatient.getPatient().getUuid());
			cagPatient.setStatus(true);
			
			dao.saveCagPatient(cagPatient);
			
			return cagPatient.getPatient();
		}
		
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
		
		Cag updatedCag = dao.updateCag(cag);
		updatedCag.setCagPatientList(getCagPatientList(updatedCag.getId()));
		
		return updatedCag;
	}
	
	@Override
	public CagPatient getCagPatientByUuid(String uuid) {
		return dao.getCagPatientByUuid(uuid);
	}
	
	@Override
	public CagVisit openCagVisit(CagVisit cagVisit) {
		
		Cag cag = Context.getService(CagService.class).getCagByUuid(cagVisit.getCag().getUuid());
		Integer cagId = cag.getId();
		cagVisit.setCagId(cagId);
		cagVisit.setCreator(Context.getAuthenticatedUser());
		
		Patient attender = Context.getPatientService().getPatientByUuid(cagVisit.getAttender().getUuid());
		Integer attenderId = attender.getPatientId();
		cagVisit.setPatientId(attenderId);
		
		CagVisit savedCagVisit = dao.saveCagVisit(cagVisit);
		
		Map<String, String> absentees = cagVisit.getAbsentees();
		if (!absentees.isEmpty())
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
		
		Set<Visit> visits = cagVisit.getVisits();
		CagEncounter cagEncounter = new CagEncounter();
		cagEncounter.setCag(cagVisit.getCag());
		cagEncounter.setCagVisit(savedCagVisit);
		cagEncounter.setAttender(cagVisit.getAttender());
		cagEncounter.setCagEncounterDateTime(cagVisit.getDateStarted());
		
		for (Visit currentVisit : visits) {
			cagEncounter.getEncounters().add(currentVisit.getNonVoidedEncounters().get(0));
		}
		
		saveCagEncounter(cagEncounter);
		
		return getCagVisitByUuid(savedCagVisit.getUuid());
	}
	
	@Override
	public CagVisit getCagVisitByUuid(String uuid) {
		CagVisit retrievedCagVisit = dao.getCagVisitByUuid(uuid);
		
		System.out.println("\n\n" + retrievedCagVisit + "\n\n");
		
		String displayed = dao.getCagById(retrievedCagVisit.getCagId()).getName() + " @ "
		        + retrievedCagVisit.getLocationName() + " - " + retrievedCagVisit.getDateStarted();
		retrievedCagVisit.setDisplay(displayed);
		
		Patient attender = Context.getPatientService().getPatient(retrievedCagVisit.getPatientId());
		Visit attenderActiveVisitList = Context.getVisitService().getActiveVisitsByPatient(attender).get(0);
		retrievedCagVisit.setAttenderVisit(attenderActiveVisitList);
		
		Map<String, String> absentees = getAbsentees(retrievedCagVisit);
		retrievedCagVisit.setAbsentees(absentees);
		
		List<Patient> presentPatients = getPresentPatients(absentees.keySet(), retrievedCagVisit.getCagId());
		
		for (Patient currentPatient : presentPatients) {
			if (currentPatient.getUuid() != attender.getUuid()) {
				Visit patientVisit = Context.getVisitService().getActiveVisitsByPatient(currentPatient).get(0);
				retrievedCagVisit.getOtherMemberVisits().add(patientVisit);
			}
			
		}
		
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
			throw new ParseException("Date ParseException encountered!", 1);
		}
		finally {
			
			CagVisit cagVisit = dao.closeCagVisit(uuid, dateStopped);
			
			Map<String, String> absentees = getAbsentees(cagVisit);
			
			Set<String> absenteeUuidSet = absentees.keySet();
			Integer cagId = cagVisit.getCagId();
			Date visitStartDate = cagVisit.getDateStarted();
			
			//			List<Visit> presentPatientVisits = closePatientVisits(absenteeUuidSet, cagId, visitStartDate, dateStopped);
			
			return cagVisit;
		}
	}
	
	@Override
	public void deleteCagVisit(String uuid) {
		CagVisit cagVisit = getCagVisitByUuid(uuid);
		
		dao.deleteCagVisit(cagVisit.getId());
	}
	
	@Override
	public CagEncounter getCagEncounterByUuid(String uuid) {
		
		System.out.println("About to fetch CAG Encounter : " + uuid);
		CagEncounter cagEncounter = dao.getCagEncounterByUuid(uuid);
		System.out.println("Fetched CAG Encounter : " + cagEncounter.getCag());
		
		return cagEncounter;
	}
	
	@Override
	public CagEncounter saveCagEncounter(CagEncounter cagEncounter) {
		cagEncounter.setCreator(Context.getAuthenticatedUser());
		dao.saveCagEncounter(cagEncounter);
		
		Set<Encounter> encounters = cagEncounter.getEncounters();
		for (Encounter currentEncounter : encounters) {
			dao.saveCagPatientEncounter(currentEncounter);
			
			Set<Obs> topLevelObs = currentEncounter.getObsAtTopLevel(false);
			for (Obs currentObs : topLevelObs) {
				currentObs.setEncounter(currentEncounter);
				Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
				
			}
			if (!currentEncounter.getOrders().isEmpty()) {
				Set<Order> orders = currentEncounter.getOrders();
				Object[] ordersArray = orders.toArray();
				
				for (int index = 0; index < ordersArray.length; index += 2) {
					Order currentOrder = (Order) ordersArray[index];
					currentOrder.setInstructions("As directed");
					Context.getOrderService().saveOrder(currentOrder, new OrderContext());
				}
			}
			
		}
		
		CagEncounter cagEncounter1 = getCagEncounterByUuid(cagEncounter.getUuid());
		
		return cagEncounter1;
	}
	
	@Override
	public void deleteCagEncounter(String uuid) {
		
	}
	
}
