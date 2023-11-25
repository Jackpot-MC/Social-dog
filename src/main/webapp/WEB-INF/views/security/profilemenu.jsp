<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<nav class="navbar navbar-default">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.member.loginId"	var="loginId" />
		<!-- nav 적용  -->
		<ul id="nav1" class="nav justify-content-start">
			<li class="nav-item"><a class="nav-link" href="/security/profile">내 정보 수정</a></li>
			<li class="nav-item"><a class="nav-link" href="/dog/modify">강아지 정보 수정</a></li>
			<li class="nav-item"><a class="nav-link" href="/security/pwdupdate">비밀번호 변경</a></li>
			<li class="nav-item"><a class="nav-link" href="#">알림</a></li>
		</ul>
	</sec:authorize>
</nav>	
		
