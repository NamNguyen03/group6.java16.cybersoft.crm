
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Project</title>
<link rel="shortcut icon" href="/assets/images/favicon.ico" />
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
								<a href="#">Manage Project</a></li>
								<li class="breadcrumb-item active" aria-current="page">
								Project Information</li>
						</ol>
					</nav>
					<h1 class="m-0">Project Information</h1>
				</div>
				<div class="ml-auto">
					<a href="" class="btn btn-light"><i
						class="material-icons icon-16pt text-muted mr-1">settings</i>
						Settings</a>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<!-- Page Content -->
		<div class="card card-form">
			<div class="row no-gutters">
				<div class="col-lg-4 card-body">
					<p>
						<strong class="headings-color">Description of project
							information</strong>
					</p>
					<p class="text-muted">An e-portal project that connects the
						world with an investment of $100 billion.</p>
				</div>
				<div class="col-lg-8 card-form__body border-left">
					<div class="table-responsive border-bottom" data-toggle="lists"
						data-lists-values='["js-lists-values-employee-name"]'>

						<table class="table mb-0 thead-border-top-0">
							<thead>
								<tr>

									<th style="width: 18px;">
										<div class="custom-control custom-checkbox">
											<input type="checkbox"
												class="custom-control-input js-toggle-check-all"
												data-target="#staff" id="customCheckAll"> <label
												class="custom-control-label" for="customCheckAll"><span
												class="text-hide">Toggle all</span></label>
										</div>
									</th>
									<th>UserName</th>
									<th style="width: 37px;">Position</th>
									<th style="width: 120px;"></th>
									<th style="width: 51px;">CreateBy</th>
									<th style="width: 24px;"></th>
								</tr>
							</thead>
							<tbody class="list" id="staff">
								<tr class="selected">

									<td>
										<div class="custom-control custom-checkbox">
											<input type="checkbox"
												class="custom-control-input js-check-selected-row"
												checked="" id="customCheck1_1"> <label
												class="custom-control-label" for="customCheck1_1"><span
												class="text-hide">Check</span></label>
										</div>
									</td>

									<td>

										<div class="media align-items-center">
											<div class="avatar avatar-xs mr-2">
												<img
													src="<c:url value="/assets/images/256_luke-porter-261779-unsplash.jpg"/>"
													alt="Avatar" class="avatar-img rounded-circle">
											</div>
											<div class="media-body">

												<span class="js-lists-values-employee-name">Michael
													Smith</span>

											</div>
										</div>

									</td>
									<td><span class="badge badge-warning">ADMIN</span></td>
									<td><small class="text-muted"></small></td>
									<td>&dollar;12,402</td>
									<td><a href="" class="text-muted"><i
											class="material-icons">more_vert</i></a></td>
								</tr>
								<tr>

									<td>
										<div class="custom-control custom-checkbox">
											<input type="checkbox"
												class="custom-control-input js-check-selected-row"
												id="customCheck2_1"> <label
												class="custom-control-label" for="customCheck2_1"><span
												class="text-hide">Check</span></label>
										</div>
									</td>

									<td>

										<div class="media align-items-center">
											<img src="<c:url value="/assets/images/avatar/green.svg"/>"
												class="mr-2" alt="avatar" />
											<div class="media-body">

												<span class="js-lists-values-employee-name">Connie
													Smith</span>

											</div>
										</div>

									</td>


									<td><span class="badge badge-success">USER</span></td>
									<td><small class="text-muted"></small></td>
									<td>&dollar;1,943</td>
									<td><a href="" class="text-muted"><i
											class="material-icons">more_vert</i></a></td>
								</tr>
								<tr>

									<td>
										<div class="custom-control custom-checkbox">
											<input type="checkbox"
												class="custom-control-input js-check-selected-row"
												id="customCheck3_1">
												<label
												class="custom-control-label" for="customCheck3_1">
												<span class="text-hide">Check</span>
												</label>
										</div>
									</td>

									<td>${projectname.name}</td>


									<td><span class="badge badge-primary">MANAGER</span></td>
									<td><small class="text-muted">1 week ago</small></td>
									<td>&dollar;1,943</td>
									<td><a href="" class="text-muted"><i
											class="material-icons">more_vert</i></a></td>
								</tr>

							</tbody>
						</table>
					</div>


				</div>
			</div>
		</div>

		<!-- END Page Content -->
	</div>
	<!-- List.js -->
	<script src="<c:url value="/assets/vendor/list.min.js" />"></script>
	<script src="<c:url value="/assets/js/list.js" />"></script>
</body>
</html>