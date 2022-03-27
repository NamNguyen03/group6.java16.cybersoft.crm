/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.project;

import java.sql.SQLException;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.repository.ProjectRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.ProjectRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequetModel.CreateProjectRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequetModel.UpdateProjectRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectResponse;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectRoleResponse;

/**
 * 
 * @author Phạm Huy Phần
 *
 */
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepo projectRepo;

	public ProjectServiceImpl() {
		projectRepo = new ProjectRepoImpl();
	}

	@Override
	public List<ProjectRoleResponse> getAllProjectByLeaderIsNull() {
		return projectRepo.getAllProjectByLeaderIsNull();
	}

	@Override
	public List<ProjectResponse> getAllMyProject(String usernameReq) {

		return projectRepo.getAllMyProject(usernameReq);
	}

	@Override
	public boolean add(CreateProjectRequestModel projectRequest) {
		if (projectRequest.getName() == null || projectRequest.getDescription() == null
				|| projectRequest.getStatus() == null || projectRequest.getCreateBy() == null)
			return false;
		if (projectRepo.existsByID(projectRequest.getName())) {
			return false;
		}
		if (!projectRepo.isAdmin(projectRequest.getCreateBy())) {
			return false;
		}

		Project project = new Project();
		project.setName(projectRequest.getName());
		project.setDescription(projectRequest.getDescription());
		project.setStatus(projectRequest.getStatus());
		project.setCreateBy(projectRequest.getCreateBy());

		return projectRepo.add(project);
	}

	@Override
	public List<ProjectRequetModel> getAllProjectAndRole() {

		return null;
	}

	@Override
	public List<ProjectResponseModels> findByRoleName(String roleName) {

		return null;
	}

	@Override
	public void updateId(int id) {
		try {
			projectRepo.updateById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Project> getListProject() {
		return projectRepo.getListProject();
	}

	@Override
	public boolean update(UpdateProjectRequestModel projectRequest) {

		if (projectRequest.getName() == null || projectRequest.getDescription() == null
				|| projectRequest.getStatus() == null || projectRequest.getUpdateBy() == null) {
			return false;
		}
		if (!projectRepo.isAdmin(projectRequest.getUpdateBy())) {
			return false;
		}

		Project project = new Project();
		project.setId(projectRequest.getId());
		project.setName(projectRequest.getName());
		project.setDescription(projectRequest.getDescription());
		project.setStatus(projectRequest.getStatus());
		project.setUpdateBy(projectRequest.getUpdateBy());

		return projectRepo.updatet(project);
	}

	@Override
	public boolean update(int id, String name, String description, String satus, String updateBy) {
		if (!projectRepo.existsById(id)) {
			return false;
		}
		return projectRepo.update(id, name, description, satus, updateBy);
	}

	@Override
	public ProjectResponse getAllProjectByName(String projectname) {
		return projectRepo.getAllMyProjectByName(projectname);
	}

	@Override
	public Project findByName(String projectName) {
		return projectRepo.findByName(projectName);
	}

	@Override
	public Project findById(int Id) {
		return projectRepo.findById(Id);
	}
}
