<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../layouts/header.jsp"%>
	
<link rel="stylesheet" href="/resources/css/notice/list.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/main.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/appointment/list.css" type="text/css" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=531949ea4d79a7a9c534d7d834673f7a"></script>
</head>

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

<div class="content container mx-auto" style="width:100%; height:700px; position:relative;overflow:auto;">
    <div class="row justify-content-center flex-column">
         <div class="card3 card-margin" style="padding:0;">
            <div class="card-header">
           	 <a href="javascript:history.back()">
            	<i class="fa-solid fa-arrow-left fa-lg ml-3" style="color: #212529;"></i></a>
                <img class="profile-pic-appointment-list ml-3" src="https://i.imgur.com/GJ5WWIB.jpeg">
                    <div class="d-flex flex-column">
                        <div class="pl-3 mb-0" style="font-size:17px;">  
						 ${appointment.appointmentName}
						 
						 	</div>
                    </div>
            </div>
            <div class="card-body">
                <div class="widget-49" >
                    <div class="widget-49-title-wrapper" >
                        <div class="widget-49-meeting-info" >
                            <div class="widget-49-meeting-points">
                            	<span class="widget-49-meeting-time">시간  | 
                            	<fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${appointment.appointmentDate}"/></span>
                        	</div>
                        	<div class="widget-49-meeting-points">
                        		<span class="widget-49-meeting-item">
                        		장소  | ${appointment.placeName}<br>
                        		주최자 <i class="fa-solid fa-paw" style="color: #ffc107;"></i> ${appointment.memberName}<br></span>
                        		<span class="widget-49-meeting-time">참가자<br></span>
                        		<c:forEach var="participant" items="${list}">
								<span>&nbsp; &nbsp; &nbsp;<i class="fa-solid fa-person" style="color: #ffc107;"></i> ${participant.memberName }(${participant.memberAddress })<br>
								</c:forEach></span>
                    		</div>
                    		<hr>
							<div class="widget-49-meeting-points">
							${appointment.content}
							</div>
							<hr>
							
							<p class="widget-49-meeting-item">${appointment.placeName}</p>
								<div id="map" style="width: 400px; height: 300px;"></div>
								<script>
										var container = document.getElementById('map');
										var options = {
											center: new kakao.maps.LatLng(${appointment.placeSpot}),
											level: 3
										};
								
										var map = new kakao.maps.Map(container, options);
								</script>
								<br>
                        </div>
                    </div>
                   <div class="widget-49-meeting-action">
                   		<a href="javascript:history.back()" class="btn btn-sm btn-flash-border-primary">
                   			<i class="fa-solid fa-arrow-left" style="color: #212529;"></i> 뒤로가기</a>
						<a href="${cri.getLinkWithAppointmentId('modify', appointment.appointmentId)}"
							class="btn modify"> <i class="fas fa-edit"></i> 수정
						</a> <a href="#" class="btn remove">
							<i class="fas fa-trash-alt"></i> 삭제</a>
                        <a href="/appointment/my_appointment" class="btn list">
                       		<i class="fas fa-list"></i> 약속보기</a>
						<a href="#" class="btn btn-primary attend">
							<i class="fa-solid fa-arrow-right-to-bracket"></i> 참여</a>
						<a href="#" class="btn btn-danger absent">
							<i class="fa-solid fa-right-from-bracket"></i> 불참</a>
                    </div>
                </div>
            </div>
          </div>
    </div>
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