package eh203.hibernate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;

import eh203.hibernate.domain.Person;

import utils.HibernateUtil;

public class AddPerson {
	public static void main(String[] args) throws IOException, ParseException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		
		System.out.print("Enter a person surname: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String surname = in.readLine();
		System.out.print("Enter a person forenames: ");
		String forenames = in.readLine();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.print("Enter a person dob (YYYY-MM-DD): ");
		Date dob = df.parse(in.readLine());
		
		// Create new person
		Person person = new Person(surname, forenames, dob);
		
		session.save(person);
		
		System.out.println("Added: " + person.getSurname() + ", " + person.getForenames() + " - " + person.getDob());
		
		session.getTransaction().commit();
	}
}
