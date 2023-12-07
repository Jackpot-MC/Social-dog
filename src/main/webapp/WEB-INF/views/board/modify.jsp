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
<script src="/resources/js/rest.js"></script>

<script>
$(document).ready(function() {
	$('#content').summernote({
		height : 300, // 에디터 높이
		focus : true, // 에디터 로딩후 포커스를 맞출지 여부
		lang : "ko-KR", // 한글 설정
	});
	
 	// 첨부파일
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
	
	// 첨부파일 삭제
	$('.remove-attachment').click(async function(e) {
		if(!confirm('파일을 삭제할까요?')) return;
		let no = $(this).data("no");
		
		let url = '/board/remove/attach/' + no +'?_csrf=${_csrf.token}';
		let result = await rest_delete(url);
		if(result == 'OK') {
			$(this).parent().remove();
		} else{
			alert('파일 삭제 실패');
		} 
	});*/



	// 돌아가기 버튼
	$('.get').click(function(){
		document.forms.getForm.submit();
	});
	 */
});

</script>

<section class="notice">
	<div class="page-title">
		<div class="container">
			<h3>글 수정</h3>
		</div>
	</div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
				<form:form modelAttribute="board" role="form"
					action="?_csrf=${_csrf.token}" enctype="multipart/form-data">

					<input type="hidden" name="pageNum" value="${cri.pageNum}" />
					<input type="hidden" name="amount" value="${cri.amount}" />
					<input type="hidden" name="type" value="${cri.type}" />
					<input type="hidden" name="keyword" value="${cri.keyword}" />

					<!-- bno와 loginId는 수정 안한다는 설정 -->
					<form:hidden path="bno" />
					<form:hidden path="loginId" />

					<!-- 제목 -->
					<div class="form-group">
						<form:label path="title">Title</form:label>
						<form:input path="title" cssClass="form-control" />
						<form:errors path="title" cssClass="error" />
					</div>

<%--  					<!-- 첨부파일 -->
					<div class="my-3">
						<label for="attaches">첨부파일</label>
						<c:forEach var="file" items="${board.attaches}">
							<div>
								<i class="fa-solid fa-floppy-disk"></i> ${file.filename}
								(${file.formatSize})
								<button type="button" data-no="${file.no}"
									class="btn btn-danger btn-sm py-0 px-1 remove-attachment">
									<i class="fa-solid fa-times"></i>
								</button>
							</div>
						</c:forEach>
					</div>

					<div class="form-group">
						<label for="attaches">추가 첨부파일</label>
						<div id="attach-list" class="my-1"></div>
						<input type="file" class="form-control" multiple name="files" />
					</div>  --%>

					<!-- 내용 -->
					<div class="form-group">
						<form:label path="content">Content</form:label>
						<form:textarea path="content" class="form-control" rows="10"></form:textarea>
						<form:errors path="content" cssClass="error" />
					</div>
					
					<div class="form-group">
					<label for="boardPhoto">공유할 강아지 사진을 업로드해주세요!</label>
                    <input type="file" class="form-control" name="boardPhoto">
                    </div> 
                    
					<!-- 확인 취소 돌아가기 버튼 -->
					<button type="submit" class="btn btn-dark">
						<i class="fas fa-check"></i> 확인
					</button>
					<button type="reset" class="btn btn-dark">
						<i class="fas fa-undo"></i> 취소
					</button>
					<a href="${cri.getLinkWithBno('get', board.bno)}"
						class="btn btn-dark get"> <i class="fas fa-file-alt"></i> 돌아가기
					</a>

				</form:form>
			</div>
		</div>
	</div>
</section>

<%@include file="../layouts/footer.jsp"%>


