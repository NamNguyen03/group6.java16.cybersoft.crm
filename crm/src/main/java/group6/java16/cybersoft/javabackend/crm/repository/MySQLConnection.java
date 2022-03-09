package group6.java16.cybersoft.javabackend.crm.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	private static final String url = "jdbc:mysql://localhost:3306/CRM";
	private static final String username = "root";
	private static final String password = "13062001Tp";
	
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
