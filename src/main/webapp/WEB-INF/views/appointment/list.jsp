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

</head>
<body>
		<header>
		</header>

 <script type="text/javascript"> 
      // js코드
</script>
	
<div class="content container-fluid mx-auto" style="width:100%; height:700px; position:relative;overflow:auto;">
    <div class="row justify-content-center flex-column">
   	 <c:forEach var="appointment" items="${list}">
         <div class="card card-margin">
            <div class="card-header no-border">
                <img class="profile-pic-appointment-list ml-3" src="https://i.imgur.com/GJ5WWIB.jpeg">
                    <div class="d-flex flex-column">
                        <h3 class="mt-2 mb-0">${appointment.memberName}</h3>
                    </div>
            </div>
            <div class="card-body pt-2">
                <div class="widget-49">
                    <div class="widget-49-title-wrapper">
                        <div class="widget-49-meeting-info">
                            <span class="widget-49-pro-title">${appointment.appointmentName}</span>
                            <div class="widget-49-date-primary">
                            	<span class="widget-49-meeting-time"><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${appointment.regDate}"/></span>
                        	</div>
                        </div>
                    </div>
                    <ul class="widget-49-meeting-points">
                        <li class="widget-49-meeting-item"><span>장소: ${appointment.placeName}</span></li>
                        <li class="widget-49-meeting-item"><span>장소 종류: ${appointment.placeTypeName}</span></li>
                    </ul>
                    <div class="widget-49-meeting-action">
                        <a href="appointment/get?appointmentId=${appointment.appointmentId }" class="btn btn-sm btn-flash-border-primary">상세보기</a>
                        <a href="#" class="btn btn-sm btn-flash-border-primary" style="color:#0091EA;">참여하기</a>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</body>
</html>