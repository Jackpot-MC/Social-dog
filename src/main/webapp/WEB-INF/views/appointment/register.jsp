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

<link rel="stylesheet" href="/resources/css/appointment/register.css" type="text/css" />

<style>
section.register {
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
	
/* 	$('#btn').click(function(){

		    var form = document.getElementById("excelForm");

		    form.action = "ExcelList.do";

		    form.method = "POST";

		    form.submit();

		}); */
	
	if(window.localStorage.getItem('placeKakaoName')){
		 const placeKakaoName = window.localStorage.getItem('placeKakaoName')
		 document.getElementById("appointment-placeName").innerHTML="장소명: " + placeKakaoName;
		}
	
	if(window.localStorage.getItem('placeId')){
		 const placeId = window.localStorage.getItem('placeId')
		 document.getElementById("appointment-placeId").innerHTML="장소ID: " + placeId;
		}
	
	if(window.localStorage.getItem('placeAddress')){
		 const placeAddress = window.localStorage.getItem('placeAddress')
		 document.getElementById("appointment-placeAddress").innerHTML="지번 주소: " + placeAddress;
		}
	
	if(window.localStorage.getItem('placeRoadAddress')){
		 const placeRoadAddress = window.localStorage.getItem('placeRoadAddress')
		 document.getElementById("appointment-placeRoadAddress").innerHTML="도로명 주소: " + placeRoadAddress;
		}
	
	if(window.localStorage.getItem('placeCategory')){
		 const placeCategory = window.localStorage.getItem('placeCategory')
		 document.getElementById("appointment-placeCategory").innerHTML="카테고리 번호: " + placeCategory;
		}
	
	if(window.localStorage.getItem('placeX')){
		 const placeX = window.localStorage.getItem('placeX')
		 document.getElementById("appointment-placeX").innerHTML="위도: " + placeX;
		}
	
	if(window.localStorage.getItem('placeY')){
		 const placeY = window.localStorage.getItem('placeY')
		 document.getElementById("appointment-placeY").innerHTML="경도: " + placeY;
		}
});
// 기본글꼴설정
$('#content').summernote('fontName', 'Arial');
</script>

<%-- <sec:authentication property="principal.memberId" var="memberId"/> --%>

<section class="register">
	<div class="page-title">
		<div class="container">
			<h3>약속 만들기</h3>
		</div>
	</div>
	
<div class="panel panel-default">
	<div class="panel-body">
		<form:form modelAttribute="appointment" role="form"
			action="?_csrf=${_csrf.token}"
			enctype="multipart/form-data">
			<form:hidden path="hostId" value="${hostId}"/>
				
			<div class="form-group">
				<form:label path="appointmentName">제목</form:label>
				<form:input path="appointmentName" cssClass="form-control"/> <!-- name은 VO에 있는 멤버변수 이름을 사용 -->	
				<form:errors path="appointmentName" cssClass="error"/>
			</div>
			
			<p id="appointment-placeName-post"></p>
			
			<span style="font-weight: bold;">약속 장소 정보</span>
			<div class="card" id="appointment-placeName"></div>
			<div class="card" id="appointment-placeId"></div>
			<div class="card" id="appointment-placeAddress"></div>
			<div class="card" id="appointment-placeRoadAddress"></div>
			<div class="card" id="appointment-placeCategory"></div>
			<div class="card" id="appointment-placeX"></div>
			<div class="card" id="appointment-placeY"></div><br>
		
			<div class="form-group">
				<form:label path="content">약속 내용</form:label>
				<form:textarea path="content" cssClass="form-control"/> 
				<form:errors path="content" cssClass="error"/>
			</div>
			
			<div class="form-group">
				<form:label path="appointmentDate">약속 날짜</form:label>
				<form:input type="datetime-local" path="appointmentDate" cssClass="form-control"/>
			</div>
			
			<button type="submit" class="btn btn-dark">
				<i class="fas fa-undo"></i>확인</button>	
			<button type="reset" class="btn btn-dark">
				<i class="fas fa-undo"></i>취소</button>	
			<a href="javascript:history.back()" class="btn btn-dark"> <!--history.back() : 캐시된 것을 가져옴-->
				<i class="fas fa-list"></i>목록</a>
		</form:form>

<%-- 		<form:form modelAttribute="place" role="form"
			action="?_csrf=${_csrf.token}"
			enctype="multipart/form-data">
<!-- 			<div id="place-placeName"></div>
			<div id="place-placeKakaoId"></div>
			<div id="place-placeTypeId"></div> -->
			
			<form:hidden path="placeName" value="${placeName}"/>
			<form:hidden path="placeKakaoId" value="${placeId}"/>
			<form:hidden path="placeTypeId" value="${placeCategory}"/>
			
			<span style="font-weight: bold;">약속 장소 정보</span>
			<div class="card" id="appointment-placeName"></div>
			<div class="card" id="appointment-placeId"></div>
			<div class="card" id="appointment-placeAddress"></div>
			<div class="card" id="appointment-placeRoadAddress"></div>
			<div class="card" id="appointment-placeCategory"></div>
			<div class="card" id="appointment-placeX"></div>
			<div class="card" id="appointment-placeY"></div><br>
			
			<button type="submit" class="btn btn-dark">
				<i class="fas fa-undo"></i>확인</button>	
			<button type="reset" class="btn btn-dark">
				<i class="fas fa-undo"></i>취소</button>	
			<a href="javascript:history.back()" class="btn btn-dark"> <!--history.back() : 캐시된 것을 가져옴-->
				<i class="fas fa-list"></i>목록</a>
		</form:form> --%>
		
		</div>
		
	</div>
</section>

<%@ include file="../layouts/footer.jsp" %>