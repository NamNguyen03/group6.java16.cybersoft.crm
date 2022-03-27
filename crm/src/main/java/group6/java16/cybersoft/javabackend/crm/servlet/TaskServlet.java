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

import group6.java16.cybersoft.javabackend.crm.model.Project;
import group6.java16.cybersoft.javabackend.crm.model.Task;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectRequetModel;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectService;
import group6.java16.cybersoft.javabackend.crm.service.project.ProjectServiceImpl;
import group6.java16.cybersoft.javabackend.crm.service.status.StatusResponseModels.StatusTask;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskRequestModel;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.TaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskResponseModels.UpdateStatusTaskResponse;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels;
import group6.java16.cybersoft.javabackend.crm.service.user.UserService;
import group6.java16.cybersoft.javabackend.crm.service.user.UserServiceImpl;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.GetUserInProjectResponseModel;
import group6.java16.cybersoft.javabackend.crm.service.user.UserResponseModels.UserResponseModel;
import group6.java16.cybersoft.javabackend.crm.share.model.MyNotification;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskService;
import group6.java16.cybersoft.javabackend.crm.service.task.TaskServiceImpl;
import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

@WebServlet(name = "taskServlet", urlPatterns = { UrlConst.TASK, UrlConst.UPDATE_TASK, UrlConst.LIST_TASK })
public class TaskServlet extends HttpServlet {

	private TaskService taskService;
	private UserService userService;

	public TaskServlet() {
		taskService = new TaskServiceImpl();
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {

		case UrlConst.LIST_TASK:
			try {
				int idProject = Integer.parseInt(req.getSession().getAttribute("projectId").toString());
				List<TaskResponse> listTask = taskService.getListTaskByProjectId(idProject);
				req.setAttribute("tasks", listTask);

			} catch (Exception e) {
			}
			req.getRequestDispatcher(JspConst.LIST_TASK).forward(req, resp);
			break;

		case UrlConst.TASK:
				int idProject = Integer.parseInt(req.getSession().getAttribute("projectId").toString());
				List<GetUserInProjectResponseModel> users = userService.findAllUserInProject(idProject);
				req.setAttribute("users", users);
				List<TaskResponse> listTask = taskService.getListTaskByProjectId(idProject);
				req.setAttribute("tasks", listTask);

			req.getRequestDispatcher(JspConst.TASK).forward(req, resp);
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
			try {
				int idProject = Integer.parseInt(req.getSession().getAttribute("projectId").toString());
				List<GetUserInProjectResponseModel> users = userService.findAllUserInProject(idProject);
				req.setAttribute("users", users);
				List<TaskResponse> listTask = taskService.getListTaskByProjectId(idProject);
				req.setAttribute("tasks", listTask);

			} catch (Exception e) {
			}
			req.getRequestDispatcher(JspConst.TASK).forward(req, resp);
			break;
		case UrlConst.UPDATE_TASK:
			postTaskUpdate(req, resp);
			break;
		case UrlConst.UPDATE_STATUS_TASK:
			updateStatusTask(req, resp);
			break;
		case UrlConst.LIST_TASK:
			getListTask(req, resp);
			break;
		// case UrlConst.TASK:
		// getTaskById(req, resp);
		// break;
		case UrlConst.CREATE_STATUS_TASK:
			createStatusTask(req, resp);
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
		task.setProjectId(Integer.parseInt(req.getSession().getAttribute("projectId").toString()));
		String userID = req.getParameter("userId");
		task.setUserId(Integer.parseInt(userID));
		boolean rp = taskService.add(task);
		List<Task> listTask = taskService.getListTask();
		if (listTask != null && !listTask.isEmpty()) {
			req.setAttribute("tasks", listTask);
		}
		if (rp) {
			req.setAttribute("notification", new MyNotification("Add task successfully", false));
		} else {
			req.setAttribute("notification", new MyNotification("Add task  failed", true));
		}
		int idProject = Integer.parseInt(req.getSession().getAttribute("projectId").toString());
		List<GetUserInProjectResponseModel> users = userService.findAllUserInProject(idProject);
		req.setAttribute("users", users);
		List<TaskResponse> tasks = taskService.getListTaskByProjectId(idProject);
		req.setAttribute("tasks", tasks);
		req.getRequestDispatcher(JspConst.TASK).forward(req, resp);

	}

	private void postTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

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
		taskService.updateStatusTask(task_id, status_name);
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
		req.setAttribute("listTask", listTask);
		req.getRequestDispatcher(JspConst.TASK).forward(req, resp);
	}

	private void createStatusTask(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String status_name = req.getParameter("statusName");
		boolean exists = taskService.existByName(status_name);
		if (!exists) {
			req.setAttribute("message", "invalid status name !!");

		} else {
			boolean create = taskService.createStatusTask(status_name);
			if (create) {
				req.setAttribute("message", "create success!!");
				;
			} else {
				req.setAttribute("message", "create failed!!");
			}
		}
		req.getRequestDispatcher(JspConst.CREATE_STATUS_TASK).forward(req, resp);
		;

	}
}
