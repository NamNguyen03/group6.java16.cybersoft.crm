package group6.java16.cybersoft.javabackend.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.UserRepoImpl;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

public abstract class AuthFilter implements Filter {

	private UserRepo repo;

	public AuthFilter() {
		repo = new UserRepoImpl();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String servletPath = req.getServletPath();

		if (req.getRequestURI().matches(".*(css|eot|ttf|woff|woff2|svg|jpg|png|gif|js)")) {
			chain.doFilter(request, response);
			return;
		}
		if (servletPath.startsWith(UrlConst.LOGIN) || servletPath.startsWith(UrlConst.FORGOT_PASSWORD)) {
			chain.doFilter(request, response);
		} else {
			String username = String.valueOf(req.getSession().getAttribute("username"));

			if (null == username || "".equals(username) || "null".equals(username)) {
				resp.sendRedirect(req.getContextPath() + UrlConst.LOGIN);
				return;
			}
			if (servletPath.startsWith(UrlConst.ROLE) || servletPath.startsWith(UrlConst.USER_ADD)

					|| servletPath.startsWith(UrlConst.ADMIN_RESET_PASSWORD)
					|| servletPath.startsWith(UrlConst.CREATE_STATUS_TASK)
					|| servletPath.startsWith(UrlConst.USER_LIST) || servletPath.startsWith(UrlConst.PROJECT_ADD)
					|| servletPath.startsWith(UrlConst.PROJECT_UPDATE)) {
        
				if (!repo.isAdmin(username)) {
					resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
					return;
				}
			}


			if (servletPath.startsWith(UrlConst.PROJECT_USER) || 
				servletPath.startsWith(UrlConst.TASK)) {

				try {
					int idProject = Integer.parseInt(String.valueOf(req.getSession().getAttribute("projectId")));
					if (!repo.isLeaderProject(username, idProject)) {
						resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
						return;
					}
				} catch (Exception e) {
					resp.sendRedirect(req.getContextPath() + UrlConst.WORK_SPACE);
					return;
				}
			}

			if (servletPath.startsWith(UrlConst.LIST_TASK)) {

				try {
					Integer.parseInt(String.valueOf(req.getSession().getAttribute("projectId")));
				

				} catch (Exception e) {
					resp.sendRedirect(req.getContextPath() + UrlConst.WORK_SPACE);
					return;
				}
			}


			chain.doFilter(request, response);

		}
	}

}
