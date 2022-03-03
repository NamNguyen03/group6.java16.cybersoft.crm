/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.role;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Role;
import group6.java16.cybersoft.javabackend.crm.service.role.RoleRequestModels.*;

/**
 * @author nam
 *
 */
public interface RoleService {

	/**
	 * get all role
	 * 
	 * @return List<Role>
	 */
	List<Role> getAll();

	/**
	 * add new role for user consists of add role into project and add role into system
	 * 
	 * @param updateRoleRequest
	 * @return true if save success else return false
	 */
	boolean saveRole(UpdateRoleRequest updateRoleRequest);

	/**
	 * remove role
	 * 
	 * @param removeRoleRequestModel
	 * @return true if remove success else false
	 */
	boolean removeRole(RemoveRoleRequestModel removeRoleRequestModel);
	
}
