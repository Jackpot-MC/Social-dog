<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="/resources/css/notice/list.css" type="text/css" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=531949ea4d79a7a9c534d7d834673f7a"></script>



<%@ include file="../layouts/header.jsp"%>

<script>
	$(document).ready(async function(){		// fetch()를 사용하는 함수에 async 작성. 비동기 함수임을 선언
		$('.remove').click(function(){ //post라서 .list, .modify와 달리 별도 처리 필요
				if(!confirm('정말 삭제할까요?')) return;
				document.forms.removeForm.submit();
			});
		$('.attend').click(function(){ 
			if(!confirm('참가하시겠습니까?')) return;
			document.forms.attendForm.submit();
		});
		$('.absent').click(function(){ 
			if(!confirm('참가 취소하시겠습니까?')) return;
			document.forms.absentForm.submit();
		});
	
	if (${checkAttendance} > 0){
		$('.attend').hide();
	}	else{
		$('.absent').hide();
	}
		
		let appointmentId = ${param.appointmentId}; 	// 글번호
		let memberId = '${memberId}';	// 작성자(로그인 유저)  
	});
</script>


<section class="notice">
	<div class="page-title">
		<div class="container">
			<h3><i class="far fa-file-alt"></i>${appointment.appointmentName}</h3>
		</div>
	</div>
	<div class="container" style="width: 900px;">
			<div style="text-align:right;">
				<i class="fas fa-clock"></i>
				<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${appointment.appointmentDate}" />
				<div><i class="fas fa-user"></i>${appointment.memberName}</div>
			</div>

<a href="#" class="btn btn-primary attend"> <i class="fa-solid fa-arrow-right-to-bracket"></i>참여
</a>

<a href="#" class="btn btn-danger absent"> <i class="fa-solid fa-right-from-bracket"></i> 불참
</a>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>참가자</th>
			<th>참가자 주소</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="participant" items="${list}">
			<tr>
				<td style="width: 200px">${participant.memberName }</td>
				<td style="width: 150px">${participant.memberAddress }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<hr>

<div>
${appointment.content}
</div>
<hr>

<h5>${appointment.placeName}</h5>
<div id="map" style="width: 500px; height: 400px;"></div>

<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(${appointment.placeSpot}),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
</script>


<br>

<div class="mt-4">
	<a href="/appointment/my_appointment" class="btn btn-dark list"> <i
		class="fas fa-list"></i>목록
	</a> <a
		href="${cri.getLinkWithAppointmentId('modify', appointment.appointmentId)}"
		class="btn btn-dark modify"> <i class="fas fa-edit"></i>수정
	</a> <a href="#" class="btn btn-dark remove"> <i
		class="fas fa-trash-alt"></i>삭제
	</a>
</div>

<form action="remove" method="post" name="removeForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="appointmentId"
		value="${appointment.appointmentId}" /> <input type="hidden"
		name="pageNum" value="${cri.pageNum}" /> <input type="hidden"
		name="amount" value="${cri.amount}" /> <input type="hidden"
		name="type" value="${cri.type}" /> <input type="hidden"
		name="keyword" value="${cri.keyword}" />
</form>

<form action="attend" method="post" name="attendForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" 
		name="appointmentId"
		value="${appointment.appointmentId}" /> 
	<input type="hidden" 
		name="memberId" 
		value="${memberId}" />
</form>

<form action="absent" method="post" name="absentForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" 
		name="appointmentId"
		value="${appointment.appointmentId}" /> 
	<input type="hidden" 
		name="memberId" 
		value="${memberId}" />
</form>

<%@ include file="../layouts/footer.jsp"%>