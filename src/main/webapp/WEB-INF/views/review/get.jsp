<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"> // moment 날짜 포맷팅
</script>

<%@ include file = "../layouts/member-header.jsp" %>

<script>
	$(document).ready(async function(){		// fetch()를 사용하는 함수에 async 작성. 비동기 함수임을 선언
		$('.remove').click(function(){ //post라서 .list, .modify와 달리 별도 처리 필요
				if(!confirm('정말 삭제할까요?')) return;
				document.forms.removeForm.submit();
			});
	
	});
</script>

<h1 class="page-header mt-4"><i class="far fa-file-alt"></i>${review.reviewTitle}</h1>
<div class="d-flex justify-content-between">
	<div><i class="fas fa-user"></i>${review.memberId}</div>
	<div>
		<i class="fas fa-clock"></i>
		<fmt:formatDate pattern="yyyy-MM-dd" value="${review.regDate}" />
	</div>
</div>

<hr>

<div>
	${review.reviewContent}
</div>

<br>

<div class="mt-4">
	<a href="${cri.getLink('list')}" class="btn btn-primary list">
		<i class="fas fa-list"></i>목록</a>
	<a href="${cri.getLinkWithReviewId('modify', review.reviewId)}" class="btn btn-primary modify">
		<i class="fas fa-edit"></i>수정</a>
	<a href="#" class="btn btn-danger remove">
		<i class="fas fa-trash-alt"></i>삭제</a>
</div>

<form action="remove" method="post" name="removeForm">
<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  -->
	<input type="hidden" name="noticeId" value="${review.reviewId}" />
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
</form>

<%@ include file="../layouts/footer.jsp" %>