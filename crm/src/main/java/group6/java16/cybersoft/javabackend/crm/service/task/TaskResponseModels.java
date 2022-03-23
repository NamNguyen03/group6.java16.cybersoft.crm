/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.task;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trunghau
 *
 */


public class TaskResponseModels {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class UpdateStatusTaskResponse{
		private int id;
		private String taskName;
		private String userName;
		private String projectName;
		private String description;
		private String statusName; 
		
	}
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class TaskResponse{
		private int id;
		private String taskName;
		private String userName;
		private String projectName;
		private String description;
		private String statusName; 
		
	}

	
	

}
