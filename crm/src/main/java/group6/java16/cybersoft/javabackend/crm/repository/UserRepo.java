package group6.java16.cybersoft.javabackend.crm.repository;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;

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
	
	/**
	 * Retrieves an User by its username
	 * 
	 * @param usernam
	 * 
	 * @return true if role of user is leader else false
	 */
	boolean isLeader(String username);

	/**
	 * get all user in DB
	 * 
	 * @return List<User>
	 */
	List<UserResponseModels.UserResponseModel> getAll();

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

}
