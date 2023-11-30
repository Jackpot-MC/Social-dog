<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/resources/css/security/mypage.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>

<sec:authentication property="principal.username" var="username" />

<!-- Modal Body -->
<div class="modal-body">
	<div class="modal-body" style="padding: 50px;">
		<div class="container" style="width: 400px;">
			<section id="formHolder">
				<div class="">
					<form:form modelAttribute="dog" action="/dog/register?_csrf=${_csrf.token}"
						enctype="multipart/form-data">
						<h1 class="title" style="padding-bottom: 30px;">
							<i class="far fa-edit"></i>내 강아지 등록하기
						</h1>

						<form:hidden path="memberId" value="${dog.memberId}" />

						<div class="form-group">
							<form:label path="dogName">이름</form:label>
							<form:input path="dogName" cssClass="form-control" />
							<form:errors path="dogName" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="dogKind">견종</form:label>
							<form:input path="dogKind" cssClass="form-control" />
							<form:errors path="dogKind" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="dogBirth">생일</form:label>
							<form:input type="date" path="dogBirth" cssClass="form-control" />
							<form:errors path="dogBirth" cssClass="error" />
						</div>

						<div class="form-group">
							<form:label path="dogDescription">관심사</form:label>
							<form:input path="dogDescription" cssClass="form-control"></form:input>
							<form:errors path="dogDescription" cssClass="error" />
						</div>
							
						<div class="form-group">
							<form:label path="dogDescription">강아지 소개</form:label>
							<form:textarea path="dogDescription" cssClass="form-control"></form:textarea>
							<form:errors path="dogDescription" cssClass="error" />
						</div>
						
						<c:if test="${param.error == 'true'}">
                    <div class="error">사용자 ID 또는 비밀번호가 일치하지 않습니다.</div>
                </c:if>

                <c:if test="${param.error == 'login_required'}">
                    <div class="error mt-4">로그인이 필요한 서비스입니다.</div>
                </c:if>
						<!--  Modal Footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-dark">확인</button>
							<button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
						</div>
						
					</form:form>
				</div>
			</section>
		</div>
	</div>
</div>

<script>
$('#modal').modal('hide'); //닫기 

/* 외부영역 클릭시 팝업 닫기 */
$(document).mouseup(function (e){
	if($(".layer_pop").has(e.target).length === 0){
		$(".layer_pop").hide();
	}
});

</script>
