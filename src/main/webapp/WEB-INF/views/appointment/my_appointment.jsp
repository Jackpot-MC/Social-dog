<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@include file="../layouts/header.jsp"%>
	<link rel="stylesheet" href="/resources/css/notice/list.css" type="text/css" />
	<link rel="stylesheet" href="/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />
	<link rel="stylesheet" href="/resources/css/appointment/list.css" type="text/css" />

<!-- 검색 search.js로 연결 -->
<script src="/resources/js/search.js">

</script>


<section class="notice">
	<div class="page-title">
		<div class="container">
			<h4 style="text-align: center;">나의 약속</h4>
		</div>
	</div>

	<%-- <%@ include file="../common/search_bar.jsp"%> --%>

	<div id="board-list">
		<div class="container">
		<c:forEach var="appointment" items="${my_appointment_list}">
         <div class="card3 card-margin" style="padding:0;">
            <div class="card-header">
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
                        		주최자 <i class="fa-solid fa-paw" style="color: #ffc107;"></i> ${appointment.memberName}</span><br>
                    			<span style="color:#808080; font-size:14px;">
									<c:choose>
								        <c:when test="${fn:length(appointment.summary) gt 148}">
									        <c:out value="${fn:substring(appointment.summary, 0, 147)}"/>...
								        </c:when>
								        <c:otherwise>
									        <c:out value="${appointment.summary}"></c:out>
							        </c:otherwise>
									</c:choose>
								</span>
                    		</div>
                        </div>
                    </div>

                    <div class="widget-49-meeting-action">
                        <a href="/appointment/my_appointment_get?appointmentId=${appointment.appointmentId}" 
                        	class="btn btn-sm btn-flash-border-primary detail-btn"
                        	 data-target="get">상세보기</a>
                    </div>
                </div>
            </div>
          </div>
        </c:forEach>
    </div>
</div>
<br>

<%@ include file="../common/pagination.jsp"%>

</section>

<%@include file="../layouts/footer.jsp"%>
