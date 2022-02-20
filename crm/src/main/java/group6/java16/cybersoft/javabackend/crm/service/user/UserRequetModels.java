package group6.java16.cybersoft.javabackend.crm.service.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequetModels {
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class LoginRequestModel{
		private String username;
		private String password;
	}
}
