<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" href="/resources/css/notice/list.css"
	type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"> // moment 날짜 포맷팅
</script>

<%@ include file="../layouts/header.jsp"%>

<script>
	$(document).ready(async function(){		// fetch()를 사용하는 함수에 async 작성. 비동기 함수임을 선언
		$('.remove').click(function(){ //post라서 .list, .modify와 달리 별도 처리 필요
				if(!confirm('정말 삭제할까요?')) return;
				document.forms.removeForm.submit();
			});
	
		let noticeId = ${param.noticeId}; 	// 글번호
		let memberId = '${memberId}';	// 작성자(로그인 유저)
	});
</script>

<%-- <sec:authentication property="principal.username" var="username" /> --%>

<section class="notice">
	<div class="page-title">
		<div class="container">
			<h3>${notice.noticeTitle}</h3>
		</div>
	</div>
	<div class="container" style="width: 900px;">
		<div class="d-flex justify-content-between">
			<div>
				<i class="fas fa-user"></i>${notice.memberLoginId}</div>
			<div>
				<i class="fas fa-clock"></i>
				<fmt:formatDate pattern="yyyy-MM-dd" value="${notice.regDate}" />
			</div>
		</div>

		<hr>

		<div>${notice.noticeContent}</div>

		<br>

		<div class="mt-4">
			<a href="${cri.getLink('list')}" class="btn btn-dark list"> <i
				class="fas fa-list"></i>목록
			</a> <a href="${cri.getLinkWithNoticeId('modify', notice.noticeId)}"
				class="btn btn-dark modify"> <i class="fas fa-edit"></i>수정
			</a> <a href="#" class="btn btn-danger remove"> <i
				class="fas fa-trash-alt"></i>삭제
			</a>
		</div>

		<form action="remove" method="post" name="removeForm">
			<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  -->
			<input type="hidden" name="noticeId" value="${notice.noticeId}" /> <input
				type="hidden" name="pageNum" value="${cri.pageNum}" /> <input
				type="hidden" name="amount" value="${cri.amount}" /> <input
				type="hidden" name="type" value="${cri.type}" /> <input
				type="hidden" name="keyword" value="${cri.keyword}" />
		</form>
	</div>
</section>
<%@ include file="../layouts/footer.jsp"%>