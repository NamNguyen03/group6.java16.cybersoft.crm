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

public class UserRepoImpl extends EntityRepo<User> implements UserRepo {

	@Override
	public User findByUsername(String username) {
		if (username == null) {
			return null;
		}
		User user = new User();
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT id, username, user_password, fullname, create_date, update_date, create_by, user_address, phone, update_by FROM t_user where username = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);

			ResultSet results = statement.executeQuery();

			results.next();
			user = super.setFiels(user, results);
			user.setUsername(results.getString("username"));
			user.setPassword(results.getString("user_password"));
			user.setFullname(results.getString("fullname"));
			user.setAddress(results.getString("user_address"));
			user.setPhone(results.getString("phone"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean isAdmin(String username) {
		if (username == null) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT EXISTS( select * from  role_details " + "	where ("
					+ " user_id = (select id  from t_user where username = ? )"
					+ "	and role_id = (select id from u_role where role_name = ? )));";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, username);
			statement.setString(2, "ADMIN");

			ResultSet results = statement.executeQuery();

			results.next();
			return results.getInt(1) == 1;

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
	public boolean checkExist(CreateUserRequestModel user) {
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT EXISTS( select * from  t_user where username = ?)";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, user.getUsername());

			ResultSet results = statement.executeQuery();

			results.next();
			if(results.getInt(1) == 1) {
				 query = "SELECT EXISTS( select * from  t_user where phone = ?)";
				 statement = connection.prepareStatement(query);

				 statement.setString(1, user.getPhone());

				 results = statement.executeQuery();

				results.next();
				return results.getInt(1) == 1;
			};
			
			return false;

		} catch (SQLException e) {
			System.out.println("check exist username,fail");
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
		String query = "DELETE FROM t_user WHERE id =?";
		Connection connection = MySQLConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			connection.close();
			
		}catch(SQLException e) {
			System.out.println("unable to connect database");
			e.printStackTrace();
	}
	

}
}

