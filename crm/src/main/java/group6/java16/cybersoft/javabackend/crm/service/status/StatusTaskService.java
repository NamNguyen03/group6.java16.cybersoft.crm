/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.status;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;

/**
 * @author nam
 *
 */
public interface StatusTaskService {

	/**
	 * @return
	 */
	List<StatusTask> getAll();

}
