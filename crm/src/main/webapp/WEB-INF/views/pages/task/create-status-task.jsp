<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Create Status Task</title>
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

.notification {
	display: flex;
	position: absolute;
	top: 180px;
	right: 30px;
	color: white;
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
						href="<c:url value="<%=UrlConst.CREATE_STATUS_TASK%>" />">Create
							Status Task </a></li>
				</ol>
			</nav>
		</div>
		<h1 class="m-0">Create Status Task</h1>
	</div>
	<div class="container page_container">
		<div class="card card-form"
			style="margin-top: 20px; width: 400px; margin-inline: auto; margin-bottom: 10px; padding: 25px;">
			<form action="<c:url value="<%=UrlConst.CREATE_STATUS_TASK%>" />"
				method="POST">
				<h3 style="text-align: center;">CREATE STATUS TASK</h3>

				<label for="statusName">Status name:</label> <input type="text"
					id="statusName" name="statusName"><br> <br>
				<div>
					<button type="submit" class="btn btn-primary">Create</button>
				</div>
			</form>
		</div>
	</div>

	<div class="container page_container">
		<div class="card card-form"
			style="margin-top: 20px; width: 400px; margin-inline: auto; margin-bottom: 10px; padding: 25px;">
			<table>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${listStatus}" var="task">
					<tr>
						<td><c:out value="${task.id}" /></td>
						<td><c:out value="${task.statusName}" /></td>
						<td>
							<form action="<c:url value="<%=UrlConst.REMOVE_STATUS_TASK%>" />"
								method="POST">
								<input type="hidden" name="id" value="${task.id}" /> 
								<button type="submit" class="btn btn-danger">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<c:if test="${notification!=null}">
		<div class="notification p-3" id="notification">
			<div
				class="d-flex p-2  ${notification.isError ? 'bg-danger' : 'bg-success'}">
				<c:out value="${notification.message}" />
				<div data-v-da9425c4="" data-v-70995076="" class="icon ml-3">
					<i onclick="closeNotification()" data-v-da9425c4=""
						class="material-icons" style="cursor: pointer;">close</i>
				</div>
			</div>
		</div>
	</c:if>
	<script>
		function closeNotification() {
			document.getElementById('notification').style.display = 'none';
		}
	</script>
</body>
