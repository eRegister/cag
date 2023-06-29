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
package org.openmrs.module.appointments;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.User;


/**
 *
 */
public class Appointment {
	protected Log log = LogFactory.getLog(this.getClass());
	
	protected Integer appointmentId;
	protected Patient patient;
	protected Date when;
	protected User provider;
	protected Location location;
	protected String reason;	
     
    /**
     * @return the appointmentId
     */
    public Integer getAppointmentId() {
    	return appointmentId;
    }
	
    /**
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(Integer appointmentId) {
    	this.appointmentId = appointmentId;
    }

	/**
     * @return the location
     */
    public Location getLocation() {
    	return location;
    }
	
    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
    	this.location = location;
    }

	/**
     * @return the patient
     */
    public Patient getPatient() {
    	return patient;
    }
	
    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
    	this.patient = patient;
    }
	
    /**
     * @return the when
     */
    public Date getWhen() {
    	return when;
    }
	
    /**
     * @param when the when to set
     */
    public void setWhen(Date when) {
    	this.when = when;
    }
	
    /**
     * @return the provider
     */
    public User getProvider() {
    	return provider;
    }
	
    /**
     * @param provider the provider to set
     */
    public void setProvider(User provider) {
    	this.provider = provider;
    }
	
    /**
     * @return the reason
     */
    public String getReason() {
    	return reason;
    }
	
    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
    	this.reason = reason;
    }
}
