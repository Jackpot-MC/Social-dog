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

<%-- <sec:authentication property="principal.memberId" var="memberId"/> --%>

<h1 class="page-header mt-4"><i class="far fa-edit"></i>글 작성하기</h1>
<div class="panel panel-default">
	<div class="panel-body">
		<form:form modelAttribute="appointment" role="form"
			action="?_csrf=${_csrf.token}"
			enctype="multipart/form-data">
			<form:hidden path="hostId" value="${hostId }"/>
				
			<div class="form-group">
				<form:label path="appointmentName">제목</form:label>
				<form:input path="appointmentName" cssClass="form-control"/> <!-- name은 VO에 있는 멤버변수 이름을 사용 -->	
				<form:errors path="appointmentName" cssClass="error"/>
			</div>

 			<div class="form-group">
				<form:label path="placeId">약속 장소</form:label> <!-- id는 summernote 용 --> 
				<form:textarea path="placeId" cssClass="form-control"></form:textarea>
				<form:errors path="placeId" cssClass="error"/>
			</div> 
			
			<div class="form-group">
				<form:label path="appointmentDate">약속 날짜</form:label>
				<form:input type="datetime-local" path="appointmentDate" cssClass="form-control"/>
			</div>
			
			<button type="submit" class="btn btn-primary">
				<i class="fas fa-undo"></i>확인</button>	
			<button type="reset" class="btn btn-primary">
				<i class="fas fa-undo"></i>취소</button>	
			<a href="javascript:history.back()" class="btn btn-primary"> <!--history.back() : 캐시된 것을 가져옴-->
				<i class="fas fa-list"></i>목록</a>
		</form:form>
		</div>
		
	</div>


<%@ include file="../layouts/footer.jsp" %>