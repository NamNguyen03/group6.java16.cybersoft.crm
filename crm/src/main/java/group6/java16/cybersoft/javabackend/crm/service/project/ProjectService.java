/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.project;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectResponse;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectRoleResponse;

/**
 * @author nam
 *
 */
public interface ProjectService {

	/**
	 * get all project not have leader
	 * 
	 * @return 	List<ProjectRoleResponse> 
	 */
	List<ProjectRoleResponse> getAllProjectByLeaderIsNull();

	/**
	 * get all project user have any permission
	 * 
	 * @param usernameReq
	 * @return List<ProjectRoleResponse>
	 */
	List<ProjectResponse> getAllMyProject(String usernameReq);

}
