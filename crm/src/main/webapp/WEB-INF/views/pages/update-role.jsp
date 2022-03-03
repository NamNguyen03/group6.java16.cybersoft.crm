<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Update Role</title>
<head>
<style>
table {
	font-family: arial, sans-serif;
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
	<div class="container page__heading-container" style="margin-top: 20px; ">
		<div class="d-flex align-items-center">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb mb-0">
					<li class="breadcrumb-item"><a
						href="<c:url value="<%=UrlConst.HOME %>" />">Dashboard</a></li>
					<li class="breadcrumb-item"><a
						href="<c:url value="<%=UrlConst.UPDATE_ROLE %>" />">Update
							Role</a></li>
				</ol>
			</nav>
		</div>
		<h1 class="m-0">Update Role</h1>
	</div>
	<div class="container page_container">
		<div class="card card-form"
			style="margin-top: 20px; width: 400px; margin-inline: auto; margin-bottom: 10px; padding: 25px;">
			<form action="<c:url value="<%=UrlConst.UPDATE_ROLE %>" />" method="POST">
				<h3 style="text-align: center;">ADD ROLE</h3>
				<div>
					<div class="form-group">
						<label for="exampleInputEmail1">Email:</label> <input type="email"
							class="form-control" id="exampleInputEmail1" name="email"
							placeholder="Enter your email address ..">
					</div>
				</div>
				<div class="form-group">
					<label for="role">Role</label> 
					<select name="roleName" onchange="selectRole()" id="role" data-toggle="select" class="form-control">
						<c:forEach items="${roles}" var="role">
							<option value="${role.name}"><c:out value="${role.name}" /></option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group"  id="select-project" >
					<label for="role">Project</label> 
					<select  name="projectName" onchange="selectRole()" id="project" data-toggle="select" class="form-control">
						<c:forEach items="${projects}" var="project">
							<option value="${project.name}"><c:out value="${project.name}" /></option>
						</c:forEach>
					</select>
				</div>
				<div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>

		<div class="card card-form">
			<div class="card-header card-header-tabs-basic nav "role="tablist">
				<a class='${name == "all" ? "active": "" }'
					href="<c:url value="<%=UrlConst.UPDATE_ROLE%>"></c:url>">All</a>
				<c:forEach items="${roles}" var="role">
					<a class='${name == role.name ? "active": "" }'
						href="<c:url value="<%=UrlConst.UPDATE_ROLE%>"> <c:param name="name" value="${role.name}" /></c:url>"><c:out
							value="${role.name}" /></a>
				</c:forEach>
			</div>
			<table>
				<tr>
					<th>Username</th>
					<th>Full name</th>
					<th>Role</th>
					<th>Project Name</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.fullname}" /></td>
						<td><c:out value="${user.roleName}" /></td>
						<td><c:out value="${user.projectName}" /></td>
						<td>
							<form action="<c:url value="<%=UrlConst.REMOVE_ROLE %>" />" method="POST">
								<input type="hidden" name="idUser" value="${user.idUser}" />
								<input type="hidden" name="username" value="${user.username}" />
								<input type="hidden" name="roleName" value="${user.roleName}" />
								<input type="hidden" name="projectName" value="${user.projectName}" />
								<button type="submit" class="btn btn-danger" >Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script>
		document.getElementById("select-project").style.visibility = "hidden";
		function selectRole() {
		  	var role = document.getElementById("role").value;
		 	if(role == "LEADER" || role == "MEMBER"){
		 		document.getElementById("select-project").style.visibility = "";
		 	}else{
		 		document.getElementById("select-project").style.visibility = "hidden";
		 	}	
		}
	</script>
	
</body>
