/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.project;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequetModel.CreateProjectRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequetModel.UpdateProjectRequestModel;import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectResponse;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectRoleResponse;

/**
 * 
 * @author Phạm Huy Phần
 *
 */
public interface ProjectService {

	/**
	 * get all project not have leader
	 * 
	 * @return List<ProjectRoleResponse>
	 */
	List<ProjectRoleResponse> getAllProjectByLeaderIsNull();

	/**
	 * get all project user have any permission
	 * 
	 * @param usernameReq
	 * @return List<ProjectRoleResponse>
	 */
	List<ProjectResponse> getAllMyProject(String usernameReq);

	/**
	 * 
	 * @param project
	 * @return true if update success else false
	 */
	boolean add(CreateProjectRequestModel project);

	/**
	 * 
	 * @return List<ProjectRequetModel>
	 */
	List<ProjectRequetModel> getAllProjectAndRole();

	/**
	 * 
	 * @param roleName
	 * @return List<ProjectResponseModels>
	 */

	List<ProjectResponseModels> findByRoleName(String roleName);

	/**
	 * 
	 * @param id
	 */
	void updateId(int id);

	/**
	 * 
	 * @return List<Project>
	 */
	List<Project> getListProject();
	/**
	 * 
	 * @param project
	 * @return
	 */
	
	boolean update(UpdateProjectRequestModel project);
	/**
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param satus
	 * @param updateBy
	 * @return
	 */
	boolean update(int id, String name,String description, String satus, String updateBy) ;
}



























