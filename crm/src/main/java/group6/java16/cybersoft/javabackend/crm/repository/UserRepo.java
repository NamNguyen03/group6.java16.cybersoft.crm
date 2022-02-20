package group6.java16.cybersoft.javabackend.crm.repository;

import group6.java16.cybersoft.javabackend.crm.model.User;

public interface UserRepo {
	/**
	 * Retrieves an User by its username
	 * 
	 * @param username must not be {@literal null}.
	 * 
	 * @return User if username exists or null if username not exists
	 */
	User findByUsername(String username);
}
