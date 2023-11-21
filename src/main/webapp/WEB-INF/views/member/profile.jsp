<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../layouts/member-header.jsp" %>

<%-- <sec:authentication property="principal.member" var="member"/> --%>

<div class="d-flex my-3">
	<form:form modelAttribute="member"
				style="width:500px" class="mx-auto"> <!-- 첨부파일 기능 때문에 액션에 암호화 코드 넣음 -->
	<h1 class="mt-4">마이페이지</h1>

			<div class="form-group mt-4">
				아이디: ${member.memberLoginId}
			</div>
			<div class="form-group mt-4">
				<form:label path="memberLoginPwd">새 비밀번호 입력</form:label>
				<form:input path="memberLoginPwd" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="memberLoginPwd2">새 비밀번호 확인</form:label>
				<form:input path="memberLoginPwd2" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="memberName">이름: ${member.memberName}</form:label>
				<form:input path="memberName" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="memberBirth">생년월일: ${member.memberBirth}</form:label>
				<form:input path="memberBirth" cssClass="form-control"/>
			</div>						
			<div class="form-group">
				<form:label path="memberEmail">이메일: ${member.memberEmail}</form:label>
				<form:input path="memberEmail" cssClass="form-control"/>
			</div>
			<div class="form-group">
				<form:label path="memberAddress">주소: ${member.memberAddress}</form:label>
				<form:input path="memberAddress" cssClass="form-control"/>
			</div>			
			
			<div class="text-center">
				<button type="submit" class="btn btn-primary">
					<i class="fas fa-undo"></i>확인</button>	
				<button type="reset" class="btn btn-primary">
					<i class="fas fa-undo"></i>취소</button>	
			</div>
	</form:form>
</div>




<%@ include file="../layouts/footer.jsp" %>