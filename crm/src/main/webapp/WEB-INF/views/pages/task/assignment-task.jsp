<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Update Role</title>
<head>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	padding: 8px;
	text-align: left;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<div class="container page__heading-container"
		style="margin-top: 20px;">
		<div class="d-flex align-items-center">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb mb-0">
					<li class="breadcrumb-item"><a
						href="<c:url value="<%=UrlConst.HOME%>" />">Dashboard</a></li>
					<li class="breadcrumb-item"><a
						href="<c:url value="<%=UrlConst.TASK%>" />">Add Task</a></li>
				</ol>
			</nav>
		</div>
	</div>
	<div class="container page_container">
		<div class="card card-form"
			style="margin-top: 20px; width: 400px; margin-inline: auto; margin-bottom: 10px; padding: 25px;">
			<form action="<c:url value="<%=UrlConst.UPDATE_ROLE%>" />"
				method="POST">
				<h3 style="text-align: center;">ASSIGNMENT TASK</h3>
				<div class="form-group">
					<label for="role">Project Name</label> <select name="roleName"
						onchange="selectRole()" id="role" data-toggle="select"
						class="form-control">
						<c:forEach items="${roles}" var="role">
							<option value="${role.name}"><c:out value="${role.name}" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="role">UserName</label> <select name="roleName"
						onchange="selectRole()" id="role" data-toggle="select"
						class="form-control">
						<c:forEach items="${roles}" var="role">
							<option value="${role.name}"><c:out value="${role.name}" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="role">Status Task</label> <select name="roleName"
						onchange="selectRole()" id="role" data-toggle="select"
						class="form-control">
						<c:forEach items="${roles}" var="role">
							<option value="${role.name}"><c:out value="${role.name}" /></option>
						</c:forEach>
					</select>

				</div>
				<div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>
		<div class="card card-form">
			<table class="table table-striped border-bottom mb-0">
				<tr>
					<td>ID</td>
					<td>Task Name</td>
					<td>Description</td>
					<td>Status</td>
					<td>CreateBy</td>
					<td>CreatDate</td>
					<td>&emsp;...</td>
				</tr>
				<tbody>
					<c:choose>
						<c:when test="${projects== null}">
							<tr class="row">
								<td class="col-12s">This is no data</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="projects" items="${projects}">
								<tr>
									<td>${projects.id}</td>
									<td style="width: 200px">
										<div>
											<a href="#" class="text-15pt d-flex align-items-center">
												<i class="material-icons icon-16pt mr-1">business</i> <strong
												name="projects">${projects.name}</strong>
											</a>
										</div>
									</td>
									<td>${projects.description}</td>
									<td class="text-right" style="width: 70px">
										<div class="badge badge-soft-success">${projects.status}</div>
									</td>
									<td>${projects.createBy}</td>

									<td>${projects.createDate}</td>

									<td><input type="submit" value="Xem"></td>

								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
				</div>
				</div>
				<script>
					document.getElementById("select-project").style.visibility = "hidden";
					function selectRole() {
						var role = document.getElementById("role").value;
						if (role == "LEADER" || role == "MEMBER") {
							document.getElementById("select-project").style.visibility = "";
						} else {
							document.getElementById("select-project").style.visibility = "hidden";
						}
					}
				</script>
</body>
