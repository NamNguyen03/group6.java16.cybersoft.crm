/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository;
import java.util.List;
import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel.CreateTaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskRoleResponse;

public interface TaskRepo {
	
	boolean add(CreateTaskRequestModel task);

	List<TaskRoleResponse> getAllTaskByLeaderIsNull();

	boolean existsByID(String id);

	Task findByName(String name);

	boolean existsByNameAndLeaderIsNull(String taskName);

	boolean existsByNameAndLeader(String taskName, String usernameReq);

	List<TaskResponse> getAllMyTask(String usernameReq);

	void updateById(int id);

	boolean existsById(int id);

	boolean update(Task task);

	List<Task> getListTask();
	
	boolean isAdmin(String taskName);
	
	public Task getById(int id);
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
