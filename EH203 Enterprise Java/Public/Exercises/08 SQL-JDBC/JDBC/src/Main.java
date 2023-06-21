import domain.Project;
import manager.ProjectManager;

public class Main {
	public static void main(String[] args) {
		ProjectManager projectManager = new ProjectManager();
	//	Project project = new Project();
	//	project.setProjectName("ICAP OpenMRS Training 1");
	//	projectManager.addProject(project);
		
		for (Project project : projectManager.getProjectList()) {
			System.out.println(project.getProjectName());
		}
		
	}
	
}
