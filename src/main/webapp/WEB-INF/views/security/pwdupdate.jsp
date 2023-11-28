<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../security/profilemenu.jsp" %>

<link rel="stylesheet" href="/resources/css/security/mypage.css" type="text/css" />


<sec:authentication property="principal.username" var="username" />

<div class="container">
	<section id="formHolder">
		<div class="d-flex my-3">
			<form:form modelAttribute="member" action="/security/pwdupdate?_csrf=${_csrf.token}" 
						style="width:500px" class="mx-auto" id="myForm">
				<!-- 첨부파일 기능 때문에 액션에 암호화 코드 넣음 -->
				<h1 class="title">비밀번호 변경</h1>

                <div class="form-group">
                    <form:label path="loginPwd">새 비밀번호 입력</form:label>
                    <form:password path="loginPwd" cssClass="form-control" id="loginPwd"/>
                    <form:errors path="loginPwd" cssClass="error" />
                </div>
                <div class="form-group">
                    <form:label path="loginPwd2">새 비밀번호 확인</form:label>
                    <form:password path="loginPwd2" cssClass="form-control"/>
                    <form:errors path="loginPwd2" cssClass="error" />
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
console.log("result", "${result}");
if("${result}" == "success"){
	alert("비밀번호가 변경되었습니다.");
}

 $(document).ready(function(){
    $("#editBtn").click(function(e){
    	e.preventDefault();
    	
/*      	let myPass = $("#loginPwd").val();
    	if( myPass == "" ) {
    		alert("패스워드를 입력하세요");
    		return;
    	}  */
    	
        if(confirm("수정하시겠습니까?")){
            $("#myForm").submit();
        }
    }); 
});

</script>
