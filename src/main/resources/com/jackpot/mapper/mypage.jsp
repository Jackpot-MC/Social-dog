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
			action="/security/mypage?_csrf=${_csrf.token}" style="width:800px"
			class="mx-auto">

			<h1 class="title">마이페이지</h1>
			<h4>${member.memberName}(${member.loginId})님 반갑습니다.</h4>
		
		<div style="display:flex;">
				<c:forEach var="dog" items="${dogList}">
				<div style="flex:200px; border: 1px solid black; margin-right:10px;">
					<img src="/resources/images/${dog.dogPhotoPath}" style="width:100px; height:100px">

					<h5>이름: ${dog.dogName}</h5>
					<h5>생일: ${dogBirth}</h5>
					<h5>좋아하는것: ${dog.dogFavorite}</h5>
					<h5>참고: ${dog.dogDescription}</h5>
				</div>	
				</c:forEach>
			</div>
		
		
		</form:form>
	</div>
</div>

<%@ include file="../layouts/footer.jsp"%>