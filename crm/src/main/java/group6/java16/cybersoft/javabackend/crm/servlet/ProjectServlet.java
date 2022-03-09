package group6.java16.cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.repository.MySQLConnection;
import group6.java16.cybersoft.javabackend.crm.repository.impl.ProjectRepoImpl;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectService;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectServiceImpl;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "projectServlet", urlPatterns = UrlConst.MANAGE_PROJECT)
public class ProjectServlet extends HttpServlet {
	private ProjectServiceImpl projectServiceImpl;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		projectServiceImpl = new ProjectServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case UrlConst.MANAGE_PROJECT:
			getListProject(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	private void getListProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				List<Project> projects = projectServiceImpl.findAll();
				if(projects != null) {
					req.setAttribute("projects", projects);
				}
				req.getRequestDispatcher(JspConst.MANAGE_PROJECT).forward(req, resp);
	}
}
