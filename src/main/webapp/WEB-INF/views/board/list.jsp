<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="../layouts/header.jsp"%>
<link rel="stylesheet" href="/resources/css/notice/list.css" type="text/css" />

<!-- 검색 search.js로 연결 -->
<script src="/resources/js/search.js">
	
</script>


<section class="notice">
	<div class="page-title">
		<div class="container">
			<h3>커뮤니티</h3>
		</div>
	</div>

	<%@ include file="../common/search_bar.jsp"%>

	<div id="board-list">
		<div class="container">
			<table class="board-table">
				<thead>
					<tr>
						<th style="width: 60px">No</th>
						<th>제목</th>
						<th style="width: 100px">작성자</th>
						<th style="width: 130px">등록일</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="board" items="${list}">
						<tr>
							<td style="width: 60px">${board.bno}</td>
							<td class="th-title2">
								<a style="font-weight:700;" class="move" href="${cri.getLinkWithBno('get', board.bno)}">
									${board.title}</a>						
									<br>
									<br><span style="color:#808080;">${board.content}</span>
							</td>
							<td>${board.loginId}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" /></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="text-right">
				<a href="register" class="btn btn-dark" style="height: 30px;"> <i
					class="far fa-edit"></i> 글쓰기
				</a>
			</div>

			<%@ include file="../common/pagination.jsp"%>
		</div>
	</div>
</section>


<%@include file="../layouts/footer.jsp"%>
