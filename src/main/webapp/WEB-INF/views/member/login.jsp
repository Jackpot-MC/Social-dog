<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Date"%>

<%@ include file ="../layouts/header.jsp" %>
<%
	Date d = new Date();
%>

<body>
  
	<div style="width:500px" class="mx-auto">

		<c:if test="${param.error == 'true'}">
			<div class="error">
				사용자 ID 또는 비밀번호가 일치하지 않습니다.
			</div>
		</c:if>
		
		<c:if test="${param.error == 'login_required'}">
			<div class="error mt-4">
				로그인이 필요한 서비스입니다.
			</div>
		</c:if>

      <form action="/member/login" method="post">
      <h1 class="mt-4"><i class="fa-solid fa-right-to-bracket"></i> 사용자 로그인</h1>
      	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      	<div class="form-group mt-4">
           	<label for="memberLoginId"><i class="fa-solid fa-user"></i> 사용자 ID:</label>
      		<input type="text" name="memberLoginId" id="memberLoginId" class="form-control" />
      	</div>

      	<div class="form-group">
           	<label for="memberLoginPwd"><i class="fa-solid fa-lock"></i> 비밀번호:</label>
      		<input type="password" name="memberLoginPwd" id="memberLoginPwd" class="form-control" />
      	</div>
      	
       	<div class="form-group form-check">
           	<label class="form-check-label">
      		<input type="checkbox" name="remember-me" class="form-check-input" /> 로그인 유지
      		</label>		
      	</div>
      	
      	<button type="submit" class="btn btn-primary btn-block">
      		<i class="fa-solid fa-right-to-bracket"></i>로그인
      	</button>
      	
   		<a href="/member/signup" class="btn btn-primary btn-block" role="button">
   			<i class="fa-solid fa-user-plus"></i>회원가입
   		</a>
   		
      </form>
     </div>
  </body>
<%@ include file ="../layouts/footer.jsp" %>