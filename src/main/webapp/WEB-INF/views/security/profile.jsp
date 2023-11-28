<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../security/profilemenu.jsp" %>

<link rel="stylesheet" href="/resources/css/mypage.css" type="text/css" />


<sec:authentication property="principal.username" var="username" />

<div class="container">
	<section id="formHolder">
		<div class="d-flex my-3">
			<form:form modelAttribute="member" action="/security/profile?_csrf=${_csrf.token}" 
						style="width:500px" class="mx-auto" id="myForm">
				<!-- 첨부파일 기능 때문에 액션에 암호화 코드 넣음 -->
				<h1 class="title">내 정보 수정</h1>

                <div class="form-group">
                    <form:label path="loginId">아이디</form:label>
                    <form:hidden path="loginId"  class="form-control" />
                    <div id="loginid" class="form-control">${loginId}</div>
                </div>
                <div class="form-group">
                    <form:label path="memberName">이름</form:label>
                    <form:input path="memberName" cssClass="form-control" />
                    <form:errors path="memberName" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:label path="memberBirth">생년월일</form:label>
                    <form:input type="date" path="memberBirth"  cssClass="form-control" />
                	<form:errors path="memberBirth" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:label path="memberEmail">이메일</form:label>
                    <form:input path="memberEmail" cssClass="form-control" />
                    <form:errors path="memberEmail" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:label path="memberAddress">주소</form:label>
                    <form:input path="memberAddress" cssClass="form-control" />
                	<form:errors path="memberAddress" cssClass="error"/>
                </div>

            	<div class="CTA text-center">
               		<button type="submit" class="update" id="editBtn">수정</button>    
                	<button type="reset" class="reset">취소</button>    
				</div>
			</form:form>
		</div>
	</section>
</div>


<%@ include file="../layouts/footer.jsp"%>


<script>
if("${result}" == "success"){
	alert("내 정보가 수정되었습니다.");
}

 $(document).ready(function(){
    $("#editBtn").click(function(e){
    	e.preventDefault();
    	
    	if(confirm("수정하시겠습니까?")){
    		$("#myForm").submit();
    	}
    }); 
});
</script>
