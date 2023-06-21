package eh203.hibernate;
import java.util.List;

import org.hibernate.Session;

import eh203.hibernate.domain.Person;

import utils.HibernateUtil;

public class ListPersons {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		// Query using HQL
		//List<Person> persons = session.createQuery("from Person").list();
		
		// New criteria based syntax
		List<Person> persons = session.createCriteria(Person.class).list();
		
		session.getTransaction().commit();
		
		for (Person person : persons)
			System.out.println(person.getSurname() + ", " + person.getForenames() + " - " + person.getDob());
		
		
	}
}
