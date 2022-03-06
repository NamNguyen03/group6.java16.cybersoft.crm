package group6.java16.cybersoft.javabackend.crm.service.user;

import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.UserRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.LoginRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.AcceptResetPasswordResponseModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.LoginResponseModel;

public class UserServiceImpl implements UserService {

	private UserRepo userRepo;

	public UserServiceImpl() {
		userRepo = new UserRepoImpl();
	}

	@Override
	public LoginResponseModel login(LoginRequestModel loginRequestModel) {
		User user = userRepo.findByUsername(loginRequestModel.getUsername());
		if(user == null || user.getPassword() == null) {
			return null;
		}
		if(user.getPassword().equals(loginRequestModel.getPassword())) {
			return new LoginResponseModel(user.getUsername(), user.getFullname());
		}
		return null;
	}

	@Override
	public List<UserResponseModels.UserResponseModel> getAll() {
		return userRepo.getAll();
	}

	@Override
	public List<UserResponseModels.UserResponseModel> findByRoleName(String roleName) {
		return userRepo.findByRoleName(roleName);
	}

	@Override
	public boolean updateNewPassword(String username, String password) {
		if(!userRepo.existsByUsername(username)) {
			return false;
		}
		
		return userRepo.updateNewPassword(username,password);
	}

	@Override
	public List<AcceptResetPasswordResponseModel> getAllUserRequestResetPassword() {
		return userRepo.getAllUserRequestResetPassword() ;
	}

	@Override
	public boolean acceptResetPassword(String username) {
		if(username == null || username.equals("")) {
			return false;
		}
		if(!userRepo.existsByUsername(username)) {
			return false;
		}
		
		return userRepo.acceptResetPassword(username);
	}

	@Override
	public boolean rejectResetPassword(String username) {
		if(username == null || username.equals("")) {
			return false;
		}
		if(!userRepo.existsByUsername(username)) {
			return false;
		}
		
		return userRepo.RejectResetPassword(username);
	}

}
