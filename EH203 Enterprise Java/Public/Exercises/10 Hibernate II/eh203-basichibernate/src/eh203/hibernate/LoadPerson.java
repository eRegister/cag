package eh203.hibernate;
import org.hibernate.Session;

import eh203.hibernate.domain.Person;

import utils.HibernateUtil;

public class LoadPerson {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person person = (Person)session.load(Person.class, 1);
		
		System.out.println(person.getSurname() + ", " + person.getForenames() + " - " + person.getDob());
		
		session.getTransaction().commit();
	}
}
