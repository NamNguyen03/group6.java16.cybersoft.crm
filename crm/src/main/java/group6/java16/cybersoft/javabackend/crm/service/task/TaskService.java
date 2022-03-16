/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.task;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;

/**
 * @author trunghau
 *
 */
public interface TaskService {
	/**
	 * @author trunghau
	 *
	 */
	UpdateStatusTaskResponse getTaskUpdateById(int id);
	/**
	 * @author trunghau
	 *
	 */
	void updateStatusTask(int task_id,String status_name);
	/**
	 * @param project_id
	 * @return
	 */
	List<TaskResponse> getListTaskByProjectId(int project_id);
	/**
	 * @param id
	 * @return
	 */
	TaskResponseModels.TaskResponse findById(int id);
	
	List<StatusTask> getListStatusTask();

}
