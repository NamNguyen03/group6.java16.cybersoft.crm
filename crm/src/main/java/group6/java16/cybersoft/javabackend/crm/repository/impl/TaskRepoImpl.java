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
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel.CreateTaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskRoleResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;

public class TaskRepoImpl extends EntityRepo<MyEntity> implements TaskRepo {
	@Override
	public Task getById(int id) {
		return null;
	}

	@Override
	public boolean add(CreateTaskRequestModel task) {
		try (Connection connecttion = MySQLConnection.getConnection()) {
			String query = "insert into  task(task_name, task_description, create_by,status_id,user_id,project_id)\r\n"
					+ "values (?,?,?,?,?,?)";
			PreparedStatement statement = connecttion.prepareStatement(query);
			statement.setString(1, task.getName());
			statement.setString(2, task.getDescription());
			statement.setString(3, task.getCreateBy());
			statement.setInt(4, task.getStatusId());
			statement.setInt(5, task.getUserId());
			statement.setInt(6, task.getProjectId());

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
			task.setDescription(results.getString("task_description"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}

	@Override
	public UpdateStatusTaskResponse getTaskUpdateById(int id) {
		UpdateStatusTaskResponse task = new UpdateStatusTaskResponse();
		String query = "use CRM;"
				+ "select  task.id,task_name,task_description,t_user.fullname,"
				+ "status_task.status_task_name,project.project_name FROM task "
				+ "INNER JOIN t_user ON task.user_id=t_user.id INNER JOIN status_task ON task.status_id = status_task.id "
				+ "INNER JOIN project ON task.project_id=project.id where task.id = ?";
		try (Connection connection = MySQLConnection.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				task.setId(result.getInt("id"));
				task.setTaskName(result.getString("task_name"));
				task.setDescription(result.getString("task_description"));
				task.setStatusName(result.getString("status_name"));
				task.setUserName(result.getString("fullname"));
				task.setProjectName(result.getString("project_name"));


			}

		} catch (SQLException e) {
			System.out.println("getTaskbyID fails");
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
	public void updateById(int id) {

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
				task.setDescription(results.getString("task_description"));
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
	
	public void updateStatusTask(int task_id, String status_name) {
		String query =  "update task set status_id = (select id from status_task where status_task.status_task_name = ?) where id = ? ";


		try (Connection connection = MySQLConnection.getConnection()){
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,status_name);
			statement.setInt(2,task_id);
			statement.executeUpdate();
			connection.close();


		}catch(SQLException e)

		{
			System.out.println("update status task fails");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<TaskResponseModels.TaskResponse> getListTaskByProjectId(int project_id){

		List<TaskResponseModels.TaskResponse> listTasks = new ArrayList<>();
		try (Connection connection = MySQLConnection.getConnection())  {
			String query ="  select  task.id,task_name,task_description,task.create_by as 'create_by_task' ,task.create_date as 'create_date_task',task.update_by,\r\n"
					+ " task.update_date,task.user_id,task.status_id,task.project_id,t_user.fullname,project.project_name,status_task.status_task_name\r\n"
					+ " from task join t_user on task.user_id = t_user.id \r\n"
					+ "			join project on task.project_id = project.id\r\n"
					+ "            join status_task on task.status_id = status_task.id\r\n"
					+ "	where task.project_id = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1,project_id);
			ResultSet results = statement.executeQuery();

			TaskResponseModels.TaskResponse task;
			while (results.next()) {
				task = new TaskResponseModels.TaskResponse();
				task.setId(results.getInt("id"));
				task.setTaskName(results.getString("task_name"));
				task.setDescription(results.getString("task_description"));
				task.setUserName(results.getString("fullname"));
				task.setCreateBy(results.getString("create_by_task"));
				task.setCreateDate(results.getDate("create_date_task"));
				task.setStatusName(results.getString("status_task_name"));
				listTasks.add(task);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTasks;
	}

	@Override
	public TaskResponseModels.TaskResponse findById(int id) {

		TaskResponseModels.TaskResponse task = null;
		try (Connection connection = MySQLConnection.getConnection()) {
			String query = " select  task.id,task_name,task_description,task.create_by,task.create_date,task.update_by,task.update_date,task.user_id,task.status_id,task.project_id,t_user.fullname,status_task.status_task_name,project.project_name FROM task INNER JOIN t_user ON task.user_id=t_user.id INNER JOIN status_task ON task.status_id = status_task.id INNER JOIN project ON task.project_id=project.id where task.id = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();

			results.next();
			task = new TaskResponseModels.TaskResponse();

			task.setId(results.getInt("id"));
			task.setTaskName(results.getString("task_name"));
			task.setDescription(results.getString("task_description"));
			task.setUserName(results.getString("fullname"));
			task.setStatusName(results.getString("status_task_name"));
			task.setProjectName(results.getString("project_name"));


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}

	@Override
	public boolean isAdmin(String taskName) {
		if (taskName == null || "".equals(taskName)) {
			return false;
		}

		return checkRole(taskName, "ADMIN");
	}

	private boolean checkRole(String username, String roleName) {
		if (username == null || "".equals(username)) {
			return false;
		}

		try (Connection connection = MySQLConnection.getConnection()) {
			String query = "SELECT EXISTS( select * from  role_details " + "	where ("
					+ " user_id = (select id  from t_user where username = ? )"
					+ "	and role_id = (select id from u_role where role_name = ? )));";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, username);
			statement.setString(2, roleName);

			ResultSet results = statement.executeQuery();

			results.next();
			return results.getBoolean(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
