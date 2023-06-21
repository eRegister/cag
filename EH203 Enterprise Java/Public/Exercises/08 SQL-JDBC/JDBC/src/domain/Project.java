package domain;

import java.util.List;

public class Project {
	
	private Integer projectId;
	
	
	public List<Assignment> getAssignments() {
		return assignments;
	}
	
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	private String projectName;
	
	private List<Assignment> assignments;
	
	public Integer getProjectId() {
		return projectId;
	}
	
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
