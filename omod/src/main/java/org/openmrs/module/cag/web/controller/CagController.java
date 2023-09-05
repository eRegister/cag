/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.cag.web.controller;

import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.cag.api.CagService;
import org.openmrs.module.cag.cag.Cag;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/${rootArtifactid}/${rootArtifactid}Link.form'.
 */

//@Component
@Controller
@RequestMapping("/rest1/" + RestConstants.VERSION_1 + CagController.CAG_NAMESPACE)
public class CagController extends MainResourceController {
	
	public static final String CAG_NAMESPACE = "/cag";
	//
	//	@RequestMapping(value = "/save", method = RequestMethod.POST)
	//	public @ResponseBody
	//	String saveCag(@RequestBody Cag cag) throws Exception {
	//
	//		if (cag != null) {
	//			Context.getService(CagService.class).saveCag(cag);
	//			return "Saved successfully..." + Context.getService(CagService.class).getCagByUuid(cag.getUuid());
	//		} else {
	//			//			System.out.println("Failed to save cag...");
	//			return "Failed to save cag is null...";
	//		}
	//	}
	//
	//	@Override
	//	public String getNamespace() {
	//		return RestConstants.VERSION_1 + "/cag";
	//	}
}