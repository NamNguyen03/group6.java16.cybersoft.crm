package group6.java16.cybersoft.javabackend.crm.service.task;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.repository.TaskRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.TaskRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel.CreateTaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel.UpdateTaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskRoleResponse;

public class TaskServiceImpl implements TaskService {

	private TaskRepo taskRepo;

	public TaskServiceImpl() {
		taskRepo = new TaskRepoImpl();
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
		if (task.getName() == null || task.getDescription() == null || task.getCreateBy() == null)
			return false;
		if (taskRepo.existsByID(task.getName())) {
			return false;
		}
//		if (!task.isAdmin(task.getCreateBy())) {
//			return false;
//		}

		Task tak = new Task();
		tak.setTaskName(task.getName());
		tak.setDescript(task.getDescription());
		tak.setCreateBy(task.getCreateBy());

		return taskRepo.add(tak);
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

}
