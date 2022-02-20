<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<dec:title />
</title>
<jsp:include page="/layout/header.jsp" />
<dec:head />
</head>
<body>
	<div class="preloader"></div>
	
	<!-- Header Layout -->
    <div class="mdk-header-layout js-mdk-header-layout">
    	<jsp:include page="/layout/top-navigation.jsp" />
    	
    	<div class="mdk-header-layout__content page">
    		<jsp:include page="/layout/left-navigation.jsp" />
    		<dec:body />
    	</div>
    </div>
    
	<!-- App Settings FAB -->
    <div id="app-settings" hidden>
        <app-settings layout-active="fixed" :layout-location="{
      'default': 'index.html',
      'fixed': 'dashboard.html',
      'fluid': 'fluid-dashboard.html',
      'mini': 'mini-dashboard.html'}">
        </app-settings>
    </div>
    <jsp:include page="/layout/booter.jsp" />
</body>
</html>