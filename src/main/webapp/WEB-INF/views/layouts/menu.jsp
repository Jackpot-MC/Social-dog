<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<nav class="navbar navbar-expand-md bg-warning navbar-dark sticky-top">
	<a class="navbar-brand" href="/">
    	<i class="fa-solid fa-paw"></i> Social Dogs</a>

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
	
		<!--메뉴구성-->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="#">산책하기</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">나의약속</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">산책기록</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="/notice/list"></i>커뮤니티</a>
			</li>
		</ul>

		<ul class="navbar-nav ml-auto">
		<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.username" var="username"/> <!-- username 스코프 생성 -->
				
		<!-- 로그인 상태 -->
			<li class="nav-item">
				<a class="nav-link" href="/member/profile">
					
					${memberName}
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link logout-link" href="#">
					<i class="fa-solid fa-right-from-bracket"></i> 로그아웃
				</a>
			</li>
		</sec:authorize>
		</ul>
		
		<!-- 로그아웃 된 상태 -->
		<sec:authorize access="isAnonymous()">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a class="nav-link" href="/member/login">
					<i class="fa-solid fa-right-from-bracket"></i> 로그인
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/member/signup">
					<i class="fa-solid fa-user-plus"></i> 회원가입
				</a>
			</li>
		</ul>
		</sec:authorize>
</div>
</nav>

<form id="logoutForm" action="/member/logout" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>
$(document).ready(function() {
	$('.logout-link').click(function(e){
		e.preventDefault();
		$('#logoutForm').submit();
	});	
});
</script>