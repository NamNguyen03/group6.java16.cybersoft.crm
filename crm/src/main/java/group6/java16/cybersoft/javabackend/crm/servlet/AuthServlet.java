package group6.java16.cybersoft.javabackend.crm.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels.LoginRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.LoginResponseModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserService;
import group6.java16.cybersoft.javabackend.crm.service.user.UserServiceImpl;
import group6.java16.cybersoft.javabackend.crm.util.*;

@WebServlet(name = "loginServlet", urlPatterns = UrlConst.LOGIN)
public class AuthServlet extends HttpServlet {
	
	private UserService userService;
	
	public AuthServlet() {
		userService = new UserServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(); 
		session.setAttribute("fullname", null);
		session.setAttribute("username", null);
		session.setAttribute("projectName",null);
		session.setAttribute("projectId", null);
		req.getRequestDispatcher(JspConst.AUTH_LOGIN)
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		LoginRequestModel loginRequestModel = new LoginRequestModel(email, password);
		LoginResponseModel loginResponseModel = userService.login(loginRequestModel);  
		if(loginResponseModel == null) {
			req.setAttribute("message", "error");
			req.getRequestDispatcher(JspConst.AUTH_LOGIN)
			.forward(req, resp);
			
		}else {
			HttpSession session = req.getSession(); 
			session.setAttribute("fullname", loginResponseModel.getFullname());
			session.setAttribute("username", loginResponseModel.getUsername());
			session.setAttribute("projectName",null);
			session.setAttribute("projectId", null);
			resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
		}
		
	}
}
