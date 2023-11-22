<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/member-header.jsp"%>
<link rel="stylesheet" href="/resources/css/profile.css" type="text/css" />

<sec:authentication property="principal.username" var="username" />

<div class="container">
	<section id="formHolder">
		<div class="d-flex my-3">
			<form:form modelAttribute="member" action="/member/profile?_csrf=${_csrf.token}" 
						style="width:500px" class="mx-auto">
				<!-- 첨부파일 기능 때문에 액션에 암호화 코드 넣음 -->
				<h1 class="mt-4">마이페이지</h1>

                <div class="form-group mt-4">
                    <form:label path="memberLoginId">아이디</form:label>
                    <form:hidden path="memberLoginId"  class="form-control" />
                    <div id="loginid" class="form-control">${memberLoginId}</div>
                </div>
                <div class="form-group mt-4">
                    <form:label path="memberLoginPwd">새 비밀번호 입력</form:label>
                    <form:password path="memberLoginPwd" cssClass="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="memberLoginPwd2">새 비밀번호 확인</form:label>
                    <form:password path="memberLoginPwd2" cssClass="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="memberName">이름</form:label>
                    <form:input path="memberName" cssClass="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="memberBirth">생년월일</form:label>
                    <form:input path="memberBirth"  cssClass="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="memberEmail">이메일</form:label>
                    <form:input path="memberEmail" cssClass="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="memberAddress">주소</form:label>
                    <form:input path="memberAddress" cssClass="form-control" />
                </div>

            	<div class="CTA text-center">
               		<button type="submit" class="update">수정</button>    
                	<button type="reset" class="reset">취소</button>    
				</div>
			</form:form>
		</div>
	</section>
</div>


<%@ include file="../layouts/footer.jsp"%>