package appointment.db;

import java.util.List;

import appointment.Appointment;


/**
 * Appointment data access object
 */
public interface AppointmentDAO {
	/**
	 * Gets the appointments
	 * @return the appointments
	 */
    public List<Appointment> getAppointments();
	
	/**
	 * Saves a appointment
	 * @param appointment the appointment
	 */
    public void saveAppointment(Appointment appointment);
}
