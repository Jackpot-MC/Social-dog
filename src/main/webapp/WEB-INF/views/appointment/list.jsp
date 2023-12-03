	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Document</title>
	<link
		rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet"/>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />
	<link rel="stylesheet" href="/resources/css/appointment/list.css" type="text/css" />

	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/js/list.js"></script>

</head>
<body>
		<header>
		</header>

 <script type="text/javascript"> 
	$(document).ready(async function(){		// fetch()를 사용하는 함수에 async 작성. 비동기 함수임을 선언
		$('.absent-appointment').click(function(e){ //post라서 .list, .modify와 달리 별도 처리 필요
				e.preventDefault();	
				if(!confirm('정말 참가 취소할까요?')) return;
				
				//form을 얻어서 submit() 호출
				//console.log(document.forms);
				$(this).document.forms.absentAppointment.submit();
			});
	
		$('.attend-appointment').click(function(e) {
			e.preventDefault();	
			if(!confirm('정말 참가할까요?')) return;
			
			//form을 얻어서 submit() 호출
			//console.log(document.forms);
			$(this).document.forms.attendAppointment.submit();
		});
		
		function deleteRow(ths){
		    var ths = $(ths);
		    
		    ths.document.forms.removeFormReview.submit();
		};
	
</script>
	
	        
	
<div class="content container-fluid mx-auto" style="width:100%; height:700px; position:relative;overflow:auto;">
    <div class="row justify-content-center flex-column">

    <!-- 약속 작성하기 모달 윈도우 버튼 -->
        <button type="button" class="btn register-appointment-btn" style="width:100%; padding:10px; margin:auto;"
                data-toggle="modal" onclick="location.href='appointment/register'"> 약속 작성하기 </button>

   	 <c:forEach var="appointment" items="${list}">
         <div class="card3 card-margin" style="padding:0;">
            <div class="card-header">
                <img class="profile-pic-appointment-list ml-3" src="https://i.imgur.com/GJ5WWIB.jpeg">
                    <div class="d-flex flex-column">
                        <div class="pl-3 mb-0" style="font-size:17px;">  
						 ${appointment.appointmentName}
						<c:choose>
							<c:when test="${appointment.hostId == memberId}">
<!-- 								<i class="fa-solid fa-circle-check fa-lg" style="color: #8f4218;display: inline-block;"></i>
 -->							</c:when>
							<c:otherwise>
<!-- 								<i class="fa-regular fa-circle-check fa-lg" style="color: #8f4218;display: inline-block;"></i>
 -->							</c:otherwise>
						</c:choose>
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
                        		주최자 <i class="fa-solid fa-paw" style="color: #ffc107;"></i> ${appointment.memberName}</span>
                    		</div>
                        </div>
                    </div>

                    <div class="widget-49-meeting-action">
                        <a href="appointment/get?appointmentId=${appointment.appointmentId}" 
                        	class="btn btn-sm btn-flash-border-primary">상세보기</a>
                        <c:choose>
							<c:when test="${appointment.hostId == memberId}">
								<a href="#" class="btn btn-sm btn-flash-border-primary" style="color:#0091EA;">취소하기</a>
							</c:when>
							<c:otherwise>
								<a href="#" class="btn btn-sm btn-flash-border-primary" style="color:#0091EA;">참여하기</a>
							</c:otherwise>
						</c:choose>
                    </div>
                </div>
            </div>
          </div>
        </c:forEach>
    </div>
</div>
</body>
</html>