<%@page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>Task</title>
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


	<!-- START BODY -->
	<div class="container">
		<div class="card card-form">
			<table class="table mb-0 thead-border-top-0">
				<thead>
					<tr>
						<th>Task ID</th>
						<th>Task</th>
						<th>Description</th>
						<th>User</th>
						<th>Status</th>
						<th>#</th>
					</tr>
				</thead>
				<tbody class="list" id="staff02">
					<div class="col-lg-8 card-form__body card-body">

						<tr>
							<td>${task.id }</td>
							<td>${task.taskName }</td>
							<td>${task.description }</td>
							<td>${task.userName }</td>

							<td>
								<form action="<c:url value="<%=UrlConst.UPDATE_STATUS_TASK%>" />"
									method="POST">

									<div class="form-group" id="select-project">
										<label for="status">Status</label> 
										<select name="listStatus"
											 id="project" data-toggle="select"
											class="form-control">
											<c:forEach items="${listStatus}" var="status">
												<option value="${status.name}"><c:out
														value="${status.name}" /></option>
											</c:forEach>
										</select>
									</div>


								</form>

							</td>


						</tr>


					</div>
				</tbody>
			</table>
		</div>
	</div>
	<!-- END BODY -->
</body>