package group6.java16.cybersoft.javabackend.crm.service.user;

import java.sql.Date;

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
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CreateUserRequestModel{
		private String username;
		private String password;
		private String fullname;
		private String address;
		private String phone;
		private String createBy;

	}
	
}
