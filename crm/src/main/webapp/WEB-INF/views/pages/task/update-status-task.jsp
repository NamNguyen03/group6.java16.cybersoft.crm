<%@page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="#">User</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            User Dashboard
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">User Dashboard</h1>
	            </div>
	            
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	
	<!-- START BODY -->
	<div class="container">
		<div class="card card-form">
            <table class="table mb-0 thead-border-top-0">
                <thead>
                    <tr>
	                     <th>Project</th>
						 <th>Name</th>
	                     <th>Task</th>
	                     <th>Description</th>
	                     <th>Status</th>
		              	 <th>#</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:otherwise>
                 			<c:forEach var="task" items="${task}" >
	                 			<tr>
		                           <td>
		                               ${task.projectName  }
		                           </td>
		                           <td>${users }</td>
		                           <td>${task_name }</td>
		                           <td>${description }</td>
		                           <td>${status }</td>		                           
		                           <td>
		                           	<a href="" class="text-muted"><i class="material-icons">settings</i></a>
		                           	<a href="<c:url value="<%=UrlConst.USER_DELETE%>" />?id=${t_user.id}" class="text-muted"><i class="material-icons">delete</i></a>
		                           </td>
	                    		</tr>
                 			</c:forEach>
                 		</c:otherwise>
                 	</c:choose>
               	</tbody>
            </table>
		</div>
	</div>
	<!-- END BODY -->
</body>