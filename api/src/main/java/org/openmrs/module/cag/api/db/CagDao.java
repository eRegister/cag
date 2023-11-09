package org.openmrs.module.cag.api.db;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.module.cag.cag.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("cag.CagDao")
public interface CagDao {
	
	Cag getCagById(Integer cagId);
	
	List<Cag> getCagList();
	
	void saveCag(Cag cag);
	
	Cag getCagByUuid(String uuid);
	
	Cag updateCag(Cag cag);
	
	CagPatient getCagPatientById(Integer cagPatientId);
	
	List<Patient> getCagPatientList(Integer cagId);
	
	void saveCagPatient(CagPatient cagPatient);
	
	void deletePatientFromCag(Integer patientId);
	
	void deleteCag(String uuid);
	
	List<Integer> getPatientIdList(Integer cagId);
	
	void clearCag(Integer cagId);
	
	CagPatient getCagPatientByUuid(String uuid);
	
	CagVisit saveCagVisit(CagVisit cagVisit);
	
	CagVisit getCagVisitByUuid(String uuid);
	
	CagVisit getCagVisitById(Integer id);
	
	void deleteCagVisit(Integer visitId);
	
	CagVisit closeCagVisit(String cagVisitUuid, String dateStopped);
	
	List<Visit> getCagVisits(Integer cagId);
	
	Visit getVisit(Patient patient, String dateStarted);
	
	void saveAbsentCagPatient(Absentee absentee);
	
	List<Absentee> getAbsenteeList(Integer visitId);
	
	void closeCagPatientVisit(Patient patient, String startDate, String dateStopped);
	
	//	void closeCagPatientVisit(String uuid, Date dateStopped);
	
	CagEncounter getCagEncounterByUuid(String uuid);
	
	void saveCagEncounter(CagEncounter cagEncounter);
	
	void saveCagPatientEncounter(Encounter encounter);
	
	void deleteCagEncounter(String uuid);
	
	public List<CagPatient> getAllCagPatients();
	
}
