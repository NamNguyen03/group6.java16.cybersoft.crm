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
						href="<c:url value="<%=UrlConst.CREATE_STATUS_TASK%>" />">Create Status Task
							</a></li>
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

					<label for="statusName">Status name:</label> 
					<input type="text"
						id="statusName" name="statusName"><br>
					<br>
				
					<p>${message}</p>		
				<div>
					<button type="submit" class="btn btn-primary">Create</button>
				</div>
			</form>
		</div>

		
		</div>
	</div>
	

</body>
