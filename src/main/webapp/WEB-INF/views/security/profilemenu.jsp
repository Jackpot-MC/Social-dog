<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<style>
#menu {
	padding: 20px;
}
</style>

<section id="menu" class="justify-content-center">
	<nav
		class="navbar navbar-expand-sm bg-light navbar-light justify-content-center">
		<!-- nav 적용  -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/security/mypage">마이페이지</a></li>
			<li class="nav-item"><a class="nav-link" href="/security/profile">내 정보 수정</a></li>
			<li class="nav-item"><a class="nav-link" href="/dog/modify">강아지 정보 수정</a></li>
			<li class="nav-item"><a class="nav-link" href="/security/pwdupdate">비밀번호 변경</a></li>
			<li class="nav-item"><a class="nav-link" href="#">즐겨찾는 장소</a></li>
		</ul>
	</nav>
</section>
