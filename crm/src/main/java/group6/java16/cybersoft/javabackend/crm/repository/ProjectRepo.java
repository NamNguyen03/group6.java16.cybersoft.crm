/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequestModels.ProjectRoleResponse;

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

}
