package org.openmrs.module.cag.api.db;

import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.cag.CagEncounter;
import org.openmrs.module.cag.cag.CagPatient;
import org.openmrs.module.cag.cag.CagVisit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cag.CagDao")
public interface CagDao {
	
	public Cag getCagById(Integer cagId);
	
	public List<Cag> getCagList();
	
	public void saveCag(Cag cag);
	
	public Cag getCagByUuid(String uuid);
	
	public Cag updateCag(Cag cag);
	
	public CagPatient getCagPatientById(Integer cagPatientId);
	
	public List<Patient> getCagPatientList(Integer cagId);
	
	public void saveCagPatient(CagPatient cagPatient);
	
	void deletePatientFromCag(Integer patientId);
	
	public void deleteCag(String uuid);
	
	public List<Integer> getPatientIdList(Integer cagId);
	
	void clearCag(Integer cagId);
	
	public CagPatient getCagPatientByUuid(String uuid);
	
	Integer saveCagVisit(CagVisit cagVisit);
	
	CagVisit getCagVisitByUuid(String uuid);
	
	void deleteCagVisit(Integer visitId);
	
	CagVisit updateCagVisit(CagVisit cagVisit);
	
	void createMapping(CagVisit cagVisit);
	
	List<Visit> getCagVisits(Integer cagId);
	
	CagEncounter getCagEncounterByUuid(String uuid);
	
	Integer saveCagEncounter(CagEncounter cagEncounter);
	
	void deleteCagEncounter(String uuid);
	
}
