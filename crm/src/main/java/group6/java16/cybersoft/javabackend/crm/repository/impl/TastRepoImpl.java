/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.model.seedword.MyEntity;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.TaskRepo;

/**
 * @author trunghau
 *
 */
public class TastRepoImpl extends EntityRepo<MyEntity> implements TaskRepo {
	
	@Override
	public Task getById(int id) {
//		Task task = new Task();
//		String query = "SELECT * FROM task WHERE id = ?";
//		try {Connection connection = MySQLConnection.getConnection();
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setInt(1,id);
//			
//			ResultSet result = statement.executeQuery();
//			while(result.next()) {
//				task.setId(result.getInt("id"));
//				task.setTaskName(result.getString("task_name"));
//				task.setCreateBy(result.getString("create_by"));
//				task.setUpdateBy(result.getString("update_by"));
//				task.setDescript(result.getString("task_description"));
//				
//				
//			}
//			
//			
//		}catch(){
//			
//		}
//		
		return null;
	}

}
