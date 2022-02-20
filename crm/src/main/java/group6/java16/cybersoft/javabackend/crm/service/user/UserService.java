package group6.java16.cybersoft.javabackend.crm.service.user;

import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.LoginRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.LoginResponseModel;

public interface UserService {

	/**
	 * Retrieves an LoginResponseModel by its LoginRequestModel
	 * 
	 * @param LoginRequestModel must not be {@literal null}.
	 * 
	 * @return the LoginResponseModel if username exists and 
	 * password input equals password in Database else
	 * return null 
	 */
	LoginResponseModel login(LoginRequestModel loginRequestModel);

}
