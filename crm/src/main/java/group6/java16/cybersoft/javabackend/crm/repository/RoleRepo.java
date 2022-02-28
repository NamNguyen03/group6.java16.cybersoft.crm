/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Role;

/**
 * @author nam
 *
 */
public interface RoleRepo {

	/** 
	 * get all role in DB
	 * 
	 * @return List<Role> 
	 */
	List<Role> getAll();

	/**
	 * check role name is exists
	 * 
	 * @param name
	 * @return true if role name exists else return false
	 */
	boolean existsByName(String name);

	/**
	 * find role when name 
	 * 
	 * @param roleName
	 * @return Role
	 */
	Role findByName(String name);

	/**
	 * add role user in project
	 * 
	 * @param email
	 * @param idRole
	 * @param idProject
	 * 
	 * @return true if
	 */
	boolean saveRoleInProject(String email, int idRole, int idProject);
	
	/**
	 * add role user
	 * 
	 * @param email
	 * @param idRole
	 * @return true if insert success else return false
	 */
	boolean saveRoleDetails(String email, int idRole);
}
