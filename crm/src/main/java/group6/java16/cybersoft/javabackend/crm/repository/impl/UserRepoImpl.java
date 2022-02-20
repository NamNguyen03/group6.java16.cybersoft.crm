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
			String query = "SELECT id, username, u_password, fullname, create_date, update_date, create_by, address, phone, update_by FROM t_user where username = ?";
	
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				user = super.setFiels(user, results);
				user.setUsername(results.getString("username"));
				user.setPassword(results.getString("u_password"));
				user.setFullname(results.getString("fullname"));
				user.setAddress(results.getString("address"));
				user.setPhone(results.getString("phone"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
