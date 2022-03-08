package group6.java16.cybersoft.javabackend.crm.repository;

import java.util.List;
import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.AcceptResetPasswordResponseModel;

public interface UserRepo {
	/**
	 * Retrieves an User by its username
	 * 
	 * @param username 
	 * 
	 * @return User if username exists or null if username not exists
	 */
	User findByUsername(String username);
	
	/**
	 * Retrieves an User by its username
	 * 
	 * @param username 
	 * 
	 * @return true if role of user is admin else false
	 */
	boolean isAdmin(String username);
	
	boolean add(User user) ;

	/**
	 * @param user
	 * @return
	 */
	boolean checkExistByUsername(String username);

	/**
	 * @return
	 */
	List<User> getListUser();

	/**
	 * @param id
	 */
	void deleteById(int id);
	/**
	 * Retrieves an User by its username
	 * 
	 * @param usernam
	 * 
	 * @return true if role of user is leader else false
	 */
	boolean isLeader(String username);

	/**
	 * get all user and role in DB
	 * 
	 * @return List<User>
	 */
	List<UserResponseModels.UserResponseModel> getAllUserAndRole();

	/**
	 * find all user in DB have role.name equals roleName 
	 * 
	 * @param roleName
	 * @return List<User> 
	 */
	List<UserResponseModels.UserResponseModel> findByRoleName(String roleName);

	/**
	 * check id user exists
	 * 
	 * @param idUser
	 * @return true if id exists else false
	 */
	boolean existsById(int id);

	/**
	 * find User by id
	 * 
	 * @param idUser
	 * @return User if id exists else return null
	 */
	User findById(int id);

	/**
	 * check username exists
	 * 
	 * @param username
	 * @return true if username exists else false
	 */
	boolean existsByUsername(String username);

	/**
	 * update new password
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
	boolean RejectResetPassword(String username);

}
