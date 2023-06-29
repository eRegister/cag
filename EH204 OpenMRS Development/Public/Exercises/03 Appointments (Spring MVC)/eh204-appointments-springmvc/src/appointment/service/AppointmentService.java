package appointment.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import appointment.Appointment;


/**
 * Reservation service
 */
@Transactional
public interface AppointmentService {
	/**
	 * Gets the appointments
	 * @return the appointments
	 */
	@Transactional(readOnly=true)
	public List<Appointment> getAppointments();
	
	/**
	 * Saves an appointment
	 * @param appointment the appointment
	 */
    public void saveAppointment(Appointment appointment);
}
