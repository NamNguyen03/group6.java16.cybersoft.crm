package group6.java16.cybersoft.javabackend.crm.service.user;

import java.sql.SQLException;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.UserRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.CreateUserRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.LoginRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.LoginResponseModel;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;

public class UserServiceImpl implements UserService {

	private UserRepo userRepo;

	public UserServiceImpl() {
		userRepo = new UserRepoImpl();
	}

	@Override
	public LoginResponseModel login(LoginRequestModel loginRequestModel) {
		User user = userRepo.findByUsername(loginRequestModel.getUsername());
		if (user.getPassword() == null) {
			return null;
		}
		if (user.getPassword().equals(loginRequestModel.getPassword())) {
			return new LoginResponseModel(user.getUsername(), user.getFullname());
		}
		return null;
	}

	@Override
	public boolean add(CreateUserRequestModel userRequest) {
		if (userRequest.getUsername() == null || userRequest.getPassword() == null || userRequest.getAddress() == null
				|| userRequest.getPhone() == null || userRequest.getCreateBy() == null) {
			return false;
		}
		if (userRepo.checkExist(userRequest)) {
			return false;
		}
		if (!userRepo.isAdmin(userRequest.getCreateBy())) {
			return false;
		}

		User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		user.setFullname(userRequest.getFullname());
		user.setAddress(userRequest.getAddress());
		user.setPhone(userRequest.getPhone());
		user.setCreateBy(userRequest.getCreateBy());

		return userRepo.add(user);
	}
	
	@Override
	public List<User> getListUser(){
		List<User> listUser = null;
		
			listUser = userRepo.getListUser();
	
			return listUser;
	}
	@Override
	public void deleteById(int id) {
		userRepo.deleteById(id);
		System.out.println("service");
	}	
	
}
