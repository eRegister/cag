import domain.Project;
import hibernateemr.HibernateUtil;
import manager.ProjectManager;
import org.hibernate.Session;

public class Main {
	public static void main(String[] args) {
	//	ProjectManager projectManager = new ProjectManager();
	//	Project project = new Project();
	//	project.setProjectName("ICAP OpenMRS Training 1");
	//	projectManager.addProject(project);
		
	//	for (Project project : projectManager.getProjectList()) {
	//		System.out.println(project.getProjectName());
	//	}
		
		// HibernateUtil.getSessionFactory().getCurrentSession();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Project project = (Project) session.load(Project.class, 1); // Loads project record with ID=1
		session.getTransaction().commit();
		
		System.out.println("Project name is  " + project.getProjectName());
		
	}
	
}
