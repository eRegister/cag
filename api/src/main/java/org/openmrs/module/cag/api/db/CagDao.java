package org.openmrs.module.cag.api.db;

import org.openmrs.Patient;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.cag.CagPatient;
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
	
	public void deletePatientFromCag(CagPatient cagPatient);
	
	public void deleteCag(String uuid);
	
	public List<Integer> getPatientIdList(Integer cagId);
}
