package appointment.db.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import appointment.Appointment;
import appointment.db.AppointmentDAO;


/**
 * Hibernate implementation of the appointment DAO
 */
public class HibernateAppointmentDAO implements AppointmentDAO {

	private SessionFactory sessionFactory;
	
	/**
	 * Sets the session factory (called by Spring)
	 * @param sessionFactory the session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * @see appointment.db.AppointmentDAO#getAppointments()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> getAppointments() {
		return sessionFactory.getCurrentSession().createCriteria(Appointment.class).list();	
	}

	/**
	 * @see appointment.db.AppointmentDAO#saveAppointment(Appointment)
	 */
	@Override
	public void saveAppointment(Appointment appointment) {
		sessionFactory.getCurrentSession().save(appointment);	
	}
}
