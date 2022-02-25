/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group6.java16.cybersoft.javabackend.crm.util.JspConst;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

/**
 * @author nam
 *
 */
@WebServlet(name = "userServlet", urlPatterns = UrlConst.UPDATE_ROLE)
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case UrlConst.UPDATE_ROLE:
			req.getRequestDispatcher(JspConst.UPDATE_ROLE)
			.forward(req, resp);
			break;

		default:
			break;
		}
	}
}
