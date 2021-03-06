package group6.java16.cybersoft.javabackend.crm.service.user;

import java.sql.SQLException;
import java.util.List;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.UserRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequetModel.UpdateProjectRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.CreateUserRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.LoginRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.UpdateUserRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.AcceptResetPasswordResponseModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.GetUserInProjectResponseModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.LoginResponseModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.UserResponseModel;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;

public class UserServiceImpl implements UserService {

	private UserRepo userRepo;

	public UserServiceImpl() {
		userRepo = new UserRepoImpl();
	}

	@Override
	public LoginResponseModel login(LoginRequestModel loginRequestModel) {

		User user = userRepo.findByUsername(loginRequestModel.getUsername());
		if (user == null || user.getPassword() == null) {
			return null;
		}
		if (user.getPassword().equals(loginRequestModel.getPassword())) {
			return new LoginResponseModel(user.getUsername(), user.getFullname());
		}
		return null;
	}

	@Override
	public boolean add(CreateUserRequestModel userRequest) {
		if (userRequest.getUsername() == null  || userRequest.getAddress() == null
				|| userRequest.getPhone() == null || userRequest.getCreateBy() == null) {
			return false;
		}
		if (userRepo.existsByUsername(userRequest.getUsername())) {
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
	public List<User> getListUser() {

		return userRepo.getListUser();
	}

	@Override
	public void deleteById(int id) {
		try {
			userRepo.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("service");
	}

	@Override
	public List<UserResponseModels.UserResponseModel> getAllUserAndRole() {
		return userRepo.getAllUserAndRole();
	}

	@Override
	public List<UserResponseModels.UserResponseModel> findByRoleName(String roleName) {
		return userRepo.findByRoleName(roleName);
	}

	@Override
	public boolean updateNewPassword(String username, String password) {
		System.out.println(username);
		if(!userRepo.existsByUsername(username)) {
			return false;
		}
		System.out.println(username);
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

	@Override
	public void updateId(int id) {
		try {
			userRepo.updateById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean update(UpdateUserRequestModel userRequest) {
		
		if (userRequest.getUsername() == null || userRequest.getAddress() == null
				|| userRequest.getPhone() == null || userRequest.getUpdateBy() == null) {
			return false;
		}
		if (!userRepo.isAdmin(userRequest.getUpdateBy())) {
			return false;
		}

		User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		user.setFullname(userRequest.getFullname());
		user.setAddress(userRequest.getAddress());
		user.setPhone(userRequest.getPhone());
		user.setCreateBy(userRequest.getUpdateBy());

		return userRepo.update(user);
	}
	public List<GetUserInProjectResponseModel> findAllUserInProject(int idProject) {
		return userRepo.findAllUserInProject(idProject);
	}

}
