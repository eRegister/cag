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
		
		//		String displayed = dao.getCagById(cagEncounter.getCagId()).getName() + " @ "
		//		        + Context.getLocationService().getLocation(cagEncounter.getLocationId()) + " - From "
		//		        + cagEncounter.getDateCreated() + " - " + cagEncounter.getNextEncounterDate();
		
		return cagEncounter;
	}
	
	public Set<Obs> copyGroupMembers(Obs currentObs, Patient currentPatient) {
		Set<Obs> newGroupMembers = new HashSet<Obs>();
		
		Set<Obs> currentObsGroupMembers = currentObs.getGroupMembers(false);
		for (Obs currentObsGroupMember : currentObsGroupMembers) {
			Integer conceptId = currentObsGroupMember.getConcept().getConceptId();
			
			if (COPABLE_CONCEPTS.contains(conceptId)) {
				
				Obs newObs = new Obs(currentPatient, currentObsGroupMember.getConcept(), currentObs.getObsDatetime(),
				        currentObs.getLocation());
				//				newObs.setValueText(currentObsGroupMember.getValueText());
				//				newObs.setValueNumeric(currentObsGroupMember.getValueNumeric());
				//				newObs.setValueDatetime(currentObsGroupMember.getValueDatetime());
				//				newObs.setValueCoded(currentObsGroupMember.getValueCoded());
				//				newObs.setValueCodedName(currentObsGroupMember.getValueCodedName());
				
				if (conceptId == 3843) {
					newObs.setConcept(new Concept(3841));
					
				} else if (conceptId == 2250) {
					try {
						List<Obs> artRegimenObs = Context.getObsService().getObservationsByPersonAndConcept(currentPatient,
						    currentObs.getConcept());
						//						System.out.println("For patient : " + currentPatient + " \n artRegimenObs : "
						//								+ artRegimenObs.toString());
						
						Concept previousValueConded = artRegimenObs.get(0).getValueCoded();
						//						System.out.println("previousValueConded : " + previousValueConded);
						
						newObs.setValueCoded(previousValueConded);
						
					}
					catch (APIException e) {
						System.out.println("CAG Member (" + currentPatient.getNames()
						        + ") has no previous ART Consultation (Intake / Followup)");
					}
					
				}
				
				if (currentObsGroupMember.hasGroupMembers())
					newObs.setGroupMembers(copyGroupMembers(currentObsGroupMember, currentPatient));
				
				newGroupMembers.add(newObs);
			}
			
		}
		
		return newGroupMembers;
	}
	
	@Override
	public CagEncounter saveCagEncounter(CagEncounter cagEncounter) {
		
		Integer cagId = Context.getService(CagService.class).getCagByUuid(cagEncounter.getCag().getUuid()).getId();
		CagVisit cagVisit = getCagVisitByUuid(cagEncounter.getCagVisit().getUuid());
		
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
			System.out.println("Current patient is : " + currentPatient);
			if (!(currentPatient.getUuid() == attenderEncounter.getPatient().getUuid())) {
				
				Encounter currentEncounter = new Encounter();
				currentEncounter.setEncounterType(attenderEncounter.getEncounterType());
				
				String visitDate = formatDateTime(cagVisit.getDateStarted());
				Visit currentVisit = dao.getVisit(currentPatient, visitDate);
				currentEncounter.setVisit(currentVisit);
				
				currentEncounter.setLocation(attenderEncounter.getLocation());
				currentEncounter.setForm(attenderEncounter.getForm());
				currentEncounter.setPatient(currentPatient);
				currentEncounter.setEncounterDatetime(attenderEncounter.getEncounterDatetime());
				currentEncounter.setCreator(attenderEncounter.getCreator());
				dao.saveCagPatientEncounter(currentEncounter);
				
				Set<Obs> obsSet = attenderEncounter.getObs();
				int count = 1;
				//				==============================
				//				Obs obsForm = new Obs(currentPatient, )
				//				=============================
				for (Obs currentObs : obsSet) {
					System.out.println("other patient obs : " + count + " : " + currentObs.getConcept().getId() + " : "
					        + currentObs.getConcept().getUuid());
					Integer conceptId = currentObs.getConcept().getId();
					
					if (conceptId == 3843) {
						
						currentObs.getValueCoded().setUuid("0f880c52-3ced-43ac-a79b-07a2740ae428");
						currentObs.setEncounter(currentEncounter);
						//						Person person = Context.getPersonService().getPersonByUuid(currentPatient.getUuid());
						currentObs.setPerson(currentPatient);
						Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
						
					} else if (conceptId == 2250) {
						try {
							List<Obs> artRegimenObs = Context.getObsService().getObservationsByPersonAndConcept(
							    currentPatient, currentObs.getConcept());
							System.out.println("For patient : " + currentPatient + " \n artRegimenObs : "
							        + artRegimenObs.toString());
							
							Concept previousValueConded = artRegimenObs.get(0).getValueCoded();
							System.out.println(previousValueConded);
							
							currentObs.setValueCoded(previousValueConded);
							currentObs.setEncounter(currentEncounter);
							currentObs.setPerson(currentPatient);
							Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
							
						}
						catch (APIException e) {
							System.out.println("CAG Member (" + currentPatient.getNames()
							        + ") has no previous ART Consultation (Intake / Followup)");
						}
						
					} else if (conceptId == 3751 || conceptId == 3752 || conceptId == 4174 || conceptId == 3730) {
						
						currentObs.setEncounter(currentEncounter);
						currentObs.setPerson(currentPatient);
						Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
						
					}
					count += 1;
					
				}
				
				if (!attenderEncounter.getOrders().isEmpty()) {
					
					Set<Order> orders = attenderEncounter.getOrders();
					Object[] ordersArray = orders.toArray();
					System.out.println("ordersArray.toString() : " + ordersArray.length);
					
					for (int index = 0; index < ordersArray.length; index += 2) {
						DrugOrder currentOrder = (DrugOrder) ordersArray[index];
						System.out.println("Looping through orders for patient " + currentPatient);
						
						List<Order> orderList = Context.getOrderService().getOrders(currentPatient,
						    currentOrder.getCareSetting(), currentOrder.getOrderType(), false);
						System.out.println("Previous orders for : " + currentPatient + " are : " + orderList);
						
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
						newOrder.setNumRefills(currentOrder.getNumRefills());
						newOrder.setDosingInstructions(currentOrder.getDosingInstructions());
						newOrder.setDuration(currentOrder.getDuration());
						newOrder.setDurationUnits(currentOrder.getDurationUnits());
						newOrder.setAction(currentOrder.getAction());
						
						if (!orderList.isEmpty()) {
							DrugOrder previousOrder = (DrugOrder) orderList.get(0);
							
							newOrder.setDose(previousOrder.getDose());
							newOrder.setDoseUnits(previousOrder.getDoseUnits());
							newOrder.setFrequency(previousOrder.getFrequency());
							newOrder.setQuantityUnits(previousOrder.getQuantityUnits());
							newOrder.setDrug(previousOrder.getDrug());
							newOrder.setRoute(previousOrder.getRoute());
							newOrder.setQuantity(computeQty(newOrder));
							
							System.out.println("\nNEW Order, Details from previous order \n" + newOrder);
							Context.getOrderService().saveOrder(newOrder, new OrderContext());
							
						} else {
							
							newOrder.setDose(currentOrder.getDose());
							newOrder.setDoseUnits(currentOrder.getDoseUnits());
							newOrder.setFrequency(currentOrder.getFrequency());
							newOrder.setQuantityUnits(currentOrder.getQuantityUnits());
							newOrder.setDrug(currentOrder.getDrug());
							newOrder.setDrug(currentOrder.getDrug());
							newOrder.setRoute(currentOrder.getRoute());
							newOrder.setAction(currentOrder.getAction());
							newOrder.setQuantity(computeQty(newOrder));
							
							System.out.println("\nNEW Order \n" + newOrder);
							Context.getOrderService().saveOrder(newOrder, new OrderContext());
							System.out.println("Successfully saved an order !!!!!!");
							
						}
					}
				}
			} else {
				int count = 1;
				for (Obs currentObs : attenderEncounter.getObs()) {
					System.out.println(count);
					currentObs.setEncounter(attenderEncounter);
					Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
					count += 1;
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
		
		System.out.println("When trying to get encounter by uuid : " + cagEncounter.getUuid());
		CagEncounter cagEncounter1 = getCagEncounterByUuid(cagEncounter.getUuid());
		System.out.println("\nFinally!!!!!!!!!1\n" + cagEncounter1);
		
		return cagEncounter1;
	}
	
	@Override
	public void deleteCagEncounter(String uuid) {
		
	}
	
	public String formatDateTime(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = simpleDateFormat.format(date);
		
		return dateString;
	}
	
	public CagEncounter saveCagEncounterBackup(CagEncounter cagEncounter) {
		
		Integer cagId = Context.getService(CagService.class).getCagByUuid(cagEncounter.getCag().getUuid()).getId();
		CagVisit cagVisit = getCagVisitByUuid(cagEncounter.getCagVisit().getUuid());
		
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
			System.out.println("Current patient is : " + currentPatient);
			if (!(currentPatient.getUuid() == attenderEncounter.getPatient().getUuid())) {
				
				Encounter currentEncounter = new Encounter();
				currentEncounter.setEncounterType(attenderEncounter.getEncounterType());
				
				String visitDate = formatDateTime(cagVisit.getDateStarted());
				Visit currentVisit = dao.getVisit(currentPatient, visitDate);
				currentEncounter.setVisit(currentVisit);
				
				currentEncounter.setLocation(attenderEncounter.getLocation());
				currentEncounter.setForm(attenderEncounter.getForm());
				currentEncounter.setPatient(currentPatient);
				currentEncounter.setEncounterDatetime(attenderEncounter.getEncounterDatetime());
				currentEncounter.setCreator(attenderEncounter.getCreator());
				dao.saveCagPatientEncounter(currentEncounter);
				
				Set<Obs> obsSet = attenderEncounter.getObsAtTopLevel(false);
				int count = 1;
				
				for (Obs currentObs : obsSet) {
					System.out.println("other patient obs : " + count + " : " + currentObs.getConcept().getId() + " : "
					        + currentObs.getConcept().getUuid());
					Integer conceptId = currentObs.getConcept().getId();
					Obs newObs = new Obs(currentPatient, currentObs.getConcept(), currentObs.getObsDatetime(),
					        currentObs.getLocation());
					
					currentObs.setEncounter(currentEncounter);
					currentObs.setPerson(currentPatient);
					
					//					Make call with curr Obs, curr person
					
					if (conceptId == 3843) {
						
						currentObs.getValueCoded().setUuid("0f880c52-3ced-43ac-a79b-07a2740ae428");
						System.out.println("About to save obs");
						Person person = Context.getPersonService().getPersonByUuid(currentPatient.getUuid());
						
						Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
						
						System.out.println("After to save obs");
						
					} else if (conceptId == 2250) {
						try {
							List<Obs> artRegimenObs = Context.getObsService().getObservationsByPersonAndConcept(
							    currentPatient, currentObs.getConcept());
							System.out.println("For patient : " + currentPatient + " \n artRegimenObs : "
							        + artRegimenObs.toString());
							
							Concept previousValueConded = artRegimenObs.get(0).getValueCoded();
							System.out.println(previousValueConded);
							
							currentObs.setValueCoded(previousValueConded);
							Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
							
						}
						catch (APIException e) {
							System.out.println("CAG Member (" + currentPatient.getNames()
							        + ") has no previous ART Consultation (Intake / Followup)");
						}
						
					} else if (conceptId == 3751 || conceptId == 3752 || conceptId == 4174 || conceptId == 3730) {
						Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
						
					} else if (conceptId == 2403) {
						System.out.println("\nProcessing Top Level Observation!!!\n");
						Context.getObsService().saveObs(currentObs, "HIV Treatment and Care Progress Template Observation");
					}
				}
				
				if (!attenderEncounter.getOrders().isEmpty()) {
					
					Set<Order> orders = attenderEncounter.getOrders();
					Object[] ordersArray = orders.toArray();
					System.out.println("ordersArray.toString() : " + ordersArray.length);
					
					for (int index = 0; index < ordersArray.length; index += 2) {
						DrugOrder currentOrder = (DrugOrder) ordersArray[index];
						
						System.out.println("Looping through orders for patient " + currentPatient);
						
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
							
							System.out.println("Successfully saved an order !!!!!!");
						}
					}
				}
			} else {
				int count = 1;
				for (Obs currentObs : attenderEncounter.getObsAtTopLevel(false)) {
					System.out.println("Attender Obs: " + count + " : " + currentObs + " : " + currentObs.getConcept()
					        + " : " + currentObs.getPerson());
					currentObs.setEncounter(attenderEncounter);
					currentObs.setPerson(currentPatient);
					Context.getObsService().saveObs(currentObs, "Saving Cag member Observation");
					count += 1;
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
		
		System.out.println("When trying to get encounter by uuid : " + cagEncounter.getUuid());
		CagEncounter cagEncounter1 = getCagEncounterByUuid(cagEncounter.getUuid());
		System.out.println("\nFinally!!!!!!!!!1\n" + cagEncounter1);
		
		return cagEncounter1;
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
		
		String VisitTime = formatDateTime(cagVisit.getDateStarted());
		
		for (Patient presentPatient : cagPatientList) {
			if (!absenteeUuidSet.contains(presentPatient.getUuid())) {
				Visit visit = dao.getVisit(presentPatient, VisitTime);
				presentPatientVisits.add(visit);
			}
		}
		return presentPatientVisits;
	}
	
}
