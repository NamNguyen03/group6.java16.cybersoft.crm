package group6.java16.cybersoft.javabackend.crm.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequetModel;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectService;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectServiceImpl;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskService;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskServiceImpl;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "taskServlet", urlPatterns = { UrlConst.TASK, UrlConst.UPDATE_TASK, UrlConst.LIST_TASK,
		UrlConst.ASSIGNMENT_TASK })
public class TaskServlet extends HttpServlet {

	private TaskService taskService;

	public TaskServlet() {
		taskService = new TaskServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConst.LIST_TASK:
			List<Task> listTask = taskService.getListTask();
			if (listTask != null && !listTask.isEmpty()) {
				req.setAttribute("tasks", listTask);
			}
			req.getRequestDispatcher(JspConst.LIST_TASK).forward(req, resp);
			break;
		case UrlConst.TASK:
			req.getRequestDispatcher(JspConst.TASK).forward(req, resp);
			break;
		case UrlConst.ASSIGNMENT_TASK:
			req.getRequestDispatcher(JspConst.ASSIGNMENT_TASK).forward(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConst.TASK:
			postTaskAdd(req, resp);
			break;
		case UrlConst.UPDATE_TASK:
			postTaskUpdate(req, resp);
			break;
		default:
			break;
		}
	}

	private void postTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		TaskRequestModel.CreateTaskRequestModel task = new TaskRequestModel.CreateTaskRequestModel();
		task.setName(name);
		task.setDescription(description);
		task.setCreateBy(req.getSession().getAttribute("username").toString());
		taskService.add(task);
		List<Task> listTask = taskService.getListTask();
		if (listTask != null && !listTask.isEmpty()) {
			req.setAttribute("tasks", listTask);
		}
		resp.sendRedirect(req.getContextPath() + UrlConst.TASK);

	}

	private void postTaskUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		int id = Integer.parseInt(req.getParameter("id"));
		String updatename = req.getParameter("name");
		String updatedescription = req.getParameter("description");
		TaskRequestModel.UpdateTaskRequestModel task = new TaskRequestModel.UpdateTaskRequestModel();
		task.setId(id);
		task.setName(updatename);
		task.setDescription(updatedescription);
		task.setUpdateBy(req.getSession().getAttribute("username").toString());

		boolean check = taskService.update(task);
		if (check) {
			req.getRequestDispatcher(JspConst.PROJECT_UPDATE).forward(req, resp);
		}
	}
}
