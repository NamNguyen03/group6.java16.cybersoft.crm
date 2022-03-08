<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Update Password</title>
<link rel="shortcut icon"
	href="<c:url value="assets/images/favicon.ico" />" />

<!-- Perfect Scrollbar -->
<link type="text/css"
	href="<c:url value="assets/vendor/perfect-scrollbar.css" />"
	rel="stylesheet">

<!-- App CSS -->
<link type="text/css" href="<c:url value="assets/css/app.css" />"
	rel="stylesheet">
<link type="text/css" href="<c:url value="assets/css/app.rtl.css" />"
	rel="stylesheet">

<!-- Material Design Icons -->
<link type="text/css"
	href="<c:url value="assets/css/vendor-material-icons.css" />"
	rel="stylesheet">
<link type="text/css"
	href="<c:url value="assets/css/vendor-material-icons.rtl.css" />"
	rel="stylesheet">

<!-- Font Awesome FREE Icons -->
<link type="text/css"
	href="<c:url value="assets/css/vendor-fontawesome-free.css" />"
	rel="stylesheet">
<link type="text/css"
	href="<c:url value="assets/css/vendor-fontawesome-free.rtl.css" />"
	rel="stylesheet">
</head>

<body class="layout-fixed">
	<div class="mdk-header-layout js-mdk-header-layout">
		<div class="container page__container">
			<!-- Page Content -->
			<div class="card card-form">

				<div class="card-form__body card-body">
					<h3 class="">Reset Password</h3>
					<form  action="<c:url value="<%=UrlConst.FORGOT_PASSWORD%>" />" method="post">
						<div class="form-group">
							<label for="exampleInputEmail1">Your email:</label> <input
								type="email" name="username" class="form-control" required=""
								placeholder="Enter your email address ..">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Your password:</label> <input
								type="password" name="password" class="form-control" required=""
								placeholder="Enter your password .."> 
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Your password Repeat:</label>
							<input type="password" name="rePassword" class="form-control" required=""
								placeholder="Enter your password Repeat ..">
						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
		<c:if test="${message!=null}">
		<div style="height: 44px; right: 10px; top: 10px; position: absolute;">
			<div
				class="alert alert-dismissible bg-danger text-white border-0 fade show"
				role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<c:out value="${message}" />
			</div>
		</div>
	</c:if>
</body>