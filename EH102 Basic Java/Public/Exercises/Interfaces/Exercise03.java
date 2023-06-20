
package interfaces;

import java.util.Arrays;

class Person implements Comparable<Person> {
	protected String forename, surname;
	
	public Person(String forename, String surname) {
		this.forename = forename;
		this.surname = surname;
	}
	public String getForename() {
		return forename;
	}
	public String getSurname() {
		return surname;
	}

	@Override
	public int compareTo(Person p) {
		return this.surname.compareTo(p.surname);
	}
}
// TODO Define a class which implements SimpleCalculator
public class Exercise03 {
	public Exercise03() {
		Person[] people = new Person[6];
		people[0] = new Person("Tom", "Hanks");
		people[1] = new Person("Tom", "Cruise");
		people[2] = new Person("Penelope", "Cruise");
		people[3] = new Person("David", "Beckham");
		people[4] = new Person("Kylie", "Minogue");
		people[5] = new Person("Jennifer", "Lopez");
		
		Arrays.sort(people);
		
		for (Person p : people)
			System.out.println(p.getSurname() + ", " + p.getForename());
	}
	
	public static void main(String[] args) {
		new Exercise03();
	}
}
