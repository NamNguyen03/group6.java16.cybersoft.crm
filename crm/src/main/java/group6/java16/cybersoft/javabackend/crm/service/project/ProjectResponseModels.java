/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.service.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nam
 *
 */
public class ProjectResponseModels {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class ProjectRoleResponse{
		private int id;
		private String name;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class ProjectResponse{
		private int id;
		private String name;
		private String description;
		private String status;
	}
}
