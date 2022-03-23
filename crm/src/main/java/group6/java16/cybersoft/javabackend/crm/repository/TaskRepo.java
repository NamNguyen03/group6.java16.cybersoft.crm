/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;

/**
 * @author trunghau
 *
 */
public interface TaskRepo {
	/**
	 * @author trunghau
	 *
	 */	
	TaskResponseModels.UpdateStatusTaskResponse getTaskUpdateById(int id);
	
	void updateStatusTask(int task_id,String status_name);
	
	List<TaskResponseModels.TaskResponse> getListTaskByProjectId(int project_id);

	/**
	 * @param id
	 * @return
	 */
	TaskResponseModels.TaskResponse findById(int id);
	 
	
	
}
