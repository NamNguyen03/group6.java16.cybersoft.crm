package group6.java16.cybersoft.javabackend.crm.service.task;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel.CreateTaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel.UpdateTaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskRoleResponse;

public interface TaskService {

	List<TaskRoleResponse> getAllTaskByLeaderIsNull();

	List<TaskResponse> getAllMyTask(String usernameReq);

	boolean add(CreateTaskRequestModel task);

	List<TaskRequestModel> getAllTasktAndRole();

	List<TaskResponseModels> findByRoleName(String roleName);

	void updateId(int id);

	List<Task> getListTask();

	boolean update(UpdateTaskRequestModel project);

	boolean update(int id, String name, String description, String satus, String updateBy);
}
