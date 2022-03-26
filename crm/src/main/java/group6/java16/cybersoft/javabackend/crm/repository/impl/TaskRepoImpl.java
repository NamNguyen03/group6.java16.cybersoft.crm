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

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.model.seedword.MyEntity;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.TaskRepo;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskRoleResponse;

public class TaskRepoImpl extends EntityRepo<MyEntity> implements TaskRepo {
	@Override
	public Task getById(int id) {
		return null;
	}

	@Override
	public boolean add(Task task) {
		try (Connection connecttion = MySQLConnection.getConnection()) {
			String query = "insert into  task(task_name, task_description, create_by,update_by)\r\n"
					+ "values (?,?,?,?)";
			PreparedStatement statement = connecttion.prepareStatement(query);
			statement.setString(1, task.getTaskName());
			statement.setString(2, task.getDescript());
			statement.setString(3, task.getCreateBy());
			statement.setString(4, task.getUpdateBy());

			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("Unable to connect to database");
			e.printStackTrace();
		}
		System.out.println("false");
		return false;
	}

	@Override
	public boolean existsByID(String id) {

		return false;
	}

	@Override
	public Task findByName(String name) {
		if (name == null || "".equals(name)) {
			return null;
		}
		Task task = null;

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id,task_name,task_description,create_date,create_by,update_date,update_by,user_id,status_id,project_id where task_name = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();

			task = new Task();
			results.next();

			task = (Task) super.setFiels(task, results);
			task.setTaskName(results.getString("task_name"));
			task.setDescript(results.getString("task_description"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return task;
	}

	@Override
	public boolean existsByNameAndLeaderIsNull(String taskName) {
		return false;
	}

	@Override
	public boolean existsByNameAndLeader(String taskName, String usernameReq) {
		return false;
	}

	@Override
	public List<TaskResponse> getAllMyTask(String usernameReq) {

		return null;
	}

	@Override
	public boolean isAdmin(String userName) {
		return false;
	}

	@Override
	public boolean isLeader(String username) {
		return false;
	}

	@Override
	public void updateById(int id) throws SQLException {

	}

	@Override
	public boolean existsById(int id){
		if (id <= 0) {
			return false;
		}
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select exists( select id from task where id = ? )";

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
	public boolean update(Task task) {
		return false;
	}

	@Override
	public List<TaskRoleResponse> getAllTaskByLeaderIsNull() {
		return null;
	}

	@Override
	public Task findById(int id) {
		return null;
	}

	@Override
	public List<Task> getListTask() {
		List<Task> tasks = new ArrayList<Task>();
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "select id,task_name,task_description,create_date,create_by,update_date,update_by from task";

			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				Task task = new Task();
				task.setId(results.getInt("id"));
				task.setTaskName(results.getString("task_name"));
				task.setDescript(results.getString("task_description"));
				task.setCreateBy(results.getString("create_by"));
				task.setUpdateBy(results.getString("update_by"));
				task.setCreateDate(results.getDate("create_date"));
				task.setUpdateDate(results.getDate("update_date"));
				tasks.add(task);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

}
