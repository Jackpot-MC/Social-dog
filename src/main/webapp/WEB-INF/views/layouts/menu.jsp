<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<nav class="navbar navbar-expand-md bg-warning navbar-dark sticky-top">
	<a class="navbar-brand" href="/"> <i class="fa-solid fa-paw"></i>
		Social Dogs
	</a>

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar">

		<!--메뉴구성-->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="#">산책하기</a></li>
			<li class="nav-item"><a class="nav-link" href="#">나의약속</a></li>
			<li class="nav-item"><a class="nav-link" href="#">산책기록</a></li>
			<li class="nav-item"><a class="nav-link" href="/notice/list"></i>커뮤니티</a>
			</li>
		</ul>


		<ul class="navbar-nav ml-auto">
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.member.loginId"	var="loginId" />

				<!-- 로그인 상태 -->
				<li class="nav-item"><a class="nav-link" href="/security/profile"> 마이페이지 </a></li>
				<li class="nav-item"><a class="nav-link logout-link" href="#">
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

		/* $(function() {
			//사이드메뉴 온오프
			$('.btn-menu').on('click', function() {
				$('.side-menu').show();
				$('body').addClass('lock');
			});
			$('.side-menu-wrap').on('click', function(e) {
				e.preventDefault();
				e.stopPropagation();
				if ($(this).parent().hasClass('side-menu')) {
					if (e.target.className == 'side-menu-wrap') {
						$(this).parent().hide();
						$('body').removeClass('lock');
					}
				}
			});

			//snb 메뉴 클릭
			$('.snb ul li').on('click', function() {
				$(this).toggleClass('on');
			});
		}); */
		
	});
</script>


<%-- <ul class="navbar-nav ml-auto">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.member.loginId" var="loginId" />
		<!-- username 스코프 생성 -->

		<!-- 로그인 상태 -->

		<!-- 사이드 메뉴 -->
		<header id="header">
			<section class="nav-top clearfix">
				<div class="left">
					<a href="javascript:void(0)" class="btn-menu"> <i
						class="fa-solid fa-bars fa-2x" class="respon-img"></i>
					</a>
				</div>
			</section>

			<section id ="menu" class="side-menu">
				<div class="side-menu-wrap">
					<div class="side-menu-box">
						<div class="side-menu-top">
							<div class="side-menu-profile">${member.memberName}
								(${member.loginId})</div>

							<div class="navbar-nav">
								<ul>
									<li class="nav-item"><a class="nav-link" href="/security/profile">내 정보 수정</a></li>
								
									<li><input type="button" class="" value="내 정보 수정" onclick="location.href='/security/profile'"/>
									<li><input type="button" class="" value="강아지 정보 수정" onclick="location.href='/security/doginfo'"/>
									<li><input type="button" class="" value="비밀번호 변경" onclick="location.href='/security/pwdchange"/>
									<li><input type="button" class="" value="산책 기록 보기" onclick="location.href='#'"/>
									<li><input type="button" class="" value="내 약속 보기" onclick="location.href='#'"/>
								</ul>
							</div>

							<div class="snb">
								<ul>
									<li class="doglist">마이 펫</li>
									<li class="on"><a href="javascript:void(0)">앙꼬</a>
										<ul>
											<li><a href="javascript:void(0)">강아지정보1</a></li>
											<li><a href="javascript:void(0)">강아지정보2</a></li>
										</ul>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</section>
		</header>
		<!-- 사이드 메뉴 끝-->
	</sec:authorize>
</ul> --%>
