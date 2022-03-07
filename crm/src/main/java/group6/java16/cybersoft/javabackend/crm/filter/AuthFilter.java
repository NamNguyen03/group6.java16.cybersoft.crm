package group6.java16.cybersoft.javabackend.crm.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import group6.java16.cybersoft.javabackend.crm.repository.UserRepo;
import group6.java16.cybersoft.javabackend.crm.repository.impl.UserRepoImpl;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

@WebFilter(urlPatterns = UrlConst.GLOBAL)
public class AuthFilter implements Filter{

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

		if(req.getRequestURI().matches(".*(css|eot|ttf|woff|woff2|svg|jpg|png|gif|js)")){
			chain.doFilter(request, response);
			return;
		}

		if(servletPath.startsWith(UrlConst.LOGIN)){
			chain.doFilter(request, response);
		}
		else {
			String username = String.valueOf(req.getSession().getAttribute("username"));

			if(null == username || "".equals(username) || "null".equals(username)) {
				resp.sendRedirect(req.getContextPath() + UrlConst.LOGIN);
				return;
			}
			if(servletPath.startsWith(UrlConst.UPDATE_ROLE)) {
				if(!repo.isAdmin(username)) {
					resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
					return;
				}
			}
			
			chain.doFilter(request, response);
			
				
		}
	}

}