<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file = "../layouts/header.jsp" %>

<h1>공지사항</h1>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="review" items="${list}">
			<tr>
				<td style="width:60px">${review.reviewId}</td>
				<td><a class="move" href="${cri.getLinkWithReviewId('get', review.reviewId)}">
					${review.reviewTitle}</a>
				</td>
				<td style="width:100px">${review.memberId}</td>
				<td style="width:130px">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${review.regDate}"/>
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

<%@ include file = "../layouts/footer.jsp" %>