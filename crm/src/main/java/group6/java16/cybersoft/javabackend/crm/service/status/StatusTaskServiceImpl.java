/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.status;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.repository.StatusRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.StatusRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;

/**
 * @author nam
 *
 */
public class StatusTaskServiceImpl implements StatusTaskService {
	private StatusRepo repo;
	
	public StatusTaskServiceImpl() {
		repo = new StatusRepoImpl();
	}

	@Override
	public List<StatusTask> getAll() {
		return repo.getListStatusTask();
	}
}
