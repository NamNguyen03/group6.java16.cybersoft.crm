/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.task;

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
	void updateStatusTask(int task_id);

}
