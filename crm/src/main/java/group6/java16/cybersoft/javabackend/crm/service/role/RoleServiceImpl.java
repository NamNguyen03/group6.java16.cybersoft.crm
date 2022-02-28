/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.role;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.model.Role;
import group6.java16.cybersoft.javabackend.crm.repository.ProjectRepo;
import group6.java16.cybersoft.javabackend.crm.repository.RoleRepo;
import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.ProjectRepoImpl;
import group6.java16.cybersoft.javabackend.crm.repository.impl.RoleRepoImpl;
import group6.java16.cybersoft.javabackend.crm.repository.impl.UserRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.role.RoleRequestModels.UpdateRoleRequest;

/**
 * @author nam
 *
 */
public class RoleServiceImpl implements RoleService {

	private RoleRepo roleRepo;
	private ProjectRepo projectRepo;
	private UserRepo userRepo;
	
	public RoleServiceImpl() {
		roleRepo = new RoleRepoImpl();
		projectRepo = new ProjectRepoImpl();
		userRepo = new UserRepoImpl();
	}
	
	@Override
	public List<Role> getAll() {
		
		return roleRepo.getAll();
	}

	@Override
	public boolean saveRole(UpdateRoleRequest updateRoleRequest) {
		
		
		if(!roleRepo.existsByName(updateRoleRequest.getRoleName())) {
			return false;
		}
		
		if(!projectRepo.existsByName(updateRoleRequest.getProjectName())) {
			return false;
		}
		
		Role role = roleRepo.findByName(updateRoleRequest.getRoleName());

		
		Project project = projectRepo.findByName(updateRoleRequest.getProjectName());
		
		// admin change is allowed change role admin and change role leader, member if project do not leader
		if(userRepo.isAdmin(updateRoleRequest.getUsernameReq())) {
			
			if("LEADER".equals(updateRoleRequest.getRoleName()) || "MEMBER".equals(updateRoleRequest.getRoleName())) {
				if(!projectRepo.existsByNameAndLeaderIsNull(updateRoleRequest.getProjectName())) {
					return roleRepo.saveRoleInProject(updateRoleRequest.getEmail(), role.getId(), project.getId());
				}
			}
			if("ADMIN".contentEquals(updateRoleRequest.getRoleName())) {
				return roleRepo.saveRoleDetails(updateRoleRequest.getEmail(), role.getId());
			}
		}
		
		// leader change is allowed change role member if leader of project this
		if(userRepo.isLeader(updateRoleRequest.getEmail())) {
			if("LEADER".equals(updateRoleRequest.getRoleName()) || "MEMBER".equals(updateRoleRequest.getRoleName())) {
				if(!projectRepo.existsByNameAndLeaderIsNull(updateRoleRequest.getProjectName())) {
					return roleRepo.saveRoleInProject(updateRoleRequest.getEmail(), role.getId(), project.getId());
				}
			}
		}
		
		return false;
	}

}
