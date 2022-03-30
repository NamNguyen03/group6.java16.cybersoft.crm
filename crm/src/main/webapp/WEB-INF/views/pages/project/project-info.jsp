<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project Details</title>
<style>
@charset "UTF-8";

@import
	url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,700);

body {
	font-family: 'Open Sans', sans-serif;
	font-weight: 300;
	line-height: 1.42em;
	color: #A7A1AE;
	background-color: #1F2739;
}

h1 {
	font-size: 3em;
	font-weight: 300;
	line-height: 1em;
	text-align: center;
	color: #4DC3FA;
}

h2 {
	font-size: 1em;
	font-weight: 300;
	text-align: center;
	display: block;
	line-height: 1em;
	padding-bottom: 2em;
	color: #FB667A;
}

h2 a {
	font-weight: 700;
	text-transform: uppercase;
	color: #FB667A;
	text-decoration: none;
}

.blue {
	color: #185875;
}

.yellow {
	color: #FFF842;
}

.container th h1 {
	font-weight: bold;
	font-size: 1em;
	text-align: left;
	color: #185875;
}

.container td {
	font-weight: normal;
	font-size: 1em;
	-webkit-box-shadow: 0 2px 2px -2px #0E1119;
	-moz-box-shadow: 0 2px 2px -2px #0E1119;
	box-shadow: 0 2px 2px -2px #0E1119;
}

.container {
	text-align: left;
	overflow: hidden;
	width: 80%;
	margin: 0 auto;
	display: table;
}

.container td, .container th {
	padding-bottom: 2%;
	padding-top: 2%;
	padding-left: 2%;
}

/* Background-color of the odd rows */
.container tr:nth-child(odd) {
	background-color: #323C50;
}

/* Background-color of the even rows */
.container tr:nth-child(even) {
	background-color: #2C3446;
}

.container th {
	background-color: #1F2739;
}

.container td:first-child {
	color: #FB667A;
}

.container tr:hover {
	background-color: #464A52;
	-webkit-box-shadow: 0 6px 6px -6px #0E1119;
	-moz-box-shadow: 0 6px 6px -6px #0E1119;
	box-shadow: 0 6px 6px -6px #0E1119;
}

.container td:hover {
	background-color: #FFF842;
	color: #403E10;
	font-weight: bold;
	box-shadow: #7F7C21 -1px 1px, #7F7C21 -2px 2px, #7F7C21 -3px 3px,
		#7F7C21 -4px 4px, #7F7C21 -5px 5px, #7F7C21 -6px 6px;
	transform: translate3d(6px, -6px, 0);
	transition-delay: 0s;
	transition-duration: 0.4s;
	transition-property: all;
	transition-timing-function: line;
}
/* CSS Button*/
.button1 {
	background-image: linear-gradient(135deg, #008aff, #86d472);
	border-radius: 6px;
	box-sizing: border-box;
	color: #ffffff;
	display: block;
	height: 50px;
	font-size: 1.4em;
	font-weight: 600;
	padding: 4px;
	position: relative;
	text-decoration: none;
	width: 200px;
	z-index: 2;
}

.button1:hover {
	color: #fff;
}

.button1 .btn1 {
	align-items: center;
	background: #0e0e10;
	border-radius: 6px;
	display: flex;
	justify-content: center;
	height: 100%;
	transition: background 0.5s ease;
}

.button1:hover .btn1 {
	background: transparent;
}
</style>
</head>
<body>
	<div class="container page__heading-container">
		<div class="page__heading">
			<div class="d-flex align-items-center">
				<div>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0">
							<li class="breadcrumb-item"><a
								href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">
								Project Detail</li>
						</ol>
					</nav>
					<h1 class="m-0">Project Detail</h1>
				</div>
				<div class="ml-auto">
					<a ${user.isAdmin ? "hidden": ""}
						href="<c:url value="<%=UrlConst.MANAGE_PROJECT%>" />"
						class="btn btn-light"><i
						class=" fas fa-arrow-left icon-16pt text-muted mr-1"></i> Back </a>
				</div>
			</div>
		</div>
	</div>
	<h1>
		<span>Project Details</pan>
	</h1>
	<form action="<c:url value="<%=UrlConst.PROJECT_INFO%>"/>"
		method="post">
		<table class="container">
			<tbody>
					<tr>
						<td>Project Name</td>
						
						<td style="color: white"><c:out value="${project.name}" /></td>
					</tr>
					<tr>
						<td>Description</td>
						<td style="color: white">${project.description}</td>
					</tr>
					<tr>
						<td>Status</td>
						<td style="color: white">${project.status}</td>
					</tr>
			</tbody>
		</table>
	</form>
	<div style="margin-top: 5%">
		<a class="button1"
			href="<c:url value="<%=UrlConst.ADD_PROJECT_USER%>" />"
			style="float: left; margin-left: 32%;"> <span class="btn1">List
				User Project</span>
		</a>
		
		<a class="button1" style="float: left; margin-left: 10%;"
			href="<c:url value="<%=UrlConst.PROJECT_UPDATE%>"> <c:param name="id" value="${project.id}"/> </c:url>">  <span class="btn1">Update
				Project</span>
		</a>
	</div>
</body>
</html>