/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository;

import java.sql.SQLException;
import java.util.List;
import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskRoleResponse;

public interface TaskRepo {
	boolean add(Task task);

	List<TaskRoleResponse> getAllTaskByLeaderIsNull();

	boolean existsByID(String id);

	Task findByName(String name);

	boolean existsByNameAndLeaderIsNull(String taskName);

	boolean existsByNameAndLeader(String taskName, String usernameReq);


	List<TaskResponse> getAllMyTask(String usernameReq);

	boolean isAdmin(String userName);


	boolean isLeader(String username);

	
	void updateById(int id) throws SQLException;

	
	boolean existsById(int id);

	Task findById(int id);

	boolean update(Task task);

	List<Task> getListTask();
	
	
	public Task getById(int id);
	
}
