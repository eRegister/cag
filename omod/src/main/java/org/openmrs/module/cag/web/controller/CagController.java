package org.openmrs.module.cag.web.controller;

import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.api.impl.CagServiceImpl;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.util.SimpleObjectConverter;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/" + RestConstants.VERSION_1 + "/cag")
public class CagController extends MainResourceController {
	
	//	@RequestMapping(value = "/cag", method = RequestMethod.GET)
	//	public @ResponseBody
	//	SimpleObject getAllCags() {
	//		SimpleObject simpleObject = new SimpleObject();
	//
	//		simpleObject.add("cags", Context.getService(CagService.class).getCagList());
	//		return simpleObject;
	//	}
	//
	@Override
	public String getNamespace() {
		return RestConstants.VERSION_1 + "/cag";
	}
}
