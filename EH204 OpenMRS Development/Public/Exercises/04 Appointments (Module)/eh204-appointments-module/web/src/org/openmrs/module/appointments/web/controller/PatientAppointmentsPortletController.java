/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.appointments.web.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.ServiceContext;
import org.openmrs.module.appointments.Appointment;
import org.openmrs.module.appointments.service.AppointmentService;
import org.openmrs.web.controller.PortletController;


/**
 *
 */
public class PatientAppointmentsPortletController extends PortletController {

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
     * @see org.openmrs.web.controller.PortletController#populateModel(javax.servlet.http.HttpServletRequest, java.util.Map)
     */
    @Override
    protected void populateModel(HttpServletRequest request, Map<String, Object> model) {
    	AppointmentService svc = (AppointmentService)ServiceContext.getInstance().getService(AppointmentService.class);
    	
    	Patient patient = (Patient)model.get("patient");
    	Collection<Appointment> appointments = svc.getAppointmentsForPatient(patient);
    	
    	log.info("loaded " + appointments.size() + " for patient " + patient.getPatientId());
    	
    	model.put("patientAppointments", appointments);
    }

}
