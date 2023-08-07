package org.openmrs.module.cag.fragment.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

public class CagFragmentController {
	
	public void controller(FragmentModel model, @SpringBean("cag.CagService") CagService cagService) {
		// CagService cagService = Context.getService(CagService.class);
		
		Cag newCag = new Cag();
		newCag.setName("Test Cag name");
		newCag.setDescription("Test Cag description");
		newCag.setConstituency("Ngong");
		newCag.setVillage("Ngong");
		newCag.setUuid(UUID.randomUUID().toString());
		newCag.setVoided(false);
		User currentUser = Context.getAuthenticatedUser();
		User anotherUser = Context.getUserService().getUser(2);
		newCag.setCreator(currentUser);
		newCag.setDateCreated(new Date());
		newCag.setChangedBy(anotherUser);
		newCag.setDateChanged(new Date());
		
		cagService.saveCag(newCag);
		
		Cag cag = cagService.getCagById(1);
		model.addAttribute("cag", cag);
		
		List<Cag> cagsList = cagService.getCagList();
		model.addAttribute("cagslist", cagsList);
		
		cag.setVoided(true);
		cag.setVoidedBy(currentUser);
		cag.setVoidReason("Duplicate record");
		cagService.saveCag(cag);
	}
}
