package group6.java16.cybersoft.javabackend.crm.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	private static final String url = "jdbc:mysql://sql228.main-hosting.eu:3306/";
	private static final String database = "u411554605_crm";
	private static final String username = "u411554605_user";
	private static final String password = "Passw0rd";
	private static final String driver = "com.mysql.jdbc.Driver";
	
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
			Class.forName(driver);
		    connection = DriverManager.getConnection(url+database,username,password);
			
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
