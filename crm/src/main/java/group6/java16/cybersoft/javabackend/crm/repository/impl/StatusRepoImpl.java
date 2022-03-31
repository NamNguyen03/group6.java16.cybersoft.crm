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

import group6.java16.cybersoft.javabackend.crm.model.Status;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.StatusRepo;
import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;

/**
 * @author trunghau
 *
 */
public class StatusRepoImpl extends EntityRepo<Status> implements StatusRepo {

	@Override
	public List<StatusTask> getListStatusTask() {
		String query = "select id, status_task_name from status_task";
		List<StatusTask> listStatus = new ArrayList<>();

		try (Connection connection = MySQLConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet result = statement.executeQuery();

			StatusTask status;
			while (result.next()) {
				status = new StatusTask();
				status.setId(result.getInt("id"));
				status.setStatusName(result.getString("status_task_name"));

				listStatus.add(status);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStatus;
	}

	@Override
	public boolean createStatusTask(String status_name) {
		String query = "INSERT INTO status_task (status_task_name) VALUE (?)";
		System.out.println(status_name);
		try (Connection connection = MySQLConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, status_name);

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create status task fails");
		}
		return false;

	}
	@Override
	public boolean existsByName(String name) {
		if(name == null || "".equals(name)) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists(select id from status_task where status_task_name = ? )";
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
	public boolean existsById(int task_id) {
		if(task_id < 0 ) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists(select id from status_task where id = ? )";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, task_id);
			ResultSet results = statement.executeQuery();

			results.next();

			return results.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteById(int task_id) {
		if(task_id < 0 ) {
			return false;
		}
		
		String query = "DELETE FROM status_task WHERE id = ? ";
		try (Connection connection = MySQLConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, task_id);

			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create status task fails");
		}
		return false;
	}
	
}