<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<sec:authentication property="principal.username" var="username" />

<!-- Modal Body -->
<div class="modal-body">
	<div class="modal-body" style="padding: 50px;">
		<div class="dogRegister">

			<h4 style="padding-bottom: 30px; text-align: center;">
				<i class="far fa-edit"></i>내 강아지를 등록해주세요!
			</h4>

			<form:form modelAttribute="dog" action="/dog/register?_csrf=${_csrf.token}" id="myForm">

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
				
				<div class="form-group row">
					<label class="">강아지 프로필 사진</label>
					<input type="file" class="form-control" name="dogPhotoPath" value="${dog.dogPhotoPath}" >
				</div>	

				<!--  Modal Footer -->
				<div class="modal-footer">
					<button type="submit" class="btn btn-dark" id="editBtn">등록</button>
					<button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
				</div>
				

			</form:form>
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
