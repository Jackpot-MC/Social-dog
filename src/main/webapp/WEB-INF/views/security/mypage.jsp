<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../security/profilemenu.jsp"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/security/mypage.css" type="text/css" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>


<sec:authentication property="principal.username" var="username" />

<div class="container">
	<div class="d-flex my-3">
		<form:form modelAttribute="member"
			action="/security/mypage?_csrf=${_csrf.token}" style="width:900px"
			class="mx-auto">

			<h1 class="title">마이페이지</h1>
			<h6 class="welcomeM">${member.memberName}(${member.loginId})님 반갑습니다.</h6>

			<div class="cardHolder" style="display: flex;">
				<c:forEach var="dog" items="${dogList}">
					<div class="card card-margin">
						<div class="card-dog">
							<img src="/resources/images/${dog.dogPhotoPath}" class="dog-pic">

							<h5>${dog.dogName}</h5>
							<div class="dog-profile">
								<ul style="list-style:none; padding-left:3px;">
								<li>견종: ${dog.dogKind}</li>
	              				<li>생일: <fmt:formatDate pattern="yyyy-MM-dd" value="${dog.dogBirth}"/></li>
								<li>관심사: ${dog.dogFavorite}</li>
								<li>참고: ${dog.dogDescription}</li>
								</ul>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div>
				<h6>나의 약속</h6>
					<c:forEach var="appointment" items="${appointmentList}">
						<div>
							<ul>
								<li>${appointment.appointmentName}</li>
								<li><fmt:formatDate pattern="yyyy-MM-dd" value="${appointment.regDate}"/></li>
								<li>${appointment.placeId}</li>
							
							</ul>						
						</div>
					</c:forEach>
			</div>
		</form:form>
	</div>
</div>

<%@ include file="../layouts/footer.jsp"%>