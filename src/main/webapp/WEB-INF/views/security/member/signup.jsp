<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../../layouts/member-header.jsp"%>
<link rel="stylesheet" href="/resources/css/signup.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
 
<script>
$(document).ready(function () {
});

$(".selector").attr({
    "type" : "date",
    "type" : "email",
});

</script>


<!-- Signup Form -->
<div class="container">
	<section id="formHolder">
	
		<div class="signup form">
			<form:form modelAttribute="member" 
			           action="/security/member/signup?_csrf=${_csrf.token}"
						id="signup_form" style="width:500px" class="mx-auto">
				<!-- 첨부파일 기능 때문에 액션에 암호화 코드 넣음 -->
				<h2 class="mt-5">회원가입</h2>
				<h6>반려견과의 일상을 소셜독과 함께해요!</h6>

				<div class="form-group mt-5" id="info__id">
					<form:label path="memberLoginId">아이디<span>*</span></form:label>
					<form:input path="memberLoginId" cssClass="form-control" />
					<form:errors path="memberLoginId" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="memberLoginPwd">비밀번호<span>*</span></form:label>
	            	<form:input path="memberLoginPwd" type="password" cssClass="form-control"/>				
					<form:errors path="memberLoginPwd" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="memberLoginPwd2">비밀번호 확인<span>*</span></form:label>
					<form:input path="memberLoginPwd2" type="password" cssClass="form-control" />
					<form:errors path="memberLoginPwd2" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="memberName">이름<span>*</span></form:label>
					<form:input path="memberName" cssClass="form-control" />
					<form:errors path="memberName" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="memberBirth">생년월일<span>*</span></form:label>
					<form:input type="date" path="memberBirth" cssClass="form-control" />
					<form:errors path="memberBirth" cssClass="error" />
				</div>

				<div class="form-group">
					<form:label path="memberEmail">이메일<span>*</span></form:label>
					<form:input type="email" path="memberEmail" cssClass="form-control" placeholder="email@example.com"/>
					<form:errors path="memberEmail" cssClass="error" />
				</div>
				
				<div class="form-group">
					<form:label path="memberAddress">주소<span>*</span></form:label>
					<form:input path="memberAddress" cssClass="form-control" />
					<form:errors path="memberAddress" cssClass="error" />
				</div>

				<div class="CTA text-center">
                    <input type="submit" value="Signup Now" class="signup_button">
                    <br>
                    <br>이미 회원이신가요?
                    <a href="security/login" class="switch">로그인 하러가기</a>
                </div>
                
			</form:form>
		</div>
	</section>
</div>

<%@ include file="../../layouts/footer.jsp"%>
