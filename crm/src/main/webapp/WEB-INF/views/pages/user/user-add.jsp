<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="group6.java16.cybersoft.javabackend.crm.util.UrlConst"%>
<head>
<meta charset="UTF-8">
<title>Add New User</title>
<style >
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
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.USER_DASHBOARD%>" />">User</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Add New User
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Add New User</h1>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
            <div class="row no-gutters">
                <div class="col-lg-4 card-body">
                    <p><strong class="headings-color">Rules</strong></p>
                    <p class="text-muted">There is no rule!</p>
                </div>
                <div class="col-lg-8 card-form__body card-body">
                    <form action="<c:url value="<%=UrlConst.USER_ADD %>" />" method="post">
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" name="username" id="email">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" name="password" id="password">
                        </div>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" name="fullname" id="name">
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone:</label>
                            <input type="text" class="form-control" name="phone" id="phone">
                        </div>
                         <div class="form-group">
                            <label for="address">Address:</label>
                            <textarea type="text" class="form-control" name="address" id="address" aria-label="With textarea"></textarea>
                        </div>
                        <button class="btn btn-primary w-25 justify-content-center" type="submit" class="btn btn-primary">Add</button>
                    </form>
                </div>
            </div>
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
	<script>
		function closeNotification(){
			document.getElementById( 'notification' ).style.display = 'none';
		}
	</script>
</body>
