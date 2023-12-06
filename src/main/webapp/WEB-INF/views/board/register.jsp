<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@include file="../layouts/header.jsp"%>
<link rel="stylesheet" href="/resources/css/notice/list.css" type="text/css" />

<link rel="stylesheet" href="/resources/css/summernote/summernote-lite.min.css">
<script src="/resources/js/summernote/summernote-lite.min.js"></script>
<script src="/resources/js/summernote/lang/summernote-ko-KR.min.js"></script>

<script>
$(document).ready(function() {
	$('#content').summernote({
		height : 300, // 에디터 높이
		focus : true, // 에디터 로딩후 포커스를 맞출지 여부
		lang : "ko-KR", // 한글 설정
	});
	
	//파일 여러개 업로드시 해당 업로드파일 이름 출력 
	const attaches = $('[name="files"]');
	const attachList = $('#attach-list');

	
	attaches.change(function (e) {
		let fileList = '';
		for(let file of this.files) {
			let fileStr = `
			<div>
				<i class="fa-solid fa-file"></i>
				\${file.name}(\${file.size.formatBytes()}) 		
			</div>`;
			fileList += fileStr;
		}
		attachList.html(fileList);
	});
	//\${file.name}(\${file.size})  -> 파일명, 파일크기
	//역슬래시가 안붙으면 el로 간주함, 지금은 자바스크립트 템플릿 문자열 처리이므로 역슬래시 붙여줘야함
	
});
</script>

<section class="notice">
	<div class="page-title">
		<div class="container">
			<h3>글 작성</h3>
		</div>
	</div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
				<form:form modelAttribute="board" role="form"
					action="?_csrf=${_csrf.token}" enctype="multipart/form-data">
					<form:hidden path="loginId" value="${loginId}" />

					<div class="form-group">
						<form:label path="title">제목</form:label>
						<form:input path="title" cssClass="form-control" />
						<form:errors path="title" cssClass="error" />
					</div>
					
<!-- 					<div class="form-group">
						<label for="attaches">첨부파일</label>
						<div id="attach-list" class="my-1"></div>
						파일 목록 출력 영역
						<input type="file" class="form-control" multiple name="files" />
					</div> -->

					<div class="form-group">
						<form:label path="content">내용</form:label>
						<form:textarea path="content" class="form-control" rows="10"></form:textarea>
						<form:errors path="content" cssClass="error" />
					</div>

					<div class="form-group">
						<label>공유할 강아지 사진을 업로드해주세요!</label>
						<input type="file" name="avatar" class="form-control" />
					</div>
                    
					<button type="submit" class="btn btn-dark">
						<i class="fas fa-check"></i>확인
					</button>
					<button type="reset" class="btn btn-dark">
						<i class="fas fa-undo"></i>취소
					</button>
					<a href="javascript:history.back()" class="btn btn-dark"> <i
						class="fas fa-list"></i>목록
					</a>
				</form:form>
			</div>
		</div>
	</div>
</section>
<%@include file="../layouts/footer.jsp"%>
