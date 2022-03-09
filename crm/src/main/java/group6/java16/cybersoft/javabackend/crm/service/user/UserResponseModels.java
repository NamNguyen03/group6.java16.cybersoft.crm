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
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CreateUserResponseModel{
		private String username;
		private String password;
		private String fullname;
		private String address;
		private String phone;
		private String createBy;

	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class UserResponseModel{
		private int idUser;
		private String username;
		private String fullname;
		private String roleName;
		private String projectName;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class AcceptResetPasswordResponseModel {
		private String username;
		private String fullname;
	}
}
