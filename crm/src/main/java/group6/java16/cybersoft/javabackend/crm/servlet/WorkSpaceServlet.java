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
import javax.servlet.http.HttpSession;

import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectService;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectServiceImpl;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

/**
 * @author nam
 *
 */
@WebServlet(name = "workSpace", urlPatterns = {
		UrlConst.WORK_SPACE
})
public class WorkSpaceServlet extends HttpServlet  {
	
	private ProjectService projectService;
	

	public WorkSpaceServlet() {
		projectService = new ProjectServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String projectName = String.valueOf(req.getSession().getAttribute("projectName"));
		if(!"".equals(projectName) && !"null".equals(projectName)) {
			req.setAttribute("projectName", projectName);
		}
		String usernameReq = String.valueOf(req.getSession().getAttribute("username"));
		List<ProjectResponseModels.ProjectResponse> rp = projectService.getAllMyProject(usernameReq);
		req.setAttribute("projects", rp);
		req.getRequestDispatcher(JspConst.WORK_SPACE).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		int id =  Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession(); 
		session.setAttribute("projectName", name);
		session.setAttribute("projectId", id);
		resp.sendRedirect(req.getContextPath() + UrlConst.WORK_SPACE);
	}
	
	
}
