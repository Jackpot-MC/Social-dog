<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" href="/resources/css/layouts/menu.css" type="text/css" />

<nav class="navbar navbar-expand-md bg-warning navbar-dark sticky-top">
	<a class="navbar-brand" href="/">
	<img class="socialDog-logo mr-auto" style="width:145px;margin:0px;padding:0px;" src="../resources/images/SocialDog-logo-white.png"/></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		<!--메뉴구성-->
		<ul class="navbar-nav ml-auto justify-content-center">
			<li class="nav-item-first"><a class="nav-link" id="nav-text" href="/walk">산책하기</a></li>
			<li class="nav-item-first"><a class="nav-link" id="nav-text" href="/appointment/my_appointment">나의약속</a></li>
			<li class="nav-item-first"><a class="nav-link" id="nav-text" href="/appointment/attend_appointment">산책기록</a></li>
			<li class="nav-item-first"><a class="nav-link" id="nav-text" href="/board/list">커뮤니티</a>
			<li class="nav-item-first"><a class="nav-link" id="nav-text" href="/notice/list">공지사항</a>	</li>
		</ul>

		<ul class="navbar-nav ml-auto">
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.member.loginId"	var="loginId" />

				<!-- 로그인 상태 -->
				<li class="nav-item"><a class="nav-link" id="nav-text" href="/security/mypage"> 마이페이지 </a></li>
				<li class="nav-item"><a class="nav-link logout-link" id="nav-text" href="#">
						<i class="fa-solid fa-right-from-bracket"></i> 로그아웃
				</a></li>
			</sec:authorize>
		</ul>

		<!-- 로그아웃 된 상태 -->
		<sec:authorize access="isAnonymous()">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="/security/login">
						<i class="fa-solid fa-right-from-bracket"></i> 로그인
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/security/signup"> <i class="fa-solid fa-user-plus"></i>
						회원가입
				</a></li>
			</ul>
		</sec:authorize>
	</div>
</nav>

<form id="logoutForm" action="/security/logout" method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

<script>
	$(document).ready(function() {
		$('.logout-link').click(function(e) {
			e.preventDefault();
			$('#logoutForm').submit();
		});
	});
</script>

