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
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

@WebFilter(urlPatterns = UrlConst.GLOBAL)
public class AuthFilter implements Filter{

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
			String status = String.valueOf(req.getSession().getAttribute("user"));
			if(status.equals("null"))
				resp.sendRedirect(req.getContextPath() + UrlConst.LOGIN);
			else
				chain.doFilter(request, response);
		}
	}

}