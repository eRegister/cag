package eh203.hibernate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import eh203.hibernate.domain.Person;

import utils.HibernateUtil;

public class FindPerson {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		System.out.print("Enter a person name: ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String search = in.readLine();
		
		// Select person based on ID
		List<Person> persons = session.createCriteria(Person.class)
			.add(Restrictions.like("surname", "%" + search + "%")).list();
		
		for (Person person : persons)
			System.out.println(person.getSurname() + ", " + person.getForenames() + " - " + person.getDob());
		
		session.getTransaction().commit();
	}
}
