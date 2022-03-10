/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.task;

import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.repository.TaskRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.TaskRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;

/**
 * @author trunghau
 *
 */
public class TaskServiceImpl implements TaskService {
	private TaskRepo taskRepo;
	
	public TaskServiceImpl() {
		taskRepo = new TaskRepoImpl();
	}
	@Override
	public UpdateStatusTaskResponse getTaskUpdateById(int id) {
			
		return taskRepo.getTaskUpdateById(id);
	}

	@Override
	public void updateStatusTask(int task_id) {
		taskRepo.updateStatusTask(task_id);
	}
	
	

}
