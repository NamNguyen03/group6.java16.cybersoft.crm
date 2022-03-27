package group6.java16.cybersoft.javabackend.crm.service.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TaskRequestModel {
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CreateTaskRequestModel {
		private String name;
		private String description;
		private String createBy;
		private int projectId;
		private int userId;
		private int statusId = 1;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class UpdateTaskRequestModel {
		private  int id;
		private String name;
		private String description;
		private String updateBy;
	}
}
