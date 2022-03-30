<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Task</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Status Task</li>
						</ol>
					</nav>
					
				</div>
				<div class="ml-auto">
	                <a ${user.isLeader ? "hidden": ""} href="<c:url value="<%=UrlConst.TASK%>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    				Add New Task
	    			</a>
	            </div>
			</div>
			
		</div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container">
		<div class="card card-form">
			<table class="table mb-0 thead-border-top-0">
				<thead>
					<tr>
						<th>Task ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Username</th>
						<th>Create By</th>
						<th>Create Date</th>
						<th>Status</th>
						<th>#</th>
					</tr>
				</thead>
				<tbody class="list" id="staff02">
					<c:choose>
						<c:when test="${tasks == null}">
							<tr class="row">
								<td class="col-12 text-center">There is no data.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="tasks" items="${tasks}">
								<tr>
							<td>${tasks.id }</td>
							<td>${tasks.taskName }</td>
							<td>${tasks.description }</td>
							<td>${tasks.userName }</td>
							<td>${tasks.createBy }</td>
							<td>${tasks.createDate }</td>
							<td>${tasks.statusName }</td>
									<td><a href="" class="text-muted"><i
											class="material-icons">settings</i></a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- App Settings FAB -->
	<div id="app-settings" hidden>
		<app-settings layout-active="fixed"
			:layout-location="{
      'default': 'index.html',
      'fixed': 'dashboard.html',
      'fluid': 'fluid-dashboard.html',
      'mini': 'mini-dashboard.html'}">
		</app-settings>
	</div>
</body>
</html>