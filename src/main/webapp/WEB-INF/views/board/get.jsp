<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../layouts/header.jsp"%>
<link rel="stylesheet" href="/resources/css/notice/list.css" type="text/css" />

<script	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="/resources/js/rest.js"></script>
<script src="/resources/js/comment.js"></script>


<script>
const COMMENT_URL = '/api/board/${param.bno}/comment/';

$(document).ready(async function(){
	
	$('.remove').click(function(){
		if(!confirm('정말 삭제할까요?')) return;
		document.forms.removeForm.submit();//form을 얻어서 submit() 호출
	});
	
	let bno = ${param.bno}; // 글번호
	let loginId = '${loginId}'; // 작성자(로그인 유저)
	
	loadComments(bno, loginId); // 댓글 목록 불러오기
	// 댓글 추가 버튼 처리
	$('.comment-add-btn').click(function(e){
		createComment(bno, loginId);
	});
	
	$('.comment-list').on('click', '.comment-update-show-btn', showUpdateComment);
	
	// 수정 확인 버튼 클릭
	$('.comment-list').on('click', '.comment-update-btn', function (e){
	const el = $(this).closest('.comment');
	updateComment(el, loginId);
	});
	
	// 수정 취소 버튼 클릭
	$('.comment-list').on('click', '.comment-update-cancel-btn', cancelCommentUpdate);

	// 삭제 버튼 클릭
	$('.comment-list').on('click', '.comment-delete-btn', deleteComment);
	
});

</script>


<section class="notice">
	<div class="page-title">
		<div class="container">
			<h3>${board.title}</h3>
		</div>
	</div>
	<div class="container" style="width: 900px;">
			<div style="text-align:right;">
				<i class="fas fa-clock"></i>
				<fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" />
				<div><i class="fas fa-user"></i>${board.loginId}</div>
			</div>

		<div class="text-right">
			<c:forEach var="file" items="${board.attaches}">
				<div class="attach-file-item">
					<a href="/board/download/${file.no}" class="file-link"> <i
						class="fa-solid fa-floppy-disk"></i> ${file.filename}
						(${file.formatSize})<br>
					</a>
				</div>
			</c:forEach>
		</div>
		<hr>
		<div>${board.content}</div>

		<!-- 댓글목록 -->
		<div class="my-5">
			<i class="fa-regular fa-comments"></i>댓글 목록
			<hr>
			<div class="comment-list"></div>
		</div>



		<!-- 새 댓글 작성 / 작성자가 아닌 경우에만 보여주기 -->
		<c:if test="${loginId != board.loginId}">
			<div class="bg-light p-2 rounded my-5">
				<div>${loginId == null ? '댓글을 작성하려면 먼저 로그인하세요.' : '댓글 작성'}</div>
				<div>
					<textarea class="form-control new-comment-content" rows="3"
						${loginId == null ? 'disabled' : ''}></textarea>
					<div class="text-right">
						<button class="btn btn-primary btn-sm my-2 comment-add-btn"
							${loginId == null ? 'disabled' : '' }>
							<i class="fa-regular fa-comment"></i> 확인
						</button>
					</div>
				</div>
			</div>
		</c:if>


		<div class="mt-4">
			<a href="${cri.getLink('list')}" class="btn btn-dark list"> <i
				class="fas fa-list"></i> 목록
			</a>

			<!-- loginId이 board글의 작성자와 일치하면 수정 및 삭제 버튼 보여주기 -->
			<c:if test="${loginId == board.loginId}">
				<a href="${cri.getLinkWithBno('modify', board.bno)}"
					class="btn btn-dark modify"> <i class="far fa-edit"></i> 수정
				</a>
				<a href="#" class="btn btn-danger remove"> <i
					class="fas fa-trash-alt"></i> 삭제
				</a>
			</c:if>




			<form action="remove" method="post" name="removeForm">
				<!-- csrf.token 사용하여 작성자만 삭제 가능하도록 함 -->
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="hidden" name="bno"
					value="${board.bno}" /> <input type="hidden" name="pageNum"
					value="${cri.pageNum}" /> <input type="hidden" name="amount"
					value="${cri.amount}" />
				<!-- 삭제 후 다시 목록으로 돌아왔을때 검색한 값이 유지되도록 함 -->
				<input type="hidden" name="type" value="${cri.type}" /> <input
					type="hidden" name="keyword" value="${cri.keyword}" />
			</form>

		</div>
	</div>
</section>
<%@include file="../layouts/footer.jsp"%>



