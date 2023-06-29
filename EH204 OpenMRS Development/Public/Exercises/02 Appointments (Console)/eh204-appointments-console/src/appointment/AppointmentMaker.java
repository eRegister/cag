package appointment;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import appointment.service.AppointmentService;


/**
 * Main application class
 */
public class AppointmentMaker {
	/**
	 * Main method
	 * @param args the program arguments
	 */
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("appcontext.xml");
		
		AppointmentService svc = (AppointmentService)appContext.getBean("appointmentService");
		
		// Save a new appointment
		svc.saveAppointment(new Appointment("Mr Java", new Date()));
		
		// List all existing appointments
		for (Appointment appointment : svc.getAppointments())
			System.out.println(appointment.getPatientName() + " at " + appointment.getDate());
	}
}
