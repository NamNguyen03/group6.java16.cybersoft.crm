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
		
	try(Connection connection = MySQLConnection.getConnection()){
		PreparedStatement statemnt = connection.prepareStatement(query);
		
		ResultSet result = statemnt.executeQuery();
		
		StatusTask status ;
		while(result.next()) {
			status = new StatusTask();
			status.setId(result.getInt("id"));
			status.setStatusName(result.getString("status_task_name"));
			
			listStatus.add(status);
			
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
	
	return listStatus ;
	}
}
