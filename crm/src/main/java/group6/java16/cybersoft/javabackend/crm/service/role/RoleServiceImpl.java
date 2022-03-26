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
import group6.java16.cybersoft.javabackend.crm.service.role.RoleRequestModels.RemoveRoleRequestModel;
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
		
		// can't change my role
		if(updateRoleRequest.getUsernameReq().equals(updateRoleRequest.getEmail())) {
			return false;
		}

		if(!roleRepo.existsByName(updateRoleRequest.getRoleName())) {
			return false;
		}

		if(!updateRoleRequest.getRoleName().equals("ADMIN") && !projectRepo.existsByID(updateRoleRequest.getProjectName())) {
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
		if(userRepo.isLeader(updateRoleRequest.getUsernameReq())) {
			if("MEMBER".equals(updateRoleRequest.getRoleName())) {
				if(projectRepo.existsByNameAndLeader(updateRoleRequest.getProjectName(), updateRoleRequest.getUsernameReq())) {
					return roleRepo.saveRoleInProject(updateRoleRequest.getEmail(), role.getId(), project.getId());
				}
			}
		}

		return false;
	}

	@Override
	public boolean removeRole(RemoveRoleRequestModel removeRoleRequestModel) {

		
		if(!userRepo.existsById(removeRoleRequestModel.getIdUser())) {
			return false;
		}
		
		
		// can't remove my role
		if(removeRoleRequestModel.getUsername().equals(removeRoleRequestModel.getUsernameReq())) {
			return false;
		}
			
		if(!roleRepo.existsByName(removeRoleRequestModel.getRoleName())) {
			return false;
		}

		if(!removeRoleRequestModel.getRoleName().equals("ADMIN") && !projectRepo.existsByID(removeRoleRequestModel.getProjectName())) {
			return false;
		}

		// admin change is allowed change role admin and change role leader, member 
		if(userRepo.isAdmin(removeRoleRequestModel.getUsernameReq())) {
			if("LEADER".equals(removeRoleRequestModel.getRoleName()) || "MEMBER".equals(removeRoleRequestModel.getRoleName())) {
				return roleRepo.removeRoleInProject(removeRoleRequestModel.getIdUser(), removeRoleRequestModel.getProjectName(), removeRoleRequestModel.getRoleName());
			}
			if("ADMIN".equals(removeRoleRequestModel.getRoleName())) {
				return roleRepo.removeRole(removeRoleRequestModel.getIdUser(), removeRoleRequestModel.getRoleName());
			}
		}
		
		// leader change is allowed change role member if leader of project this
		if(userRepo.isLeader(removeRoleRequestModel.getUsernameReq())) {
			if(projectRepo.existsByNameAndLeader(removeRoleRequestModel.getProjectName(), removeRoleRequestModel.getUsernameReq())) {
				return roleRepo.removeRole(removeRoleRequestModel.getIdUser(), removeRoleRequestModel.getRoleName());
			}
		}

		return false;
	}

}
