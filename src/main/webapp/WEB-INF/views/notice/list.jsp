<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../layouts/header.jsp"%>
<link rel="stylesheet" href="/resources/css/notice/list.css"
	type="text/css" />
	
<sec:authentication property="principal.username" var="username" />


<section class="notice">
	<div class="page-title">
		<div class="container">
			<h3>공지사항</h3>
		</div>
	</div>

	<%@ include file="../common/search_bar.jsp"%>

	<div id="board-list">
		<div class="container">
			<table class="board-table">
				<thead>
					<tr>
						<th class="th-num">No</th>
						<th class="th-title">제목</th>
						<th class="th-date">작성자</th>
						<th class="th-date">등록일</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="notice" items="${list}">
						<tr>
							<td style="width: 60px">${notice.noticeId}</td>
							<td class="th-title2"><a class="move" href="${cri.getLinkWithNoticeId('get', notice.noticeId)}">
									${notice.noticeTitle}</a></td>
							<td>${notice.memberLoginId}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${notice.regDate}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="text-right">
				<a href="register" class="btn btn-dark" style="height:30px;"> 
				<i class="far fa-edit"></i> 글쓰기
				</a>
			</div>

			<%@ include file="../common/pagination.jsp"%>
		</div>
	</div>

</section>
<%@ include file="../layouts/footer.jsp"%>