/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.task;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.repository.StatusRepo;
import group6.java16.cybersoft.javabackend.crm.repository.TaskRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.StatusRepoImpl;
import group6.java16.cybersoft.javabackend.crm.repository.impl.TaskRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;

/**
 * @author trunghau
 *
 */
public class TaskServiceImpl implements TaskService {
	private TaskRepo taskRepo;
	private StatusRepo statusRepo;

	public TaskServiceImpl() {
		taskRepo = new TaskRepoImpl();
		statusRepo = new StatusRepoImpl();
	}
	@Override
	public List<TaskResponseModels.TaskResponse> getListTaskByProjectId(int project_id) {
			
		return taskRepo.getListTaskByProjectId(project_id);
	}

	
	@Override
	public UpdateStatusTaskResponse getTaskUpdateById(int id) {
		return taskRepo.getTaskUpdateById(id);
	}

	
	
	@Override
	public void updateStatusTask(int task_id,String status_name) {
		taskRepo.updateStatusTask(task_id,status_name);
	}
	

	@Override
	public TaskResponseModels.TaskResponse findById(int id){
		return taskRepo.findById(id);
	}
	@Override
	public List<StatusTask> getListStatusTask(){
		return statusRepo.getListStatusTask();
	}
}
