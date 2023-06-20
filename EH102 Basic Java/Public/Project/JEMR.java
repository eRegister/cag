package eh103.proj;

import java.io.*;
import java.util.Arrays;

/**
 * Container class to run any EMR which implements BasicEMR
 */
public class JEMR {
	protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	protected BasicEMR emr;
	
	/**
	 * Default constructor
	 * @param emr the EMR to run
	 */
	public JEMR(BasicEMR emr) {
		this.emr = emr;
	}
	
	/**
	 * Runs the configured EMR
	 */
	private void run() {
		try {
			// Display welcome
			System.out.println(emr.getWelcomeMessage());
			
			// Load EMR data from files
			if (!emr.load())
				System.out.println("Unable to load EMR data");
			
			// Check login and password
			if (!doLogin()) {
				System.out.println("Login failed after 3 attempts");
				return;				
			}
			
			// Start the command processing loop
			String cmd = "";
			do {
				System.out.print(">");
				String[] tokens = reader.readLine().toLowerCase().trim().split(" ");
				cmd = tokens.length > 0 ? tokens[0] : "";
				String[] params = tokens.length > 1 ? Arrays.copyOfRange(tokens, 1, tokens.length) : new String [] {};
				
				if (!processCommand(cmd, params))
					break;
				
			} while (true);
		} catch (IOException e) {
		}
			
		// Save EMR data to files
		if (!emr.save())
			System.out.println("Error saving EMR data");
	}
	
	/**
	 * Processes a command
	 * @param cmd the command name
	 * @param params the parameters passed to the command
	 * @return true if command processing should continue, false to exit program
	 */
	private boolean processCommand(String cmd, String[] params) {
		if (cmd.equals("add") && params.length == 1) {
			if (params[0].equals("patient"))
				emr.addPatient();
			else if (params[0].equals("user"))
				emr.addUser();
			else
				System.out.println("Invalid parameter");
		}
		else if (cmd.equals("view") && params.length == 1)
			emr.viewPatient(Integer.parseInt(params[0]));
		else if (cmd.equals("delete") && params.length == 1)
			emr.deletePerson(Integer.parseInt(params[0]));
		else if (cmd.equals("list") && params.length == 1) {
			if (params[0].equals("patient"))
				emr.listPatients();
			else if (params[0].equals("user"))
				emr.listUsers();
			else
				System.out.println("Invalid parameter");
		}
		else if (cmd.equals("find") && params.length == 1)
			emr.findPatients(params[0]);			
		else if (cmd.equals("exit"))
			return false;
		else {
			System.out.println("Usage:");
			System.out.println("  add (patient|user)  Add a new user or patient");
			System.out.println("  view <id>           View an existing patient record");
			System.out.println("  delete <id>         Delete an existing user or patient");
			System.out.println("  list (patient|user) List all existing users or patients");
			System.out.println("  find <name>         Find patients with matching name");
			System.out.println("  exit                Exit the program");
		}
		return true;
	}
	
	/**
	 * Does the user login loop
	 * @return true if user gets authenticated, else false
	 * @throws IOException
	 */
	private boolean doLogin() throws IOException {
		boolean auth = false;
		int attempts = 0;
		do {
			System.out.print("Login: ");
			String login = reader.readLine();
			System.out.print("Password: ");
			String password = reader.readLine();
			auth = emr.authenticateUser(login, password);
			if (!auth)
				System.out.println("Login or password incorrect");
		} while (!auth && ++attempts < 3);
		return auth;
	}
	
	/**
	 * The main method
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		JEMR app = new JEMR(new SimpleEMR());
		app.run();
	}

}
