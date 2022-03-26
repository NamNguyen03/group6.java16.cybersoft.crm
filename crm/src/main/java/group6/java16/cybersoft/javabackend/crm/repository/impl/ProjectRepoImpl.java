/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.ProjectRepo;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectResponse;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectRoleResponse;

/**
 * 
 * @author Phạm Huy Phần
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
			while (results.next()) {
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
	public Project findByName(String name) {
		if (name == null || "".equals(name)) {
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
		if (projectName == null || "".equals(projectName)) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists( select id from project " + "where project_name = ? and id in "
					+ "(select id from project_role " + "where role_details_id in " + "(select id from project_role "
					+ "where role_details_id " + "in (select id from role_details " + "where role_id "
					+ "= (select id from u_role where role_name = ?)))))";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, projectName);
			statement.setString(2, "ADMIN");

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
		if (projectName == null || "".equals(projectName)) {
			return false;
		}
		if (usernameReq == null || "".equals(usernameReq)) {
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
			statement.setString(3, "LEADER");

			ResultSet results = statement.executeQuery();

			results.next();
			return results.getBoolean(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
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
			while (results.next()) {
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

	@Override
	public boolean isAdmin(String projecName) {
		if (projecName == null || "".equals(projecName)) {
			return false;
		}

		return checkRole(projecName, "ADMIN");
	}

	@Override
	public boolean isLeader(String username) {
		if (username == null || "".equals(username)) {
			return false;
		}

		return checkRole(username, "LEADER");
	}

	/**
	 * 
	 * @param projecName
	 * @param roleName
	 * @return true if role equal role input, else false
	 */
	private boolean checkRole(String username, String roleName) {
		if (username == null || "".equals(username)) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT EXISTS( select * from  role_details " + "	where ("
					+ " user_id = (select id  from t_user where username = ? )"
					+ "	and role_id = (select id from u_role where role_name = ? )));";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, username);
			statement.setString(2, roleName);

			ResultSet results = statement.executeQuery();

			results.next();
			return results.getBoolean(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateById(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsById(int id) {
		if (id <= 0) {
			return false;
		}
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists( select id from project where id = ? )";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);

			ResultSet results = statement.executeQuery();

			results.next();
			return results.getBoolean(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Project findById(int id) {
		Project project = null;
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT id,project_name,project_description,project_status,create_date,create_by,update_date,update_by FROM project where id = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();

			results.next();
			project = new Project();

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
	public boolean add(Project project) {
		try (Connection connecttion = MySQLConnection.getConnection()) {
			String query = "insert into  project(project_name, project_description, project_status, create_by,update_by)\r\n"
					+ "values (?,?,?,?,?)";
			PreparedStatement statement = connecttion.prepareStatement(query);
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setString(3, project.getStatus());
			statement.setString(4, project.getCreateBy());
			statement.setString(5, project.getUpdateBy());

			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}
		System.out.println("false");
		return false;
	}

	@Override
	public List<Project> getListProject() {
		List<Project> projects = new ArrayList<Project>();
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id,project_name,project_description,project_status,create_date,create_by,update_date,update_by from project";

			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();

			while (results.next()) {
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
	public boolean existsByID(String id) {
		if (id == null || "".equals(id)) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists(select id from project where id = ? )";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			ResultSet results = statement.executeQuery();

			results.next();

			return results.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatet(Project project) {
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "update project set project_name = ?,project_description = ? ,project_status = ? ,create_by=?,update_by=? where id = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setString(3, project.getStatus());
			statement.setString(4, project.getUpdateBy());
			statement.setString(5, project.getUpdateBy());
			statement.setInt(6, project.getId());

			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}
		System.out.println("false");
		return false;
	}

	@Override
	public boolean update(int id, String name,String description, String satus, String updateBy) {
		
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "update project set project_name = ?,project_description = ? ,project_status = ? ,update_by=? where id = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,name);
			statement.setString(2,description);
			statement.setString(3,satus);
			statement.setString(4, updateBy);
			statement.setInt(5,id );

			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}
		System.out.println("false");
		return false;
	}


}
