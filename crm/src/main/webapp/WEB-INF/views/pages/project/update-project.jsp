<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Projects</title>
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
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Update Project</li>
						</ol>
					</nav>
					<h1 class="m-0">Update Project</h1>
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
						<div class="tab-pane active show fade" id="tab-22">
							<div class="container page__container">
								<div class="card card-form">
									<div class="row no-gutters">
										<div class="col-lg-4 card-body">
											<p>
												<strong class="headings-color">Form update project</strong>
											</p>
											<p class="text-muted">Please enter the exact project id
												you need to update then enter all the information you need
												to update....If in the list of companies does not exist your
												id will find it, it will automatically create a new one and
												add it to the list. book</p>
										</div>
										<div class="col-lg-8 card-form__body card-body">
											<form
												action="<c:url value="<%=UrlConst.PROJECT_UPDATE%>" />"
												method="post">
												<div class="form-group">
													<label for="exampleInputPassword1">Project ID to
														Update:</label> 
														<input disabled="disabled" value="${project.id}" type="text"
														class="form-control"required="" >
														
														<input name="id" value="${project.id}" type="hidden"
														class="form-control"required="" >
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Project Name ( NEW )
														:</label> 
														<input name="name" type="text" value="${project.name}"
														class="form-control" id="exampleInputPassword1"
														placeholder="Enter your name .." required="">
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1"> Project
														Description ( NEW ):</label> 
														<input name="description" value="${project.description}"
														type="text" class="form-control"
														id="exampleInputPassword1"
														placeholder="Enter your description .." required="">
												</div>
												<div class="form-group">
													<label for="exampleInputPassword1">Status New ( NEW )
														:</label> 
														<select value="${project.status}" name="status" class="form-control"
														id="status">
														<option value="Start">Start</option>
														<option value="Doing">Doing</option>
														<option value="Late">Late</option>
														<option value="complete">Complete</option>
													</select>
												</div>
												<div style="width: 50%; margin-inline: auto;">
													<button name="update" value="update"
														style="float: left;" type="submit" class="btn btn-primary">Update</button>
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
	<script>
		function closeNotification(){
			document.getElementById( 'notification' ).style.display = 'none';
		}
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
</html>