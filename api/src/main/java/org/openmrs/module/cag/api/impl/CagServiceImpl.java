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
		
		Cag cag = Context.getService(CagService.class).getCagByUuid(cagVisit.getCag().getUuid());
		Integer cagId = cag.getId();
		cagVisit.setCagId(cagId);
		cagVisit.setCreator(Context.getAuthenticatedUser());
		
		Patient attender = Context.getPatientService().getPatientByUuid(cagVisit.getAttenderVisit().getPatient().getUuid());
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
		
		String loggedInLocation = cagVisit.getLocationName();
		Date dateStarted = cagVisit.getDateStarted();
		
		createCagPatientVisitList(absentees.keySet(), loggedInLocation, cagId, dateStarted, cagVisit.getAttenderVisit());
		
		return getCagVisitByUuid(savedCagVisit.getUuid());
	}
	
	public void createCagPatientVisitList(Set<String> absentees, String locationName, Integer cagId, Date dateStarted,
	        Visit attenderVisit) {
		
		VisitType visitType = Context.getVisitService().getVisitType(10);
		List<Patient> presentPatients = getPresentPatients(absentees, cagId);
		
		Object[] attenderEncounters = attenderVisit.getEncounters().toArray();
		Encounter attenderEncounter = (Encounter) attenderEncounters[0];
		System.out.println("Attender Encounter : " + attenderEncounter);
		
		for (Patient currentPatient : presentPatients) {
			Visit visit = new Visit(currentPatient, visitType, dateStarted);
			visit.setLocation(Context.getLocationService().getLocation(locationName));
			Visit savedVisit = Context.getVisitService().saveVisit(visit);
			
			if (currentPatient.getUuid() != attenderVisit.getPatient().getUuid()) {
				Encounter currentEncounter = getEncounter(currentPatient, savedVisit, attenderEncounter);
				System.out.println("\nNOT CAG VISIT ATTENDER ::: Encounter:\n" + currentEncounter);
				dao.saveCagPatientEncounter(currentEncounter);
				
			} else {
				System.out.println("\n CAG VISIT ATTENDER Encounter:\n" + attenderEncounter);
				dao.saveCagPatientEncounter(attenderEncounter);
				
				System.out.println("Passed saveCagPatientEncounter(attender)");
				
				int count = 1;
				for (Obs currentObs : attenderEncounter.getObs()) {
					System.out.println(count);
					attenderEncounter.setVisit(savedVisit);
					currentObs.setEncounter(attenderEncounter);
					Context.getObsService().saveObs(currentObs, "Saving Cag member Vital Sign Observation");
					count += 1;
				}
			}
		}
	}
	
	private Encounter getEncounter(Patient currentPatient, Visit savedVisit, Encounter attenderEncounter) {
		
		Encounter currentEncounter = new Encounter();
		currentEncounter.setEncounterType(attenderEncounter.getEncounterType());
		currentEncounter.setVisit(savedVisit);
		currentEncounter.setLocation(attenderEncounter.getLocation());
		currentEncounter.setForm(attenderEncounter.getForm());
		currentEncounter.setPatient(currentPatient);
		currentEncounter.setEncounterDatetime(attenderEncounter.getEncounterDatetime());
		currentEncounter.setCreator(attenderEncounter.getCreator());
		return currentEncounter;
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
		//		retrievedCagVisit.setVisitList(attenderActiveVisitList);
		
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
		CagEncounter cagEncounter = dao.getCagEncounterByUuid(uuid);
		
		String displayed = dao.getCagById(cagEncounter.getCagId()).getName() + " @ "
		        + Context.getLocationService().getLocation(cagEncounter.getLocationId()) + " - From "
		        + cagEncounter.getDateCreated() + " - " + cagEncounter.getNextEncounterDate();
		
		return cagEncounter;
	}
	
	@Override
	public CagEncounter saveCagEncounter(CagEncounter cagEncounter) {
		
		Integer cagId = Context.getService(CagService.class).getCagByUuid(cagEncounter.getCagUuid()).getId();
		CagVisit cagVisit = getCagVisitByUuid(cagEncounter.getCagVisitUuid());
		
		System.out.println("\nOrderset FIIIIIIIIIIIIRRRRRRRRRSSSSSSSSSSSSTTTTT : \n"
		        + cagEncounter.getEncounter().getOrders().toString());
		
		cagEncounter.setCagId(cagId);
		cagEncounter.setCagVisitId(cagVisit.getId());
		cagEncounter.setCreator(Context.getAuthenticatedUser());
		dao.saveCagEncounter(cagEncounter);
		
		Encounter attenderEncounter = cagEncounter.getEncounter();
		dao.saveCagPatientEncounter(attenderEncounter);
		
		Set<String> absenteesUuidSet = getAbsentees(cagVisit).keySet();
		List<Patient> presentPatients = getPresentPatients(absenteesUuidSet, cagId);
		
		for (Patient currentPatient : presentPatients) {
			
			if (!(currentPatient.getUuid() == attenderEncounter.getPatient().getUuid())) {
				
				Encounter currentEncounter = new Encounter();
				currentEncounter.setEncounterType(attenderEncounter.getEncounterType());
				currentEncounter.setVisit(dao.getVisit(currentPatient, cagVisit.getDateStarted().toString()));
				currentEncounter.setLocation(attenderEncounter.getLocation());
				currentEncounter.setForm(attenderEncounter.getForm());
				currentEncounter.setPatient(currentPatient);
				currentEncounter.setEncounterDatetime(attenderEncounter.getEncounterDatetime());
				currentEncounter.setCreator(attenderEncounter.getCreator());
				dao.saveCagPatientEncounter(currentEncounter);
				
				Set<Obs> obsSet = attenderEncounter.getObs();
				for (Obs currentObs : obsSet) {
					
					Integer conceptId = currentObs.getConcept().getId();
					
					if (conceptId == 3843) {
						
						currentObs.getValueCoded().setUuid("0f880c52-3ced-43ac-a79b-07a2740ae428");
						currentObs.setEncounter(currentEncounter);
						currentObs.setPerson(Context.getPersonService().getPersonByUuid(currentObs.getPerson().getUuid()));
						Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
						
					} else if (conceptId == 3751 || conceptId == 3752 || conceptId == 2250 || conceptId == 4174
					        || conceptId == 3730) {
						
						currentObs.setEncounter(currentEncounter);
						currentObs.setPerson(Context.getPersonService().getPersonByUuid(currentObs.getPerson().getUuid()));
						Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
						
					}
				}
				
				if (!attenderEncounter.getOrders().isEmpty()) {
					
					Set<Order> orders = attenderEncounter.getOrders();
					Object[] ordersArray = orders.toArray();
					
					for (int index = 0; index < ordersArray.length; index += 2) {
						DrugOrder currentOrder = (DrugOrder) ordersArray[index];
						
						List<Order> orderList = Context.getOrderService().getOrders(currentPatient,
						    currentOrder.getCareSetting(), currentOrder.getOrderType(), false);
						if (!orderList.isEmpty()) {
							int lastIndex = orderList.size() - 1;
							DrugOrder previousOrder = (DrugOrder) orderList.get(lastIndex);
							
							DrugOrder newOrder = new DrugOrder();
							newOrder.setPatient(currentPatient);
							newOrder.setEncounter(currentEncounter);
							newOrder.setOrderType(currentOrder.getOrderType());
							newOrder.setDateActivated(currentOrder.getDateActivated());
							newOrder.setAutoExpireDate(currentOrder.getAutoExpireDate());
							newOrder.setOrderer(currentOrder.getOrderer());
							newOrder.setUrgency(currentOrder.getUrgency());
							newOrder.setCareSetting(currentOrder.getCareSetting());
							newOrder.setScheduledDate(currentOrder.getScheduledDate());
							newOrder.setDose(previousOrder.getDose());
							newOrder.setDoseUnits(previousOrder.getDoseUnits());
							newOrder.setFrequency(previousOrder.getFrequency());
							newOrder.setQuantityUnits(previousOrder.getQuantityUnits());
							newOrder.setDrug(previousOrder.getDrug());
							newOrder.setNumRefills(currentOrder.getNumRefills());
							newOrder.setDosingInstructions(currentOrder.getDosingInstructions());
							newOrder.setDuration(currentOrder.getDuration());
							newOrder.setDurationUnits(currentOrder.getDurationUnits());
							newOrder.setRoute(previousOrder.getRoute());
							newOrder.setAction(currentOrder.getAction());
							newOrder.setQuantity(computeQty(newOrder));
							
							System.out.println("\nNEW Order, Details from previous order \n" + newOrder);
							
							Context.getOrderService().saveOrder(newOrder, new OrderContext());
						} else {
							DrugOrder newOrder = new DrugOrder();
							newOrder.setPatient(currentPatient);
							newOrder.setEncounter(currentEncounter);
							newOrder.setOrderType(currentOrder.getOrderType());
							newOrder.setDateActivated(currentOrder.getDateActivated());
							newOrder.setAutoExpireDate(currentOrder.getAutoExpireDate());
							newOrder.setOrderer(currentOrder.getOrderer());
							newOrder.setUrgency(currentOrder.getUrgency());
							newOrder.setCareSetting(currentOrder.getCareSetting());
							newOrder.setScheduledDate(currentOrder.getScheduledDate());
							newOrder.setDose(currentOrder.getDose());
							newOrder.setDoseUnits(currentOrder.getDoseUnits());
							newOrder.setFrequency(currentOrder.getFrequency());
							newOrder.setQuantityUnits(currentOrder.getQuantityUnits());
							newOrder.setDrug(currentOrder.getDrug());
							newOrder.setNumRefills(currentOrder.getNumRefills());
							newOrder.setDosingInstructions(currentOrder.getDosingInstructions());
							newOrder.setDuration(currentOrder.getDuration());
							newOrder.setDrug(currentOrder.getDrug());
							newOrder.setDurationUnits(currentOrder.getDurationUnits());
							newOrder.setRoute(currentOrder.getRoute());
							newOrder.setAction(currentOrder.getAction());
							newOrder.setQuantity(computeQty(newOrder));
							
							System.out.println("\nNEW Order \n" + newOrder);
							
							Context.getOrderService().saveOrder(newOrder, new OrderContext());
						}
					}
				}
			} else {
				for (Obs currentObs : attenderEncounter.getObs()) {
					currentObs.setEncounter(attenderEncounter);
					Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
					
				}
				if (!attenderEncounter.getOrders().isEmpty()) {
					Set<Order> orders = attenderEncounter.getOrders();
					Object[] ordersArray = orders.toArray();
					
					for (int index = 0; index < ordersArray.length; index += 2) {
						Order currentOrder = (Order) ordersArray[index];
						currentOrder.setPatient(currentPatient);
						currentOrder.setEncounter(attenderEncounter);
						
						Context.getOrderService().saveOrder(currentOrder, new OrderContext());
					}
				}
			}
		}
		
		return getCagEncounterByUuid(cagEncounter.getUuid());
	}
	
	public Double computeQty(DrugOrder order) {
		Double dose = order.getDose();
		Double frequency = order.getFrequency().getFrequencyPerDay();
		Integer duration = order.getDuration();
		
		Integer conceptId = order.getDurationUnits().getConceptId();
		Integer durationUnits = (conceptId == 76) ? 1 : (conceptId == 93) ? 7 : 30;
		
		Double qty = dose * frequency * duration * durationUnits;
		
		System.out.println("\nQuantity : " + qty + "\n");
		
		return qty;
	}
	
	public List<Visit> getPresentPatientVisits(CagVisit cagVisit) {
		List<Visit> presentPatientVisits = new ArrayList<Visit>();
		
		List<Patient> cagPatientList = getCagPatientList(cagVisit.getCagId());
		Set<String> absenteeUuidSet = getAbsentees(cagVisit).keySet();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String VisitTime = simpleDateFormat.format(cagVisit.getDateStarted());
		
		for (Patient presentPatient : cagPatientList) {
			if (!absenteeUuidSet.contains(presentPatient.getUuid())) {
				Visit visit = dao.getVisit(presentPatient, VisitTime);
				presentPatientVisits.add(visit);
			}
		}
		return presentPatientVisits;
	}
	
	@Override
	public void deleteCagEncounter(String uuid) {
		
	}
}
