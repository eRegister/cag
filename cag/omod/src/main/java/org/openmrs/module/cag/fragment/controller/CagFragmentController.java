package org.openmrs.module.cag.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;

public class CagFragmentController {
	
	public void controller(FragmentModel model, @SpringBean("cag.CagService") CagService cagService) {
		// CagService cagService = Context.getService(CagService.class);
		Cag cag = cagService.getCagById(1);
		model.addAttribute("cag", cag);
	}
}
