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

import group6.java16.cybersoft.javabackend.crm.model.Role;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.RoleRepo;

/**
 * @author nam
 *
 */
public class RoleRepoImpl implements RoleRepo {

	@Override
	public List<Role> getAll() {
		List<Role> roles = new ArrayList<>(); 

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id, role_name from u_role";

			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet results = statement.executeQuery();

			Role role;
			while(results.next()) {
				role = new Role();
				role.setId(results.getInt("id"));
				role.setName(results.getString("role_name"));
				roles.add(role);
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roles;
	}

	@Override
	public boolean existsByName(String name) {
		if(name == null || "".equals(name)) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists(select id from u_role where role_name = ? )";
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
	public Role findByName(String name) {
		if(name == null || "".equals(name)) {
			return null;
		}
		Role role = null;

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id, role_name from u_role where role_name = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();

			results.next();
			role = new Role();
			role.setId(results.getInt("id"));
			role.setName(results.getString("role_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return role;
	}

	@Override
	public boolean saveRoleInProject(String email, int idRole, int idProject) {
		if( email == null || "".equals(email)) {
			return false;
		}
		try (Connection connection = MySQLConnection.getConnection()) {

			String query = "CALL save_role_in_project( ? , ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setInt(2, idRole);
			statement.setInt(3, idProject);
			statement.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return false;
	}


	public boolean saveRoleDetails(String email, int idRole) {
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "INSERT INTO role_details (user_id, role_id) "
					+ "SELECT * FROM (SELECT (select id from t_user where username = ? ), ? ) AS tmp "
					+ "WHERE NOT EXISTS ( SELECT id FROM role_details WHERE user_id = "
					+ "(select id from t_user where username = ? ) and role_id = ? ) LIMIT 1;";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setInt(2, idRole);
			statement.setString(3, email);
			statement.setInt(4, idRole);
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeRoleInProject(int idUser, String projectName, String roleName) {
		if( idUser <=0) {
			return false;
		}
		if( projectName == null || "".equals(projectName)) {
			return false;
		}
		if( roleName == null || "".equals(roleName)) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "delete from project_role where "
					+ "	project_id = (select id from project where project_name = ? ) "
					+ "    and role_details_id = (select id from role_details where "
					+ "		user_id = ? and role_id = (select id from u_role where role_name = ? )); ";
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setString(1, projectName);
			statement.setInt(2, idUser);
			statement.setString(3, roleName);
			
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeRole(int idUser, String roleName) {
		if( idUser <= 0) {
			return false;
		}
		if( roleName == null || "".equals(roleName)) {
			return false;
		}
		
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "delete from role_details where user_id = ? and  role_id = (select id from u_role where role_name = ? );";
			
			PreparedStatement statement;
			statement = connection.prepareStatement(query);
			statement.setInt(1, idUser);
			statement.setString(2, roleName);		
			
			statement.executeUpdate();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
