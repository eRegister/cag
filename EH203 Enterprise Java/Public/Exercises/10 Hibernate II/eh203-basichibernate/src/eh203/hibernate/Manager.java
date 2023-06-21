package eh203.hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import eh203.hibernate.domain.Person;
import eh203.hibernate.domain.Project;

import utils.HibernateUtil;

public class Manager {
	public static void main(String[] args) {
		new Manager().run();
	}
	
	public void run() {
		System.out.println("Project Manager v1");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			// Start the command processing loop
			String cmd = "";
			do {
				System.out.print(">");
				String[] tokens = in.readLine().toLowerCase().trim().split(" ");
				cmd = tokens.length > 0 ? tokens[0] : "";
				String[] params = tokens.length > 1 ? Arrays.copyOfRange(tokens, 1, tokens.length) : new String [] {};
				
				if (!processCommand(cmd, params))
					break;
				
			} while (true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Processes a command
	 * @param cmd the command name
	 * @param params the parameters passed to the command
	 * @return true if command processing should continue, false to exit program
	 */
	private boolean processCommand(String cmd, String[] params) {
		if (cmd.equals("list") && params.length == 1) {
			if (params[0].equals("person"))
				listPersons();
			else if (params[0].equals("project"))
				listProjects();
			else
				System.out.println("Invalid parameter");
		}		
		else if (cmd.equals("exit"))
			return false;
		else {
			System.out.println("Usage:");
			System.out.println("  list (person|project) List all existing persons or projects");
			System.out.println("  exit                  Exit the program");
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private void listProjects() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List<Project> projects = session.createCriteria(Project.class).list();
		for (Project project : projects)
			System.out.println(project.getProjectId() + "\t" + project.getName() + "\t" + project.getDescription());
		
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	private void listPersons() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		List<Person> persons = session.createCriteria(Person.class).list();
		for (Person project : persons)
			System.out.println(project.getPersonId() + "\t" + project.getSurname() + ", " + project.getForenames());
		
		session.getTransaction().commit();	
	}
}
