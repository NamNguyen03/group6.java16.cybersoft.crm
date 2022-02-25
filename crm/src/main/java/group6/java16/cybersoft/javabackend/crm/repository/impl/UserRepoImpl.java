package group6.java16.cybersoft.javabackend.crm.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;

public class UserRepoImpl extends EntityRepo<User> implements UserRepo {

	@Override
	public User findByUsername(String username) {
		if(username == null) {
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
			statement.setString(2, "ADMIN");
			
			ResultSet results = statement.executeQuery();

			results.next();
			return results.getInt(1) == 1;


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
