/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectRoleResponse;

/**
 * @author nam
 *
 */
public interface ProjectRepo {

	/**
	 * get all project not have leader in DB
	 * 
	 * @return 	List<ProjectRoleResponse> 
	 */
	List<ProjectRoleResponse> getAllProjectByLeaderIsNull();

	/**
	 * check name is exists
	 * 
	 * @param name
	 * @return true if name exists else false
	 */
	boolean existsByName(String name);

	/**
	 * find project by name
	 * 
	 * @param name
	 * @return Project
	 */
	Project findByName(String name);

	/**
	 * check project exists and leader is null
	 * 
	 * @param projectName
	 * @return true if project exists and leader is null else false
	 */
	boolean existsByNameAndLeaderIsNull(String projectName);

	/**
	 * check project by name and check this have leader equals usernameReq
	 * 
	 * @param projectName
	 * @param usernameReq
	 * @return true if project exists name and have leader is usernameReg else false
	 */
	boolean existsByNameAndLeader(String projectName, String usernameReq);

}
