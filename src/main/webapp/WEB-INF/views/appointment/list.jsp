<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file = "../layouts/header.jsp" %>

<h1>공지사항</h1>

<%@ include file="../common/search_bar.jsp" %>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>No</th>
			<th>약속 이름</th>
			<th>약속 장소</th>
			<th>장소 종류</th>
			<th>주최자</th>
			<th>약속 날짜</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="appointment" items="${list}">
			<tr>
				<td style="width:60px">${appointment.appointmentId}</td>
				<td><a class="move" href="${cri.getLinkWithAppointmentId('get', appointment.appointmentId)}">
					${appointment.appointmentName}</a>
				</td>
				<td style="width:200px">${appointment.placeSpot }</td>
				<td style="width:150px">${appointment.placeTypeName }</td>
				<td style="width:100px">${appointment.memberLoginId}</td>
				<td style="width:130px">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${appointment.regDate}"/>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="text-right">
	<a href="register" class="btn btn-primary">
	<i class="far fa-edit"></i>
	글쓰기
	</a>
</div>

<%@ include file="../common/pagination.jsp" %>

<%@ include file = "../layouts/footer.jsp" %>