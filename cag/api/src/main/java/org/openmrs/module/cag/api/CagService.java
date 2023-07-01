package org.openmrs.module.cag.api;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.cag.cag.Cag;
import org.springframework.transaction.annotation.Transactional;

public interface CagService extends OpenmrsService {
	
	@Transactional(readOnly = true)
	Cag getCagById(Integer cagId);
	
	@Transactional(readOnly = true)
	Cag getCagByUuid(String uuid);
	
	@Transactional(readOnly = true)
	List<Cag> getCagList();
	
	@Transactional
	void saveCag(Cag cag);
	
	@Transactional
	void voidCag(Cag cag);
	
}
