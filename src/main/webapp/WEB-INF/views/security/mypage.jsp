<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../security/profilemenu.jsp"%>
<link rel="stylesheet" href="/resources/css/profile.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/appointment/list.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>


<sec:authentication property="principal.username" var="username" />

<div class="container">
	<div class="d-flex my-3">
		<form:form modelAttribute="member"
			action="/security/mypage?_csrf=${_csrf.token}" style="width:500px"
			class="mx-auto">

			<h1 class="title">마이페이지</h1>
			<h4>${member.memberName}(${member.loginId})님 반갑습니다.</h4>

		</form:form>
	</div>
</div>

<%@ include file="../layouts/footer.jsp"%>