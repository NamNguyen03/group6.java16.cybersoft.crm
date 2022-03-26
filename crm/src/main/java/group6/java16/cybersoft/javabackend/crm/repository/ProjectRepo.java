/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository;

import java.sql.SQLException;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectResponse;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectRoleResponse;

/**
 * @author nam
 *
 */
public interface ProjectRepo {

	boolean add(Project project);

	/**
	 * get all project not have leader in DB
	 * 
	 * @return List<ProjectRoleResponse>
	 */
	List<ProjectRoleResponse> getAllProjectByLeaderIsNull();

	/**
	 * check id is exists
	 * 
	 * @param id
	 * @return true if name exists else false
	 */
	boolean existsByID(String id);

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

	/**
	 * get all project user have any permission
	 * 
	 * @param usernameReq
	 * @return List<ProjectRoleResponse>
	 */
	List<ProjectResponse> getAllMyProject(String usernameReq);

	/**
	 * Retrieves an User by its projectName
	 * 
	 * @param userName
	 * 
	 * @return true if role of user is admin else false
	 */
	boolean isAdmin(String userName);

	/**
	 * Retrieves an User by its username
	 * 
	 * @param username
	 * 
	 * @return true if role of user is leader else false
	 */

	boolean isLeader(String username);

	/**
	 * @param id
	 * @throws SQLException
	 */
	void updateById(int id) throws SQLException;

	/**
	 * check id user exists
	 * 
	 * @param idProject
	 * @return true if id exists else false
	 */
	boolean existsById(int id);

	/**
	 * find User by id
	 * 
	 * @param idProject
	 * @return User if id exists else return null
	 */
	Project findById(int id);

	/**
	 * 
	 * @param projectname
	 * @param description
	 * @param status
	 * @return true if update success else false
	 */
	boolean updatet(Project project);
	
	/**
	 * 
	 * @param id
	 * @param description
	 * @param satus
	 * @param updateBy
	 * @return
	 */
	boolean update(int id,String name,String description ,String satus,String updateBy);

	/**
	 * 
	 * @return List<Project>
	 */
	List<Project> getListProject();
	
	
}
