/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.repository;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;

/**
 * @author trunghau
 *
 */
public interface StatusRepo  {
	
	List<StatusTask> getListStatusTask();
	
	boolean createStatusTask(String status_name);

	/**
	 * @param name
	 * @return
	 */
	boolean existsByName(String name);

}
