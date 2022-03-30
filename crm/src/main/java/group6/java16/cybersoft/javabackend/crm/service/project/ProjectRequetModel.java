package group6.java16.cybersoft.javabackend.crm.service.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Phạm Huy Phần
 *
 */
public class ProjectRequetModel {
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CreateProjectRequestModel {
		private String name;
		private String description;
		private String status;
		private String createBy;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class UpdateProjectRequestModel {
		private  int id;
		private String name;
		private String description;
		private String status;
		private String updateBy;
	}
}
