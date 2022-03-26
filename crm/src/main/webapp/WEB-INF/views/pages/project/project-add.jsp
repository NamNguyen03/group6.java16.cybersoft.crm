<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Projects</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Forms</li>
						</ol>
					</nav>
					<h1 class="m-0">Add Project</h1>
				</div>
				<div class="ml-auto">
	                <a ${user.isAdmin ? "hidden": ""} href="<c:url value="<%=UrlConst.MANAGE_PROJECT%>" />" class="btn btn-light"><i class=" fas fa-arrow-left icon-16pt text-muted mr-1"></i>
	    				Back
	    			</a>
	            </div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<!-- Page Content -->
		<div class="col-lg">
			<div class="z-0">
				<div class="card">
					<div class="card-body tab-content">
						<div class="tab-pane active show fade" id="tab-21">
							<div class="container page__container">
								<div class="card card-form">
									<div class="row no-gutters">
										<div class="col-lg-4 card-body">
											<p>
												<strong class="headings-color">Form create project</strong>
											</p>
											<p class="text-muted">Please enter all your project
												information here it will automatically generate for a
												project to add to the company</p>
										</div>
										<div class="col-lg-8 card-form__body card-body">
											<form
												action="<c:url value="<%=UrlConst.PROJECT_ADD%>" />"
												method="post">
												<div class="form-group">
													<label for="exampleInputPassword1">Project Name:</label> <input
														name="name" type="text" class="form-control"
														id="exampleInputPassword1"
														placeholder="Enter your name ..">
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Description:</label> <input
														name="description" type="text" class="form-control"
														id="exampleInputPassword1"
														placeholder="Enter your ddescription ..">
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Status:</label> <select
														name="status" class="form-control" id="status">
														<option value="Doing">Doing</option>
														<option value="Late">Late</option>
														<option value="Complete">Complete</option>
													</select>
												</div>
												<div style="width: 50%; margin-inline: auto;">
													<button name="add-update" value="add" style="float: left;"
														type="submit" class="btn btn-primary">Add</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END Page Content -->
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