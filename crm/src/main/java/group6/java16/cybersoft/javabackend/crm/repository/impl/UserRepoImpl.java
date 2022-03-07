package group6.java16.cybersoft.javabackend.crm.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;

public class UserRepoImpl extends EntityRepo<User> implements UserRepo {
	
	@Override
	public User findByUsername(String username) {
		if(username == null) {
			return null;
		}
		User user = null;
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT id, username, user_password, fullname, create_date, update_date, create_by, user_address, phone, update_by FROM t_user where username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			
			results.next();
			user = new User();
			
			user = super.setFiels(user, results);
			
			user.setUsername(results.getString("username"));
			user.setPassword(results.getString("user_password"));
			user.setFullname(results.getString("fullname"));
			user.setAddress(results.getString("user_address"));
			user.setPhone(results.getString("phone"));;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean isAdmin(String username) {
	
		return checkRole(username, "ADMIN");
	}
	
	@Override
	public boolean isLeader(String username) {
	
		return checkRole(username, "LEADER");
	}

	/**
	 * check user have roleName
	 * 
	 * @param username
	 * @param roleName
	 * @return true if role equal role input, else false
	 */
	private boolean checkRole(String username, String roleName) {
		if(username == null) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT EXISTS( select * from  role_details "
					+ "	where ("
					+ " user_id = (select id  from t_user where username = ? )"
					+ "	and role_id = (select id from u_role where role_name = ? )));";

			PreparedStatement statement = connection.prepareStatement(query);
	
			statement.setString(1, username);
			statement.setString(2,roleName);
			
			ResultSet results = statement.executeQuery();

			results.next();
			return results.getBoolean(1);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<UserResponseModels.UserResponseModel> getAll() {
		List<UserResponseModels.UserResponseModel> rs = new ArrayList<>();
		try (Connection connection = MySQLConnection.getConnection())  {
			String query = "select * from (select u.id as 'id_user', u.username, u.fullname , r.role_name, p.project_name from t_user u "
					+ "left join role_details rd on rd.user_id = u.id "
					+ "left join u_role r on r.id = rd.role_id "
					+ "left join project_role pr on pr.role_details_id = rd.id "
					+ "left join project p on p.id = pr.project_id) as T where role_name = 'ADMIN' or "
					+ "project_name IS NOT NULL  order by role_name ASC ";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			
			UserResponseModels.UserResponseModel user;
			while (results.next()) {
				user = new UserResponseModels.UserResponseModel();
				user.setIdUser(results.getInt("id_user"));
				user.setUsername(results.getString("username"));
				user.setFullname(results.getString("fullname"));
				user.setRoleName(results.getString("role_name"));
				user.setProjectName(results.getString("project_name"));
				rs.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List<UserResponseModels.UserResponseModel> findByRoleName(String roleName) {
		List<UserResponseModels.UserResponseModel> rs = new ArrayList<>();
		try (Connection connection = MySQLConnection.getConnection())  {
			String query = "select * from (select u.id as 'id_user', u.username, u.fullname , r.role_name, p.project_name from t_user u "
					+ "left join role_details rd on rd.user_id = u.id "
					+ "left join u_role r on r.id = rd.role_id "
					+ "left join project_role pr on pr.role_details_id = rd.id "
					+ "left join project p on p.id = pr.project_id ) AS T where role_name = ? "
					+ " and (role_name = 'ADMIN' or project_name IS NOT NULL )";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, roleName);
			ResultSet results = statement.executeQuery();
			
			UserResponseModels.UserResponseModel user;
			while (results.next()) {
				user = new UserResponseModels.UserResponseModel();
				user.setIdUser(results.getInt("id_user"));
				user.setUsername(results.getString("username"));
				user.setFullname(results.getString("fullname"));
				user.setRoleName(results.getString("role_name"));
				user.setProjectName(results.getString("project_name"));
			
				rs.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public boolean  existsById(int id) {
		if(id <= 0) {
			return false;
		}
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists( select id from t_user where id = ? )";

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
	public User findById(int id) {
		if(id >= 0 ) {
			return null;
		}
		User user = null;
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT id, username, user_password, fullname, create_date, update_date, create_by, user_address, phone, update_by FROM t_user where id = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			
			results.next();
			user = new User();
			
			user = super.setFiels(user, results);
			
			user.setUsername(results.getString("username"));
			user.setPassword(results.getString("user_password"));
			user.setFullname(results.getString("fullname"));
			user.setAddress(results.getString("user_address"));
			user.setPhone(results.getString("phone"));;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	
}
