package eh203.hibernate;
import utils.HibernateUtil;

public class TestConfig {
	public static void main(String[] args) {
		// Creating a session causes Hibernate to load the
		// configuration file, which will validate it
		HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
