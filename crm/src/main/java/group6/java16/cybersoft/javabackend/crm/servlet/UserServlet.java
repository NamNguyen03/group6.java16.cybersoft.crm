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

import group6.java16.cybersoft.javabackend.crm.model.Role;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequestModels;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectService;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectServiceImpl;
import group6.java16.cybersoft.javabackend.crm.service.role.RoleRequestModels;
import group6.java16.cybersoft.javabackend.crm.service.role.RoleService;
import group6.java16.cybersoft.javabackend.crm.service.role.RoleServiceImpl;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.user.UserService;
import group6.java16.cybersoft.javabackend.crm.service.user.UserServiceImpl;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

/**
 * @author nam
 *
 */
@WebServlet(name = "userServlet", urlPatterns = {
		UrlConst.UPDATE_ROLE, 
		UrlConst.REMOVE_ROLE,
		UrlConst.FORGOT_PASSWORD,
		UrlConst.ACCEPT_RESET_PASSWORD,
		UrlConst.REJECT_RESET_PASSWORD
})
public class UserServlet extends HttpServlet {

	private RoleService roleService;
	private UserService userService;
	private ProjectService projectService;

	public UserServlet() {
		roleService = new RoleServiceImpl();
		userService = new UserServiceImpl();
		projectService = new ProjectServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case UrlConst.UPDATE_ROLE:
			getPageUpdateRole(req, resp);
			break;

		case UrlConst.FORGOT_PASSWORD:
			req.getRequestDispatcher(JspConst.FORGOT_PASSWORD)
			.forward(req, resp);
			break;
			
		case UrlConst.ACCEPT_RESET_PASSWORD:
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
		case UrlConst.UPDATE_ROLE:
			updateRole(req, resp);
			break;

		case UrlConst.REMOVE_ROLE:
			removeRole(req, resp);
			break;
			
		case UrlConst.FORGOT_PASSWORD:
			updatePassword(req, resp);
			break;
		
		case UrlConst.ACCEPT_RESET_PASSWORD:
			acceptResetPassword(req, resp);
			break;
			
		case UrlConst.REJECT_RESET_PASSWORD:
			rejectResetPassword(req,resp);
			break;
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
		
		boolean rp = userService.updateNewPassword(username, password);
		

		req.getRequestDispatcher(JspConst.AUTH_LOGIN)
		.forward(req, resp);

	}

	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void removeRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		String username = req.getParameter("username");
		String roleName = req.getParameter("roleName");
		String projectName = req.getParameter("projectName");
		String usernameReq = String.valueOf(req.getSession().getAttribute("username"));

		boolean rp = roleService.removeRole(new RoleRequestModels.RemoveRoleRequestModel(idUser, username, roleName, projectName, usernameReq));
	
		
		getPageUpdateRole(req, resp);

	}

	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String roleName =  req.getParameter("roleName");
		String projectName =  req.getParameter("projectName");
		String usernameReq = String.valueOf(req.getSession().getAttribute("username"));

		boolean rp = roleService.saveRole(new RoleRequestModels.UpdateRoleRequest(roleName, email, projectName, usernameReq));

		
		getPageUpdateRole(req, resp);
	}

	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getPageUpdateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Role> roles = roleService.getAll();
		req.setAttribute("roles", roles);
		String roleName = req.getParameter("name");
		roleName = roleName == null || "".equals(roleName) ? "all" : roleName;
		req.setAttribute("name",roleName );

		List<UserResponseModels.UserResponseModel> users = "all".equals(roleName) ? userService.getAll() : userService.findByRoleName(roleName);
		req.setAttribute("users", users);

		List<ProjectRequestModels.ProjectRoleResponse> projects = projectService.getAllProjectByLeaderIsNull();
		req.setAttribute("projects", projects);

		req.getRequestDispatcher(JspConst.UPDATE_ROLE)
		.forward(req, resp);

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
		
		req.getRequestDispatcher(JspConst.ACCEPT_RESET_PASSWORD)
		.forward(req, resp);
	}

	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void acceptResetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		
		boolean rp = userService.acceptResetPassword(username);
		
		getPageAcceptResetPassword(req, resp);
	}

	
	/**
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void rejectResetPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		
		boolean rp = userService.rejectResetPassword(username);
		
		getPageAcceptResetPassword(req, resp);
		
	}
}
