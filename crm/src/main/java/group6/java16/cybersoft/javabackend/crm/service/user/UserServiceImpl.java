package group6.java16.cybersoft.javabackend.crm.service.user;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.UserRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.LoginRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.LoginResponseModel;

public class UserServiceImpl implements UserService {

	private UserRepo userRepo;

	public UserServiceImpl() {
		userRepo = new UserRepoImpl();
	}

	@Override
	public LoginResponseModel login(LoginRequestModel loginRequestModel) {
		User user = userRepo.findByUsername(loginRequestModel.getUsername());
		if(user.getPassword() == null) {
			return null;
		}
		if(user.getPassword().equals(loginRequestModel.getPassword())) {
			return new LoginResponseModel(user.getUsername(), user.getFullname());
		}
		return null;
	}

}
