<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="/webapp/resources/css/app.css" />
</head>
<body>
	<div class="d-flex flex-column vh-100">
		<nav class="navbar navbar-dark bg-dark font-weight-bold">
			<a class="navbar-brand" href="#">
				<img src="${pageContext.request.contextPath}/resources/images/logo-spring.png" width="30" height="30" class="d-inline-block align-top"> Spring
				<%-- <img src="<%=request.getContextPath()%>/resources/images/logo-spring.png" width="30" height="30" class="d-inline-block align-top"> Spring --%>
			</a>
			<div>
				<c:if test="${sessionMid == null}">
					<a href="${pageContext.request.contextPath}/ch08/login" class="btn btn-success btn-sm">로그인</a>
					<%-- <a href="/webapp/ch08/login" class="btn btn-success btn-sm">로그인</a> 위와 똑같다.--%>
				</c:if>
				
				<c:if test="${sessionMid != null}">
					<b class="text-white mr-2">User ID: ${sessionMid}</b>
					<a href="${pageContext.request.contextPath}/ch08/logout" class="btn btn-success btn-sm">로그아웃</a>
				</c:if>
			</div>
			
		</nav>
		<div class="container-fluid flex-grow-1">
			<div class="row h-100">
				<div class="col-md-4 bg-dark p-3">
					<div class="h-100 d-flex flex-column">
						<div class="flex-grow-1" style="height: 0px; overflow-y: auto; overflow-x: hidden;">
							<%@ include file="/WEB-INF/views/common/menu.jsp"%>			
						</div>
					</div>
				</div>
				<div class="col-md-8 p-3">
					<div class="h-100 d-flex flex-column">
						<div class="flex-grow-1 overflow-auto pr-3" style="height: 0px; overflow-y: auto; overflow-x: auto;">