package group6.java16.cybersoft.javabackend.crm.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	private static final String url = "jdbc:mysql://194.59.164.63:3306/u411554605_CRM";
	private static final String username = "u411554605_root";
	private static final String password = "Nam12345";
	
	private static Connection connection;
	
	public static Connection getConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				return connection;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("connect db fails");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("connect db fails");
			e.printStackTrace();
		}
		
		return connection;
	}
}
