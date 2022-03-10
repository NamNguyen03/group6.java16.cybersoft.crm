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

import com.mysql.cj.protocol.Resultset;

import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.model.seedword.MyEntity;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.TaskRepo;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;

/**
 * @author trunghau
 *
 */
public class TaskRepoImpl extends EntityRepo<Task> implements TaskRepo {

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

				return task;

			}

		} catch (SQLException e) {
			System.out.println("getTaskbyID fails");
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateStatusTask(int task_id) {
		String query = "use CRM;"
				+"UPDATE `CRM`.`task` SET `status_id` = '2' WHERE (`id` = ?);";
		
		try (Connection connection = MySQLConnection.getConnection()){
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1,task_id);
		
		ResultSet result = statement.executeQuery();
//		while(result.next()) {
//			
//		}
//		
	
	}catch(SQLException e)
	{
		System.out.println("update status task fails");
		e.printStackTrace();
	}
}
	@Override
	public List<TaskResponseModels.TaskResponse> getAllTaskByUserId(int user_id){

			List<TaskResponseModels.TaskResponse> listTasks = new ArrayList();
			try (Connection connection = MySQLConnection.getConnection())  {
				String query =" select  task.id,task_name,task_description,task.create_by,task.create_date,task.update_by,task.update_date,task.user_id,task.status_id,task.project_id,t_user.fullname,status_task.status_task_name,project.project_name FROM task INNER JOIN t_user ON task.user_id=t_user.id INNER JOIN status_task ON task.status_id = status_task.id INNER JOIN project ON task.project_id=project.id where task.user_id = ?";

				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet results = statement.executeQuery();
				
				TaskResponseModels.TaskResponse task;
				while (results.next()) {
					task = new TaskResponseModels.TaskResponse();
					task.setId(results.getInt("id_user"));
					task.setTaskName(results.getString("task_name"));
					task.setUserName(results.getString("fullname"));
					task.setStatusName(results.getString("status_task_name"));
					task.setProjectName(results.getString("project_name"));
					
					listTasks.add(task);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		

		
		
		return listTasks;
	}
}
