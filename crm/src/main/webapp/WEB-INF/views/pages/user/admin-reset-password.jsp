<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Accept Reset Password</title>
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
						href="<c:url value="<%=UrlConst.ADMIN_RESET_PASSWORD%>" />">Accept
							Reset Password</a></li>
				</ol>
			</nav>
		</div>
		<h1 class="m-0">Accept Reset Password</h1>
	</div>
	<div class="container page_container"
		style="margin-top: 20px; padding: 25px;">
		<div class="card card-form">
			<table>
				<tr>
					<th>Username</th>
					<th>Full name</th>
					<th colspan="2">Action</th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.fullname}" /></td>
						<td>
							<form
								action="<c:url value="<%=UrlConst.ACCEPT_RESET_PASSWORD%>" />"
								method="POST">
								<input type="hidden" name="username" value="${user.username}" />
								<button type="submit" class="btn btn-success">Accept</button>
							</form>
						</td>
						<td>
							<form
								action="<c:url value="<%=UrlConst.REJECT_RESET_PASSWORD%>" />"
								method="POST">
								<input type="hidden" name="username" value="${user.username}" />
								<button type="submit" class="btn btn-danger">Reject</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<c:if test="${notification!=null}">
		<div class="notification p-3" id="notification">
			<div class="d-flex p-2  ${notification.isError ? 'bg-danger' : 'bg-success'}">
				<c:out value="${notification.message}" />
				<div data-v-da9425c4="" data-v-70995076="" class="icon ml-3">
					<i onclick="closeNotification()" data-v-da9425c4="" class="material-icons" style="cursor: pointer;">close</i> 
				</div>
			</div>  
		</div>
	</c:if>
	
	<script type="text/javascript">
		function closeNotification(){
			document.getElementById( 'notification' ).style.display = 'none';
		}
	</script>
</body>