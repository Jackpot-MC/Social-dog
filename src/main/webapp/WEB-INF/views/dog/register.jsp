<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/resources/css/security/mypage.css"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>

<sec:authentication property="principal.username" var="username" />

<!-- Modal Body -->
<div class="modal-body">
	<div class="modal-body" style="padding: 50px;">
		<div class="container" style="width: 400px;">
			<section id="formHolder">
				<div class="">
					<form:form modelAttribute="dog"
						action="/dog/register?_csrf=${_csrf.token}"
						enctype="multipart/form-data" id="myForm">
						<h1 class="title" style="padding-bottom: 30px;">
							<i class="far fa-edit"></i>내 강아지를 등록해주세요!
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
							<form:label path="dogDescription">강아지 소개</form:label>
							<form:textarea path="dogDescription" cssClass="form-control"></form:textarea>
							<form:errors path="dogDescription" cssClass="error" />
						</div>

						<!--  Modal Footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-dark" id="editBtn">확인</button>
							<button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
						</div>

					</form:form>
				</div>
			</section>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#editBtn").click(function(e) {
			e.preventDefault();

			let dogName = $("#dogName").val();
			if (dogName == "") {
				alert("강아지 이름을 입력해주세요");
				$("#dogName").focus();
				return;
			}
			let dogKind = $("#dogKind").val();
			if (dogKind == "") {
				alert("견종을 입력해주세요.")
				$("#dogKind").focus();
				return;
			}
			if (confirm("등록 하시겠습니까?")) {
				$("#myForm").submit();
			}
		});
	});
</script>
