package group6.java16.cybersoft.javabackend.crm.filter;

import javax.servlet.annotation.WebFilter;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import group6.java16.cybersoft.javabackend.crm.util.UrlConst;


@WebFilter(displayName = "sitemesh", urlPatterns = UrlConst.GLOBAL)
public class LayoutFilter extends SiteMeshFilter {
	
}
