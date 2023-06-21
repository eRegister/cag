package eh203.hibernate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.Session;

import eh203.hibernate.domain.Person;

import utils.HibernateUtil;

public class DelPerson {
	public static void main(String[] args) throws IOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		System.out.print("Enter a person ID: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int id = Integer.parseInt(in.readLine());
		
		// Select person based on ID
		Person person = (Person)session.load(Person.class, id);
		
		session.delete(person);
		
		System.out.println("Deleted: " + person.getSurname() + ", " + person.getForenames() + " - " + person.getDob());
		
		session.getTransaction().commit();
	}
}
