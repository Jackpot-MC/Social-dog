<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp" %>

<div>
	<form:form modelAttribute="member" enctype="multipart/form-data"
				action="/security/signup?_csrf=${_csrf.token}"
				style="width:500px" class="mx-auto"> <!-- 첨부파일 기능 때문에 액션에 암호화 코드 넣음 -->
	<h1 class="mt-4"><i class="fa-solid fa-user-plus"></i> 회원가입</h1>

			<div class="form-group mt-4">
				<form:label path="username">사용자 ID</form:label>
				<form:input path="username" cssClass="form-control"/> <!-- name은 VO에 있는 멤버변수 이름을 사용 -->	
				<form:errors path="username" cssClass="error"/>
			</div>
			<div class="form-group">
				<form:label path="password">비밀번호</form:label>
				<form:input path="password" cssClass="form-control"/>
				<form:errors path="password" cssClass="error"/>
			</div>
			<div class="form-group">
				<form:label path="password2">비밀번호 확인</form:label>
				<form:input path="password2" cssClass="form-control"/>
				<form:errors path="password2" cssClass="error"/>
			</div>
			<div class="form-group">
				<form:label path="email">email</form:label>
				<form:input path="email" cssClass="form-control"/>
				<form:errors path="email" cssClass="error"/>
			</div>
			
			<input type="file" name="avatar" /> <!-- DB에 넣지 않고 따로 처리 -->

			<div class="text-center">
				<button type="submit" class="btn btn-primary">
					<i class="fas fa-undo"></i>확인</button>	
				<button type="reset" class="btn btn-primary">
					<i class="fas fa-undo"></i>취소</button>	
			</div>
	</form:form>
</div>

<%@ include file="../layouts/footer.jsp" %>