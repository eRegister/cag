package eh203.hibernate.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Person {
	protected int personId;
	protected String surname;
	protected String forenames;
	protected Date dob;
	
	public Person() {
	}

	public Person(String surname, String forenames, Date dob) {
		this.surname = surname;
		this.forenames = forenames;
		this.dob = dob;
	}

	protected Set<Project> projects = new HashSet<Project>();
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getForenames() {
		return forenames;
	}
	
	public void setForenames(String forenames) {
		this.forenames = forenames;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
}
