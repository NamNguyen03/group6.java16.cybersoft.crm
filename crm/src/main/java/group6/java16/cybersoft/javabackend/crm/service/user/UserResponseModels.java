package group6.java16.cybersoft.javabackend.crm.service.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserResponseModels {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class LoginResponseModel{
		private String username;
		private String fullname;
	}
}
