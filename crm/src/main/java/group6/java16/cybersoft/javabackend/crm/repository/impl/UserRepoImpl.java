package group6.java16.cybersoft.javabackend.crm.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.CreateUserRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.AcceptResetPasswordResponseModel;

public class UserRepoImpl extends EntityRepo<User> implements UserRepo {
	
	@Override
	public User findByUsername(String username) {
		if (username == null) {
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
		if(username == null || "".equals(username)) {
			return false;
		}

		return checkRole(username, "ADMIN");
	}
	
	@Override
	public boolean isLeader(String username) {
		if(username == null || "".equals(username)) {
			return false;
		}

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
		if(username == null || "".equals(username)) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT EXISTS( select * from  role_details " + "	where ("
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
	public boolean add(User user) {

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "INSERT INTO t_user(username, user_password, fullname, user_address, phone, create_by, update_by)"
					+ "VALUES(?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getPhone());
			statement.setString(6, user.getCreateBy());
			statement.setString(7, user.getUpdateBy());

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
	public boolean checkExistByUsername(String username) {
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT EXISTS( select * from  t_user where username = ?)";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, username);

			ResultSet results = statement.executeQuery();

			results.next();
			
			
			return results.getBoolean(1);

		} catch (SQLException e) {
		
		}
		return false;
	}
	
	@Override
	public List<UserResponseModels.UserResponseModel> getAllUserAndRole() {
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
	public List<User> getListUser(){
		List<User> listUser = new LinkedList();
		
		
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT id, fullname, username,user_address, phone, create_by FROM t_user ";

			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet results = statement.executeQuery();

			while(results.next()) {
				User user = new User();
				
				user.setId(results.getInt("id"));
				user.setFullname(results.getString("fullname"));
				user.setUsername(results.getString("username"));
				user.setAddress(results.getString("user_address"));
				user.setPhone(results.getString("phone"));
				user.setCreateBy(results.getString("create_by"));

				
				listUser.add(user);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUser;
		
	}
	@Override
	public void deleteById(int id) {
//		String query = "DELETE FROM t_user WHERE id =?";
//		Connection connection = MySQLConnection.getConnection();
//		
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setInt(1, id);
//			
//			statement.executeUpdate();
//			connection.close();
//			
//		}catch(SQLException e) {
//			System.out.println("unable to connect database");
//			e.printStackTrace();
//	}
	

	}

	@Override
	public User findById(int id) {
		
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

	@Override
	public boolean existsByUsername(String username) {
		if(username == null || username.equals("")) {
			return false;
		}
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists( select id from t_user where username = ? )";

			PreparedStatement statement = connection.prepareStatement(query);
	
			statement.setString(1, username);
			
			ResultSet results = statement.executeQuery();

			results.next();
			return results.getBoolean(1);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateNewPassword(String username, String password) {

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "update t_user set password_new = ? where username = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
	
			statement.setString(1, password);
			statement.setString(2, username);
			
			statement.executeUpdate();
			return true;


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<AcceptResetPasswordResponseModel> getAllUserRequestResetPassword() {
		List<AcceptResetPasswordResponseModel>  users = null;
		AcceptResetPasswordResponseModel user;
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select username, fullname from t_user where password_new is not null";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			users = new ArrayList<>();
			while(results.next()) {
				user = new AcceptResetPasswordResponseModel();
				user.setUsername(results.getString("username"));
				user.setFullname(results.getString("fullname"));
				users.add(user);
			};
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean acceptResetPassword(String username) {
		if(username == null || username.equals("")) {
			return false;
		}
		try (Connection connection = MySQLConnection.getConnection()) {

			String query = "call update_password(?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return false;
	}

	@Override
	public boolean RejectResetPassword(String username) {
		if(username == null || username.equals("")) {
			return false;
		}
		
		try (Connection connection = MySQLConnection.getConnection()) {

			String query = "update t_user set password_new = null where username = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	
}


