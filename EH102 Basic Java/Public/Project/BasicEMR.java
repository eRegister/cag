package eh103.proj;

/**
 * The functionality required by a basic EMR system
 */
public interface BasicEMR {
	
	/**
	 * Gets the welcome message displayed when the EMR is started
	 * @return
	 */
	public String getWelcomeMessage();
	
	/**
	 * Loads the EMR data from the file system
	 * @return true if load was successful, else false
	 */
	public boolean load();
	
	/**
	 * Saves the EMR data to the file system
	 * @return true if save was successful, else false
	 */
	public boolean save();
	
	/**
	 * Authenticates a user
	 * @param login the user login
	 * @param password the user password
	 * @return true if user is authenticated
	 */
	public boolean authenticateUser(String login, String password);
	
	/**
	 * Adds a new patient to the EMR
	 */
	public void addPatient();
	
	/**
	 * Adds a new user to the EMR
	 */
	public void addUser();
	
	/**
	 * Lists all of the patients in the EMR
	 */
	public void listPatients();
	
	/**
	 * Lists all of the users in the EMR
	 */
	public void listUsers();
	
	/**
	 * View all the details of the specified patient
	 * @param id the patient id
	 */
	public void viewPatient(int id);
	
	/**
	 * Finds and lists all patients whose name contains
	 * the specified string
	 * @param name the string to look for in each name
	 */
	public void findPatients(String name);
	
	/**
	 * Deletes the specified person (user or patient)
	 * @param id the person id
	 */
	public void deletePerson(int id);
}
