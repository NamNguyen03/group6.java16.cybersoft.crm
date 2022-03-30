<%@page import="group6.java16.cybersoft.javabackend.crm.util.JspConst"%>
<%@page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Project</title>
</head>
<body>
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Manage Project</li>
						</ol>
					</nav>
					<h1 class="m-0">Manage Project</h1>
				</div>
				<div class="ml-auto">
					<a ${user.isAdmin ? "hidden": ""}
						href="<c:url value="<%=UrlConst.PROJECT_ADD%>" />"
						class="btn btn-light"><i
						class="material-icons icon-16pt text-muted mr-1">add</i> Add New
						Project </a>
				</div>
			</div>
		</div>
	</div>
	<div class="container page__container">
		<!-- Xep hang -->
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header card-header-large bg-white">
						<h4 class="card-header__title">Collection of projects</h4>
					</div>
					<table class="table table-striped border-bottom mb-0">
						<tr>
							<td>ID</td>
							<td>Name</td>
							<td>Status</td>
							<td>CreateBy</td>
							<td>CreatDate</td>
						</tr>
						<tbody>
							<c:choose>
								<c:when test="${projects== null}">
									<tr class="row">
										<td class="col-12s">This is no data</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="project" items="${projects}">
										<tr>
											<td>${project.id}</td>
											
											
											<td style="width: 200px">
												<a href="<c:url value="<%=UrlConst.PROJECT_INFO%>"> <c:param name="id" value="${project.id}"/> </c:url>"> 
												<i
												class="material-icons icon-16pt mr-1">business</i> <strong
												name="projectsName">${project.name}</strong> </a>
											</td>


											<td class="text-right" style="width: 70px">
												<div class="badge badge-soft-success">${project.status}</div>
											</td>
											<td>${project.createBy}</td>

											<td>${project.createDate}</td>

										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="card-footer text-center border-0">
						<a class="text-muted" href="">View All (391)</a>
					</div>
				</div>
			</div>
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