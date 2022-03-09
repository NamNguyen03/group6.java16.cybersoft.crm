<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Update Role</title>
<head>
<style>
.grid-container {
  display: grid;
  grid-template-columns: auto auto auto;
  gap: 15px;
  padding: 10px;
}
.grid-item {
  border: 1px solid gray;
  padding: 20px;
  border-radius: 10px !important;
  display: flex;
  flex-direction: column;
  width: 100%;
  background-color: white;
  
}
.grid-item:hover {
	background-color:#ccc; 
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
						href="<c:url value="<%=UrlConst.WORK_SPACE %>" />">Work Space</a></li>
				</ol>
			</nav>
		</div>
		<h1 class="m-0">Work Space</h1>
	</div>
	<div class="container page_container card card-form" style="margin-top: 20px; ">
		<div class="grid-container">
			 <c:forEach items="${projects}" var="project">
			 	<form action="<c:url value="<%=UrlConst.WORK_SPACE %>" />" method="POST" >
			 	
			 		<button type="submit" class="grid-item">
			 			<h3><c:out value="${project.name}" /></h3>
					 	<small><c:out value="${project.description}" /></small>
					 	<p><b><c:out value="${project.status}" /></b></p>
			 		</button>
					<input type="hidden" name="name" value="${project.name}" />
					<input type="hidden" name="id" value="${project.id}" />
			 	</form>
			 	
			 </c:forEach>
		</div>
	</div>
</body>