<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="../layouts/header.jsp" %>

<div>
    <form:form modelAttribute="member" enctype="multipart/form-data"
               action="/security/signup?_csrf=${_csrf.token}"
               style="width:500px" class="mx-auto"> <!-- 첨부파일 기능 때문에 액션에 암호화 코드 넣음 -->
        <h1 class="mt-4"><i class="fa-solid fa-user-plus"></i> 회원가입</h1>

        <div class="form-group mt-4">
            <form:label path="memberLoginId">사용자 ID</form:label>
            <form:input path="memberLoginId" cssClass="form-control"/> <!-- name은 VO에 있는 멤버변수 이름을 사용 -->
            <form:errors path="memberLoginId" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:label path="memberLoginPwd">비밀번호</form:label>
            <form:input path="memberLoginPwd" cssClass="form-control"/>
            <form:errors path="memberLoginPwd" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:label path="memberLoginPwd2">비밀번호 확인</form:label>
            <form:input path="memberLoginPwd2" cssClass="form-control"/>
            <form:errors path="memberLoginPwd2" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:label path="memberName">이름</form:label>
            <form:input path="memberName" cssClass="form-control"/>
            <form:errors path="memberName" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:label path="memberBirth">생년월일</form:label>
            <form:input path="memberBirth" cssClass="form-control"/>
            <form:errors path="memberBirth" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:label path="memberEmail">email</form:label>
            <form:input path="memberEmail" cssClass="form-control"/>
            <form:errors path="memberEmail" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:label path="memberAddress">주소</form:label>
            <form:input path="memberAddress" cssClass="form-control"/>
            <form:errors path="memberAddress" cssClass="error"/>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-undo"></i>확인
            </button>
            <button type="reset" class="btn btn-primary">
                <i class="fas fa-undo"></i>취소
            </button>
        </div>
    </form:form>
</div>

<%@ include file="../layouts/footer.jsp" %>