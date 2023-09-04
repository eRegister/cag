package org.openmrs.module.cag.api;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.cag.cag.CagPatient;
import org.springframework.transaction.annotation.Transactional;

public interface CagService extends OpenmrsService {
	
	//	Methods acting on Cag
	//	@Transactional(readOnly = true)
	Cag getCagById(Integer cagId);
	
	//	@Transactional(readOnly = true)
	Cag getCagByUuid(String uuid);
	
	//	@Transactional(readOnly = true)
	List<Cag> getCagList();
	
	//	@Transactional
	void saveCag(Cag cag);
	
	//	@Transactional
	void voidCag(Cag cag);
	
	//	Handles CagPatient Transactions
	//	@Transactional(readOnly = true)
	CagPatient getCagPatientById(Integer cagPatientId); //find patient method
	
	//	@Transactional(readOnly = true)
	List<Patient> getCagPatientList(Integer id); //read cag patients as list
	
	//	@Transactional
	Patient saveCagPatient(CagPatient cagPatient); //save cag patient
	
	//	@Transactional
	void deletePatientFromCag(String uuid); //set status for cag member
	
	//	@Transactional
	void deleteCag(String uuid);
	
	Cag updateCag(Cag cag);
	
}
