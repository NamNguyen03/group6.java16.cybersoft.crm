/**
 * 
 */
package group6.java16.cybersoft.javabackend.crm.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;

import group6.java16.cybersoft.javabackend.crm.util.UrlConst;

/**
 * @author nam
 *
 */
@WebFilter(urlPatterns = UrlConst.GLOBAL)
public class MyAuthFilter extends AuthFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init auth filter");
    }

    @Override
    public void destroy() {
       System.out.println("destroy auth filter");
    }

}
