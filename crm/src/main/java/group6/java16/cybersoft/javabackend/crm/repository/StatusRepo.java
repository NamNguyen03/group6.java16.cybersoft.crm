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

}
