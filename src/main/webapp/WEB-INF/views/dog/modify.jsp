<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="../layouts/header.jsp" %>
<%@ include file="../security/profilemenu.jsp" %>

<link rel="stylesheet" href="/resources/css/profile.css" type="text/css"/>

<sec:authentication property="principal.username" var="username"/>

<div class="container">
    <section id="formHolder">
        <div class="d-flex my-3">
            <form:form modelAttribute="dog" action="/dog/modify?_csrf=${_csrf.token}"
                       style="width:500px" class="mx-auto" enctype="multipart/form-data">
                <!-- 첨부파일 기능 때문에 액션에 암호화 코드 넣음 -->
                <h1 class="title">강아지 정보 수정</h1>

                <input type="file" name="photo" />

                <div class="form-group">
                    <form:label path="dogId">아이디</form:label>
                    <form:input path="dogId" class="form-control"/>
                    <div id="dogId" class="form-control">${dogId}</div>
                </div>
                <div class="form-group">
                    <form:label path="dogName">강아지 이름</form:label>
                    <form:input path="dogName" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="dogBirth">강아지 생일</form:label>
                    <form:input type="date" path="dogBirth" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="dogFavorite">좋아하는 것</form:label>
                    <form:input path="dogFavorite" cssClass="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="dogDescription">강아지 설명</form:label>
                    <form:textarea path="dogDescription" cssClass="form-control"/>
                </div>

                <div class="CTA text-center">
                    <button type="submit" class="update">수정</button>
                    <button type="reset" class="reset">취소</button>
                </div>
            </form:form>
        </div>
    </section>
</div>


<%@ include file="../layouts/footer.jsp" %>