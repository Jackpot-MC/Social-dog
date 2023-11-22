<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Date" %>

<%@ include file="../layouts/header.jsp" %>
<link rel="stylesheet" href="/resources/css/login.css" type="text/css"/>
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
        src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<%
    Date d = new Date();
%>


<div class="container">
    <section id="formHolder">

        <div style="width: 600px" class="mx-auto form">

            <form action="/security/login" method="post">
                <h2 class="mt-4">LogIn</h2>
                <h6>반려견과 반려인 모두의 행복</h6>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group mt-4">
                    <label for="loginId"><i class="fa-solid fa-user"></i>아이디</label>
                    <input type="text" name="username" id="loginId" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="loginPwd"><i class="fa-solid fa-lock"></i>비밀번호</label>
                    <input type="password" name="password" id="loginPwd" class="form-control"/>
                </div>

                <div class="form-group form-check">
                    <label class="form-check-label">
                        <input type="checkbox" name="remember-me" class="form-check-input"/> 로그인 유지
                    </label>
                </div>

                <c:if test="${param.error == 'true'}">
                    <div class="error">사용자 ID 또는 비밀번호가 일치하지 않습니다.</div>
                </c:if>

                <c:if test="${param.error == 'login_required'}">
                    <div class="error mt-4">로그인이 필요한 서비스입니다.</div>
                </c:if>

                <div class="CTA text-center">
                    <input type="submit" value="login" class="login"> <br>
                    <br>소셜독이 처음이신가요? <a href="/security/signup" class="switch">Join now</a>
                </div>
            </form>
        </div>
    </section>
</div>
<%@ include file="../layouts/footer.jsp" %>