package group6.java16.cybersoft.javabackend.crm.service.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TaskResponseModels {
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class TaskRoleResponse{
		private int id;
		private String name;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class TaskResponse{
		private int id;
		private String name;
		private String description;
	}

}
