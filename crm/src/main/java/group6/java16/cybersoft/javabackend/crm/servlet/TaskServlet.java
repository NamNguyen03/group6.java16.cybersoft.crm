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
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskService;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskServiceImpl;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

/**
 * @author trunghau
 *
 */
@WebServlet(name = "taskServlet", urlPatterns = {
		UrlConst.UPDATE_STATUS_TASK
		})
public class TaskServlet  extends HttpServlet{
	private TaskService taskService;
	public TaskServlet() {
		taskService = new TaskServiceImpl();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getPageUpdateStatusTask(req, resp);
	
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case UrlConst.UPDATE_STATUS_TASK:
			updateStatusTask(req, resp);
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
	private void updateStatusTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int statusId = Integer.parseInt(req.getParameter("update_status"));
		taskService.updateStatusTask(statusId);
		getPageUpdateStatusTask(req, resp);
		
	}
	private void getPageUpdateStatusTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UpdateStatusTaskResponse task = new UpdateStatusTaskResponse();
		int task_id = Integer.parseInt(req.getAttribute("id").toString());
		task = taskService.getTaskUpdateById(task_id);
		req.setAttribute("project", task.getProjectName());
		req.setAttribute("task_name", task.getTaskName());
		req.setAttribute("users", task.getUserName());
		req.setAttribute("status", task.getStatusName());
		req.setAttribute("description", task.getDescription());

		req.getRequestDispatcher(JspConst.UPDATE_STATUS_TASK)
		.forward(req, resp);

	}
}
