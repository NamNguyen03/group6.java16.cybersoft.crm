package group6.java16.cybersoft.javabackend.crm.service.user;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.CreateUserRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.LoginRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.AcceptResetPasswordResponseModel;
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

	/**
	 * @param user
	 * @return
	 */
	boolean add(CreateUserRequestModel user);

	/**
	 * @return
	 */
	List<User> getListUser();

	/**
	 * @param id
	 */
	void deleteById(int id);
	 /* get all user and role
	 * 
	 * @return List<User> 
	 */
	List<UserResponseModels.UserResponseModel> getAllUserAndRole();

	/**
	 * get user have role.name equals roleName
	 * 
	 * @param roleName
	 * @return List<User> 
	 */
	List<UserResponseModels.UserResponseModel> findByRoleName(String roleName);

	/**
	 * update password
	 * 
	 * @param username
	 * @param password
	 * @return true if update success else false
	 */
	boolean updateNewPassword(String username, String password);

	/**
	 * get all user request reset password
	 * 
	 * @returnList AcceptResetPasswordResponseModel 
	 */
	List<AcceptResetPasswordResponseModel> getAllUserRequestResetPassword();

	/**
	 * accept reset password
	 * 
	 * @param username
	 * @return true if update success else false
	 */
	boolean acceptResetPassword(String username);

	/**
	 * reject reset password
	 * 
	 * @param username
	 * @return true if update success else false
	 */
	boolean rejectResetPassword(String username);
	
	/**
	 * update info Staff
	 * @param id
	 */
	void updateId(int id);

}
