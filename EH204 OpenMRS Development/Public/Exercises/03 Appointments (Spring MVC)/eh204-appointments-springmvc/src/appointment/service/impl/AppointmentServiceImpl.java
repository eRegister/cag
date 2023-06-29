package appointment.service.impl;

import java.util.List;

import appointment.Appointment;
import appointment.db.AppointmentDAO;
import appointment.service.AppointmentService;


/**
 * Implementation of the reservation service
 */
public class AppointmentServiceImpl implements AppointmentService {

	private AppointmentDAO appointmentDAO;
	
	/**
	 * Sets the data access object (called by Spring)
	 * @param reservationDAO the data access object
	 */
	public void setAppointmentDAO(AppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}
	
	/**
	 * @see appointment.service.AppointmentService#getAppointments()
	 */
	@Override
	public List<Appointment> getAppointments() {
		return appointmentDAO.getAppointments();
	}

	/**
	 * @see appointment.service.AppointmentService#saveAppointment(Appointment)
	 */
	@Override
	public void saveAppointment(Appointment appointment) {
		appointmentDAO.saveAppointment(appointment);
	}
}
