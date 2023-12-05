<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp" %>

<!-- summernote -->
<link rel="stylesheet" href="/resources/css/summernote/summernote-lite.min.css">
<script src="/resources/js/summernote/summernote-lite.min.js"></script>
<script src="/resources/js/summernote/lang/summernote-ko-KR.min.js"></script>

<style>
section.modify {
  padding: 80px 0;
  width: 900px;
  margin: 0 auto;
}

.page-title {
  margin-bottom: 60px;
}
.page-title h3 {
  font-size: 28px;
  color: #333333;
  font-weight: 400;
  text-align: center;
}

</style>

<script>
$(document).ready(function() {
	$('#content').summernote({
		height: 300, // 에디터높이
		focus: true, // 에디터로딩후포커스를맞출지여부
		lang: "ko-KR",// 한글설정
	});
});
// 기본글꼴설정
$('#content').summernote('fontName', 'Arial');
</script>

<section class="modify">
	<div class="page-title">
		<div class="container">
			<h3>약속 수정하기</h3>
		</div>
	</div>
	
<div class="panel panel-default">
	<div class="panel-body">
		<form:form modelAttribute="appointment" role="form" action="?_csrf=${_csrf.token}"
			enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${cri.pageNum}"/>
			<input type="hidden" name="amount" value="${cri.amount}"/>
			<input type="hidden" name="type" value="${cri.type}"/>
			<input type="hidden" name="keyword" value="${cri.keyword}"/>
			<form:hidden path="appointmentId"/>
			<form:hidden path="hostId"/>

			
			<div class="form-group">
				<form:label path="appointmentName">제목</form:label>
				<form:input path="appointmentName" cssClass="form-control"/> <!-- name은 VO에 있는 멤버변수 이름을 사용 -->	
				<form:errors path="appointmentName" cssClass="error"/>
			</div>

			<div class="form-group">
				<form:label path="content">약속 내용</form:label>
				<form:textarea path="content" cssClass="form-control"/> <!-- name은 VO에 있는 멤버변수 이름을 사용 -->	
				<form:errors path="content" cssClass="error"/>
			</div>
			
			<div class="form-group">
				<form:label path="appointmentDate">약속 날짜</form:label>
				<form:input type="datetime-local" path="appointmentDate" cssClass="form-control"/>
			</div>
			
			<button type="submit" class="btn btn-primary">
				<i class="fas fa-check"></i>확인</button>	
			<button type="reset" class="btn btn-primary">
				<i class="fas fa-undo"></i>취소</button>	
			<a href="${cri.getLinkWithAppointmentId('get', appointment.appointmentId)}" class="btn btn-primary get">
				<i class="fas fa-list"></i>돌아가기</a>
		</form:form>
	</div>
</div>
</section>
<%@ include file="../layouts/footer.jsp" %>