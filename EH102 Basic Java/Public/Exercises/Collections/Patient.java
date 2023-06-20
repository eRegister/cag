import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class Patient {
	private String name;
	private String address;
	private Date birthdate;
	private int weight;
	
	Patient(String name, String address, Date birthdate, int weight) {
		this.name = name;
		this.address = address;
		this.birthdate = birthdate;
		this.weight = weight;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public Date getBirthdate() {
		return this.birthdate;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String toString() {
		return this.name + " " + this.birthdate;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Patient> patients = new ArrayList<Patient>();
		patients.add(new Patient("John Doe", "Easy Street", new Date(), 56));
		patients.add(new Patient("Bob Barker", "Bart Avenue", new Date(), 45));
		patients.add(new Patient("Kyle Cooley", "Sweetwater Road", new Date(), 73));
		patients.add(new Patient("Zeek Adams", "Merry Avenue", new Date(), 50));
		
		System.out.println("unsorted patients="+patients);
		PatientSort ps = new PatientSort();
		Collections.sort(patients, ps);
		System.out.println("sorted patients="+patients);

	}
}

class PatientSort implements Comparator<Patient> {
	public int compare(Patient one, Patient two) {
		return one.getName().compareTo(two.getName());
	}
}