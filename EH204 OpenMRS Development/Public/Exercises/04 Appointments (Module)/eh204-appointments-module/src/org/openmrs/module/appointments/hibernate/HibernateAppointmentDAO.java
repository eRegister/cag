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
package org.openmrs.module.appointments.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.module.appointments.Appointment;
import org.openmrs.module.appointments.db.AppointmentDAO;


/**
 *
 */
public class HibernateAppointmentDAO implements AppointmentDAO{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private SessionFactory sessionFactory;
	/**
     * @see org.openmrs.module.appointments.db.AppointmentDAO#getAllAppointments()
     */
    @SuppressWarnings("unchecked")
    public List<Appointment> getAllAppointments() {
	    Session session = sessionFactory.getCurrentSession();
	    List<Appointment> list = session.createCriteria(Appointment.class).addOrder(Order.asc("when")).list();
	    
	    return list;
	}
    
    @SuppressWarnings("unchecked")
    public List<Appointment> getAppointmentsForPatient(Patient patient) {
	    Session session = sessionFactory.getCurrentSession();
	    
	    List<Appointment> list = session.
	    	createCriteria(Appointment.class).
	    	createCriteria("patient").
	    	add(Restrictions.idEq(patient.getPatientId())).list();
	    
	    return list;
	}

	/**
     * @see org.openmrs.module.appointments.db.AppointmentDAO#getAppointment(int)
     */
    public Appointment getAppointment(int appointmentId) {
	    Session session = sessionFactory.getCurrentSession();
	    Appointment appointment = (Appointment)session.load(Appointment.class, appointmentId);
	    
	    return appointment;
    }

	/**
     * @see org.openmrs.module.appointments.db.AppointmentDAO#updateAppointment(org.openmrs.module.appointments.Appointment)
     */
    public void updateAppointment(Appointment appointment) {
	    Session session = sessionFactory.getCurrentSession();
	    session.save(appointment);
    }

	
    public SessionFactory getSessionFactory() {
    	return sessionFactory;
    }

	
    public void setSessionFactory(SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
    }

	/**
     * @see org.openmrs.module.appointments.db.AppointmentDAO#addAppointment(org.openmrs.module.appointments.Appointment)
     */
    public void addAppointment(Appointment appointment) {
    	logger.info(" about to save appointment for " + appointment.getPatient().getGivenName());
	    sessionFactory.getCurrentSession().save(appointment);
	    logger.info(" about to save appointment for " + appointment.getPatient().getGivenName());
    }

}
