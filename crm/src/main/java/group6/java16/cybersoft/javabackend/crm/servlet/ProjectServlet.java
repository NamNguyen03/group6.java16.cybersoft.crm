package group6.java16.cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.model.Role;
import group6.java16.cybersoft.javabackend.crm.model.User;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.impl.ProjectRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequetModel;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels.ProjectResponse;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectService;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectServiceImpl;
import group6.java16.cybersoft.javabackend.crm.service.role.RoleService;
import group6.java16.cybersoft.javabackend.crm.service.user.UserRequetModels;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.user.UserService;
import group6.java16.cybersoft.javabackend.crm.service.user.UserServiceImpl;
import group6.java16.cybersoft.javabackend.crm.share.model.MyNotification;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

/**
 * 
 * @author Phạm Huy Phần
 *
 */

@WebServlet(name = "projectServlet", urlPatterns = { UrlConst.MANAGE_PROJECT, UrlConst.PROJECT_ADD,
		UrlConst.PROJECT_UPDATE, UrlConst.PROJECT_INFO })
public class ProjectServlet extends HttpServlet {
	private ProjectService projectService;
	private RoleService roleService;
	private UserService userService;

	public ProjectServlet() {
		projectService = new ProjectServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConst.MANAGE_PROJECT:
			List<Project> listProject = projectService.getListProject();
			if (listProject != null && !listProject.isEmpty()) {
				req.setAttribute("projects", listProject);
			}
			req.getRequestDispatcher(JspConst.MANAGE_PROJECT).forward(req, resp);
			break;
		case UrlConst.PROJECT_ADD:
			req.getRequestDispatcher(JspConst.PROJECT_ADD).forward(req, resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			Project project1 = projectService.findById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("project", project1);
			req.getRequestDispatcher(JspConst.PROJECT_UPDATE).forward(req, resp);
			break;
		case UrlConst.PROJECT_INFO:
			Project project = projectService.findById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("project", project);
			req.getRequestDispatcher(JspConst.PROJECT_INFO).forward(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();
		switch (action) {
		case UrlConst.PROJECT_ADD:
			postProjectAdd(req, resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			postProjectUpdate(req, resp);
			break;
		default:
			break;
		}
	}

	private void postProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String status = req.getParameter("status");
		ProjectRequetModel.CreateProjectRequestModel project = new ProjectRequetModel.CreateProjectRequestModel();
		project.setName(name);
		project.setDescription(description);
		project.setStatus(status);
		project.setCreateBy(req.getSession().getAttribute("username").toString());

		boolean rp = projectService.add(project);
		if (rp) {
			req.setAttribute("notification", new MyNotification("Add project successfully", false));
		} else {
			req.setAttribute("notification", new MyNotification("Add project  failed", true));
		}
		req.getRequestDispatcher(JspConst.PROJECT_ADD).forward(req, resp);
	}

	private void postProjectUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		int id = Integer.parseInt(req.getParameter("id"));
		String updatename = req.getParameter("name");
		String updatedescription = req.getParameter("description");
		String updatestatus = req.getParameter("status");
		ProjectRequetModel.UpdateProjectRequestModel project = new ProjectRequetModel.UpdateProjectRequestModel();
		project.setId(id);
		project.setName(updatename);
		project.setDescription(updatedescription);
		project.setStatus(updatestatus);
		project.setUpdateBy(req.getSession().getAttribute("username").toString());

		boolean rp = projectService.update(project);
		if (rp) {
			req.setAttribute("notification", new MyNotification("Updata project successfully", false));
		} else {
			req.setAttribute("notification", new MyNotification("Updata project  failed", true));
		}
		req.setAttribute("project", project);
		req.getRequestDispatcher(JspConst.PROJECT_UPDATE).forward(req, resp);
	}

}
