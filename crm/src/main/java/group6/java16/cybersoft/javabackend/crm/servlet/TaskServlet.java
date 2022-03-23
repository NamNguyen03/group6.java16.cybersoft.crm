/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.mysql.cj.x.protobuf.MysqlxNotice.GroupReplicationStateChanged;

import group6.java16.cybersoft.javabackend.crm.model.Role;
import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels;
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
@WebServlet(name = "taskServlet", urlPatterns = { UrlConst.TASK,
		UrlConst.LIST_TASK,
		UrlConst.UPDATE_STATUS_TASK,
		UrlConst.CREATE_STATUS_TASK})
public class TaskServlet extends HttpServlet {
	private TaskService taskService;

	public TaskServlet() {
		taskService = new TaskServiceImpl();
		

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {

		case UrlConst.LIST_TASK:
			getListTask(req, resp);
			break;
		case UrlConst.TASK:
			getTaskById(req, resp);
			break;
		case UrlConst.CREATE_STATUS_TASK:
			req.getRequestDispatcher(JspConst.CREATE_STATUS_TASK).forward(req, resp);
			break;
		default:
			break;

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case UrlConst.UPDATE_STATUS_TASK:
			updateStatusTask(req, resp);
			break;
		case UrlConst.LIST_TASK:
			getListTask(req, resp);
			
			break;
		case UrlConst.TASK:
			getTaskById(req, resp);
			break;
		case UrlConst.CREATE_STATUS_TASK:
			createStatusTask(req, resp);
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
	private void updateStatusTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int task_id = Integer.parseInt(req.getParameter("taskId"));
		String status_name = req.getParameter("statusTask");
		System.out.println(status_name);
		taskService.updateStatusTask(task_id,status_name);
		getListTask(req, resp);

	}

	private void getPageUpdateStatusTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		UpdateStatusTaskResponse task = new UpdateStatusTaskResponse();
		int task_id = Integer.parseInt(req.getAttribute("id").toString());
		System.out.println(task_id);
		task = taskService.getTaskUpdateById(task_id);
		req.setAttribute("task", task);
		req.getRequestDispatcher(JspConst.UPDATE_STATUS_TASK).forward(req, resp);

	}

	private void getListTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		int project_id = Integer.parseInt((session.getAttribute("projectId")).toString());

		List<TaskResponseModels.TaskResponse> listTask = taskService.getListTaskByProjectId(project_id);
		List<StatusTask> listStatus = taskService.getListStatusTask();
		req.setAttribute("listStatus", listStatus );
		req.setAttribute("listTask", listTask);
		req.getRequestDispatcher(JspConst.LIST_TASK).forward(req, resp);
	}
	private void getTaskById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int task_id = Integer.parseInt(req.getParameter("id"));
		
		TaskResponseModels.TaskResponse task = taskService.findById(task_id);
		req.setAttribute("task", task);
		req.getRequestDispatcher(JspConst.TASK).forward(req, resp);;
	}
	
	private void createStatusTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String status_name = req.getParameter("statusName");
		boolean exists = taskService.existByName(status_name);
		if(!exists) {
			req.setAttribute("message", "invalid status name !!");

		}else{
		boolean create = taskService.createStatusTask(status_name);
		if(create) {
			req.setAttribute("message", "create success!!");;
		}else {
			req.setAttribute("message", "create failed!!");
		}
		}
		req.getRequestDispatcher(JspConst.CREATE_STATUS_TASK).forward(req, resp);;

	}
}
