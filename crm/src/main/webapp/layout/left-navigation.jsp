<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page__header mb-0">
		<% if(session.getAttribute("projectName") != null) {%>
		<div class="container page__container" style="width: 100%; text-align: center; padding-top: 15px; font-size: 20px; font-weight: 600">
	    		Work Space <%= session.getAttribute("projectName") %>
	    </div>
		<%} %>
    <div class="container page__container">
        <div class="navbar navbar-secondary navbar-light navbar-expand-sm p-0">
            <button class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarsExample03" type="button">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapse" id="navbarsExample03">
                <ul class="nav navbar-nav flex">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="<%=UrlConst.HOME %>" />">
                            Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="<%=UrlConst.WORK_SPACE %>" />"> Work Space</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            Project
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.MANAGE_PROJECT%>" />">
                                Manage Project
                            </a>
                             <a class="dropdown-item" href="<c:url value="<%=UrlConst.PROJECT_USER%>" />">
                                Add User In Project
                            </a>
                            <a class="dropdown-item" href="#">
                                Project Create Project
                            </a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                            User
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.USER_LIST%>" />">
                                User List
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.USER_ADD%>" />">
                                Create User
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.ROLE %>" />">
                                Role
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.ADMIN_RESET_PASSWORD %>" />">
                               	Accept Reset Password
                            </a>
                            <a class="dropdown-item" href="<c:url value="<%=UrlConst.CREATE_STATUS_TASK%>" />">
                                Create Status Task
                            </a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="<%=UrlConst.LIST_TASK%>" />">Task</a>
                       
                    </li>
                  
                </ul>
            </div>
        </div>
    </div>
    
</div>