package manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import databaseccess.JdbcFun;
import domain.Project;

public class ProjectManager {
	public void updateProject(int projectId) {
	
	};
	
	public void addProject(Project project) {
		String sql = "INSERT INTO Projects (project_name) VALUES ('" + project.getProjectName() +"')";
		try {
			Connection connection = JdbcFun.getInstance().getConnection();
			Statement statement = connection.createStatement();
			statement.execute(sql);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateProject(Project project) {
		String sql = "Update Projects SET project_name = '" + project.getProjectName() +"' WHERE project_id= " + project.getProjectId();
		try {
			Connection connection = JdbcFun.getInstance().getConnection();
			Statement statement = connection.createStatement();
			statement.execute(sql);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Project> getProjectList() {
		String sql = "SELECT * FROM Projects";
		try {
			Connection connection = JdbcFun.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Project> projectList = new ArrayList<>();
			while (resultSet.next()) {
				Project project = new Project();
				project.setProjectName(resultSet.getString("project_name"));
				project.setProjectId(resultSet.getInt("projects_id"));
				projectList.add(project);
			}
			
			return projectList;

		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
