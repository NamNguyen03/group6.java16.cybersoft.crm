/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.task;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.repository.StatusRepo;
import group6.java16.cybersoft.javabackend.crm.repository.TaskRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.StatusRepoImpl;
import group6.java16.cybersoft.javabackend.crm.repository.impl.TaskRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel.CreateTaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel.UpdateTaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskRoleResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;

public class TaskServiceImpl implements TaskService {

	private TaskRepo taskRepo;
	private StatusRepo statusRepo;

	public TaskServiceImpl() {
		taskRepo = new TaskRepoImpl();
		statusRepo = new StatusRepoImpl();
	}

	@Override
	public List<TaskRoleResponse> getAllTaskByLeaderIsNull() {
		return taskRepo.getAllTaskByLeaderIsNull();
	}

	@Override
	public List<TaskResponse> getAllMyTask(String usernameReq) {
		return taskRepo.getAllMyTask(usernameReq);
	}

	@Override
	public boolean add(CreateTaskRequestModel task) {
		if (task.getName() == null || task.getDescription() == null || task.getCreateBy() == null) {
			return false;
		}
		if (taskRepo.existsByID(task.getName())) {
			return false;
		}
		if (!taskRepo.isAdmin(task.getCreateBy())) {
			return false;
		}

		return taskRepo.add(task);
	}

	@Override
	public List<TaskRequestModel> getAllTasktAndRole() {
		return null;
	}

	@Override
	public List<TaskResponseModels> findByRoleName(String roleName) {
		return null;
	}

	@Override
	public void updateId(int id) {

	}

	@Override
	public List<Task> getListTask() {

		return taskRepo.getListTask();
	}

	@Override
	public boolean update(UpdateTaskRequestModel project) {

		return false;
	}

	@Override
	public boolean update(int id, String name, String description, String satus, String updateBy) {
		return false;
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
	public void updateStatusTask(int task_id, String status_name) {
		taskRepo.updateStatusTask(task_id, status_name);
	}

	@Override
	public TaskResponseModels.TaskResponse findById(int id) {
		return taskRepo.findById(id);
	}

	@Override
	public List<StatusTask> getListStatusTask() {
		return statusRepo.getListStatusTask();
	}

	@Override
	public boolean createStatusTask(String status_name) {
		return statusRepo.createStatusTask(status_name);
	}

	@Override
	public boolean existByName(String name) {
		return statusRepo.existsByName(name);
	}
}
