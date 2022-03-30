<%@page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>List Task</title>
<style >
.form-group{
width:120px;

}

</style>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
							<li class="breadcrumb-item"><a href="#">User</a></li>
							<li class="breadcrumb-item active" aria-current="page">Task
							</li>
						</ol>
					</nav>
					<h1 class="m-0">Task Dashboard</h1>
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
						<th>User Name</th>
						<th>Task Name</th>
						<th>ID</th>
						<th>Description</th>
						<th>Status</th>
						<th>#</th>
					</tr>
				</thead>
				<tbody class="list" id="staff02">
					<c:choose>
						<c:when test="${listTask == null}">
							<tr class="row">
								<td class="col-12 text-center">There is no task.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="task" items="${listTask}">
								<tr>
									<td>${task.userName }</td>
									<td>${task.taskName }</td>
									<td>${task.id}</td>
									<td>${task.description }</td>
									<td>${task.statusName }</td>
									
									<form
										action="<c:url value="<%=UrlConst.UPDATE_STATUS_TASK%>" />"
										method="POST">

										<td>
											<div class="form-group">
												 <select
													class="form-control" name="statusTask">
													<c:forEach items="${listStatus}" var="status">
														<option value="${status.statusName}"><c:out
																value="${status.statusName}" /></option>
													</c:forEach>
												</select>
											</div>
										</td> <input type="hidden" name="taskId" value="${task.id}" />
										<td><button type="submit" class="btn btn-primary">Submit</button></td>
									</form>

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

>
