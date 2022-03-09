/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.ProjectRepo;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectResponse;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectRoleResponse;

/**
 * @author nam
 *
 */
public class ProjectRepoImpl extends EntityRepo<Project> implements ProjectRepo {

	@Override
	public List<ProjectRoleResponse> getAllProjectByLeaderIsNull() {
		List<ProjectRoleResponse> rs = new ArrayList<>(); 

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id, project_name from project "
					+ "where id not in (select project_id from project_role "
					+ "where role_details_id in (select id from role_details "
					+ "where role_id = (select id from u_role where role_name = 'LEADER' )));";

			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet results = statement.executeQuery();

			ProjectRoleResponse project;
			while(results.next()) {
				project = new ProjectRoleResponse();
				project.setId(results.getInt("id"));
				project.setName(results.getString("project_name"));
				rs.add(project);
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public boolean existsByName(String name) {
		if(name == null || "".equals(name)) {
			return false;
		}
		
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists(select id from project where project_name = ? )";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			
			results.next();
			
			return results.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Project findByName(String name) {
		if(name == null || "".equals(name)) {
			return null;
		}
		Project project = null;
		
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id, project_name, project_description, project_status, create_date, create_by, update_date, update_by from project where project_name = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			
			project = new Project();
			results.next();
			
			project = super.setFiels(project, results);
			project.setName(results.getString("project_name"));
			project.setStatus(results.getString("project_status"));
			project.setDescription(results.getString("project_description"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return project;
	}

	@Override
	public boolean existsByNameAndLeaderIsNull(String projectName) {
		if(projectName == null || "".equals(projectName)) {
			return false;
		}	
		
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists( select id from project "
					+ "where project_name = ? and id in "
					+ "(select id from project_role "
					+ "where role_details_id in "
					+ "(select id from project_role "
					+ "where role_details_id "
					+ "in (select id from role_details "
					+ "where role_id "
					+ "= (select id from u_role where role_name = ?)))))";

			PreparedStatement statement = connection.prepareStatement(query);
	
			statement.setString(1, projectName);
			statement.setString(2,"ADMIN");
			
			ResultSet results = statement.executeQuery();

			results.next();
			return results.getBoolean(1);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean existsByNameAndLeader(String projectName, String usernameReq) {
		if(projectName == null || "".equals(projectName)) {
			return false;
		}	
		if(usernameReq == null || "".equals(usernameReq)) {
			return false;
		}	
		
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists (select id from project where project_name = ? and "
					+ "id = (select project_id from project_role where "
					+ "role_details_id = (select id from role_details where "
					+ "user_id = (select id from t_user where username = ? ) and "
					+ "role_id = (select id from u_role where role_name = ? ))))";

			PreparedStatement statement = connection.prepareStatement(query);
	
			statement.setString(1, projectName);
			statement.setString(2, usernameReq);
			statement.setString(3,"LEADER");
			
			ResultSet results = statement.executeQuery();

			results.next();
			return results.getBoolean(1);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Project> findAll(){
		List<Project> projects = new ArrayList<Project>();
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id,project_name,project_description,project_status,create_date,create_by,update_date,update_by from project";

			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();

			
			while(results.next()) {
				Project project = new Project();
				project.setId(results.getInt("id"));
				project.setName(results.getString("project_name"));
				project.setDescription(results.getString("project_description"));
				project.setStatus(results.getString("project_status")); 
				project.setCreateBy(results.getString("create_by")); 
				project.setUpdateBy(results.getString("update_by")); 
				project.setCreateDate(results.getDate("create_date")); 
				project.setUpdateDate(results.getDate("update_date")); 
				projects.add(project);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public List<ProjectResponse> getAllMyProject(String usernameReq) {
		List<ProjectResponse> rs = new ArrayList<>(); 

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id , project_name, project_description, project_status from project where "
					+ "id in (select project_id from project_role where "
					+ "role_details_id in (select id from role_details where "
					+ "user_id = (select id from t_user where username = ? )))";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, usernameReq);
			ResultSet results = statement.executeQuery();

			ProjectResponse project;
			while(results.next()) {
				project = new ProjectResponse();
				project.setId(results.getInt("id"));
				project.setName(results.getString("project_name"));
				project.setStatus(results.getString("project_status"));
				project.setDescription(results.getString("project_description"));
				rs.add(project);
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
