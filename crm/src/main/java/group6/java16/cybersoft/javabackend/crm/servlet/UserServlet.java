/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.impl.UserRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels;
import group6.java16.cybersoft.javabackend.crm.model.Role;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels;
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
		UrlConst.USER_LIST, 
		UrlConst.USER_ADD,
		UrlConst.USER_DELETE
})
public class UserServlet extends HttpServlet {

	private UserService userService;

	public UserServlet() {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {


		case UrlConst.USER_ADD:
			req.getRequestDispatcher(JspConst.USER_ADD).forward(req, resp);
			break;
		case UrlConst.USER_LIST:
			List<User> listUser = userService.getListUser();
			if (listUser != null && !listUser.isEmpty()) {
				req.setAttribute("listUser", listUser);
			}
			req.getRequestDispatcher(JspConst.USER_LIST).forward(req, resp);
			break;
		case UrlConst.USER_DELETE:
			getUserDelete(req, resp);
			resp.sendRedirect(req.getContextPath() + UrlConst.USER_LIST);
			break;

		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {

		case UrlConst.USER_ADD:
			postUserAdd(req, resp);
			break;

		default:
			break;
		}


	}


	/**
	 * @param req
	 * @param resp
	 */
	private void getUserDelete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));

		userService.deleteById(id);

	}

	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		UserRequetModels.CreateUserRequestModel user = new UserRequetModels.CreateUserRequestModel();
		user.setUsername(username);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setAddress(address);
		user.setPhone(phone);

		user.setCreateBy(req.getSession().getAttribute("username").toString());

		boolean check = userService.add(user);
		if (check) {
			resp.sendRedirect(req.getContextPath() + UrlConst.USER_ADD);
		}

	}

}
