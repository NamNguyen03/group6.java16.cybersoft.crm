package group6.java16.cybersoft.javabackend.crm.repository;

import java.sql.SQLException;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.CreateUserRequestModel;

public interface UserRepo {
	/**
	 * Retrieves an User by its username
	 * 
	 * @param username must not be {@literal null}.
	 * 
	 * @return User if username exists or null if username not exists
	 */
	User findByUsername(String username);
	
	/**
	 * Retrieves an User by its username
	 * 
	 * @param username must not be {@literal null}.
	 * 
	 * @return true if role of user is admin else false
	 */
	boolean isAdmin(String username);
	
	boolean add(User user) ;

	/**
	 * @param user
	 * @return
	 */
	boolean checkExist(CreateUserRequestModel user);

	/**
	 * @return
	 */
	List<User> getListUser();

	/**
	 * @param id
	 */
	void deleteById(int id);

}
