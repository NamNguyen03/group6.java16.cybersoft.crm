/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.user.UserService;
import group6.java16.cybersoft.javabackend.crm.service.user.UserServiceImpl;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

/**
 * @author nam
 *
 */
@WebServlet(name = "userPasswordServlet", urlPatterns = {
		UrlConst.FORGOT_PASSWORD,
		UrlConst.ACCEPT_RESET_PASSWORD,
		UrlConst.REJECT_RESET_PASSWORD,
		UrlConst.ADMIN_RESET_PASSWORD
})
public class UserPasswordServlet extends HttpServlet  {
	private UserService userService;

	public UserPasswordServlet() {
		userService = new UserServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case UrlConst.FORGOT_PASSWORD:
			req.getRequestDispatcher(JspConst.FORGOT_PASSWORD)
			.forward(req, resp);
			break;
			
		case UrlConst.ADMIN_RESET_PASSWORD:
			getPageAcceptResetPassword(req, resp);
			break;
			
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
			
		case UrlConst.FORGOT_PASSWORD:
			updatePassword(req, resp);
			break;
		
		case UrlConst.ACCEPT_RESET_PASSWORD:
			acceptResetPassword(req, resp);
			break;
			
		case UrlConst.REJECT_RESET_PASSWORD:
			rejectResetPassword(req,resp);
		
		default:
			break;
		}

	}

	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String rePassword = req.getParameter("rePassword");

		if(password == null || rePassword == null || !rePassword.equals(password) ) {
			req.setAttribute("message", "Password not equals password repeat");
			req.getRequestDispatcher(JspConst.FORGOT_PASSWORD)
			.forward(req, resp);
			return;
		}
		userService.updateNewPassword(username, password);
		req.getRequestDispatcher(JspConst.AUTH_LOGIN).forward(req, resp);

	}

	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void acceptResetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");	
		userService.acceptResetPassword(username);
		resp.sendRedirect(req.getContextPath() + UrlConst.ADMIN_RESET_PASSWORD);
	}

	
	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void rejectResetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		userService.rejectResetPassword(username);		
		resp.sendRedirect(req.getContextPath() + UrlConst.ADMIN_RESET_PASSWORD);

	}
	
	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getPageAcceptResetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<UserResponseModels.AcceptResetPasswordResponseModel> users = userService.getAllUserRequestResetPassword();
		req.setAttribute("users", users);
		req.getRequestDispatcher(JspConst.ADMIN_RESET_PASSWORD).forward(req, resp);
	}
}
