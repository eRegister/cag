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
package org.openmrs.module.appointments.service.impl;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.module.appointments.Appointment;
import org.openmrs.module.appointments.db.AppointmentDAO;
import org.openmrs.module.appointments.service.AppointmentService;


/**
 *
 */
public class AppointmentServiceImpl implements AppointmentService{

	private AppointmentDAO appointmentDAO;
	/**
     * @see org.openmrs.module.appointments.service.AppointmentService#getAllAppointments()
     */
    public List<Appointment> getAllAppointments() {
	    return appointmentDAO.getAllAppointments();
    }

	/**
     * @see org.openmrs.module.appointments.service.AppointmentService#getAppointment(int)
     */
    public Appointment getAppointment(int appointmentId) {
	    return appointmentDAO.getAppointment(appointmentId);
    }

	/**
     * @see org.openmrs.module.appointments.service.AppointmentService#updateAppointment(org.openmrs.module.appointments.Appointment)
     */
    public void updateAppointment(Appointment appointment) {
	    appointmentDAO.updateAppointment(appointment);
	    
    }

	public void setAppointmentDAO(AppointmentDAO appointmentDAO) {
	    this.appointmentDAO = appointmentDAO;
    }

	public AppointmentDAO getAppointmentDAO() {
	    return appointmentDAO;
    }

	public List<Appointment> getAppointmentsForPatient(Patient patient){
		return appointmentDAO.getAppointmentsForPatient(patient);
	}
	
	/**
     * @see org.openmrs.module.appointments.service.AppointmentService#saveAppointment(org.openmrs.module.appointments.Appointment)
     */
    public void saveAppointment(Appointment appointment) {
	    this.appointmentDAO.addAppointment(appointment);
	    
    }

}
