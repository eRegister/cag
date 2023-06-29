package appointment;

import java.util.Date;

/**
 * Appointment for a patient
 */
public class Appointment {

	private int appointmentId;
    private String patientName;
    private Date date;
    
    /**
     * Default constructor
     */
    public Appointment() {
    }
    
    /**
     * Explicit constructor
     * @param patientName the patient name
     * @param date the date
     */
    public Appointment(String patientName, Date date){
    	this.patientName = patientName;
    	this.date = date;
    }
    
    /**
     * Gets the appointment id
     * @return the appointment id
     */
    public int getAppointmentId() {
		return appointmentId;
	}

    /**
     * Sets the appointment id
     * @param appointmentId the appointment id
     */
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	/**
	 * Gets the patient name
	 * @param patientName the patient name
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * Gets the patient name
	 * @return the patient name
	 */
	public String getPatientName() {
		return patientName;
	} 
	
	/**
	 * Gets the date
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date the date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}