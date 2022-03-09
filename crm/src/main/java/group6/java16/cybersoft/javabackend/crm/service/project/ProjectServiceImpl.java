/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.project;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.repository.ProjectRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.ProjectRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectRoleResponse;

/**
 * @author nam
 *
 */
public class ProjectServiceImpl implements ProjectService{

	private ProjectRepo projectRepo;
	
	public ProjectServiceImpl() {
		projectRepo = new ProjectRepoImpl();
	}
	
	@Override
	public List<ProjectRoleResponse> getAllProjectByLeaderIsNull() {
		return projectRepo.getAllProjectByLeaderIsNull();
	}

}
