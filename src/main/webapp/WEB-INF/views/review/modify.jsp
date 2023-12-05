<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="../layouts/header.jsp" %>
<%@ include file="../security/profilemenu.jsp" %>

<link rel="stylesheet" href="/resources/css/main.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />


<sec:authentication property="principal.username" var="username"/>

<script>

</script>

<!-- 리뷰 수정하기 모달 윈도우 -->
<!-- <div class="container">
    <section id="formHolder"> -->
            <div class="text-right review-cross" data-dismiss="modal" aria-label="Close"> <i class="fa fa-times mr-2"></i> </div>
            <div class="card-body review-comment-card-body text-center"> <i class="fa-solid fa-dog fa-2xl" style="color: #ffc107;"></i>
                <div class="comment-box review-comment-box text-center mt-2">
                <h4>리뷰를 작성해 주세요!</h4>
                <form:form modelAttribute="review" action="/dog/modify?_csrf=${_csrf.token}"
					id="review_modify_form">
				<form:hidden path="memberLoginId" value="${member.loginId}"/>
				<form:hidden path="placeId" value="14"/>
				
	                    <div class="review-comment-rating form-group">
				        <form:radiobutton name="rating" path="rating" value="5" id="5"/><label for="5">☆</label>
				        <form:radiobutton name="rating" path="rating" value="4" id="4"/><label for="4">☆</label>
				        <form:radiobutton name="rating" path="rating" value="3" id="3"/><label for="3">☆</label>
				        <form:radiobutton name="rating" path="rating" value="2" id="2"/><label for="2">☆</label>
				        <form:radiobutton name="rating" path="rating" value="1" id="1"/><label for="1">☆</label>
				    </div>
						                
                <div class="form-group">
					<form:label path="reviewTitle">제목</form:label>
	            	<form:input path="reviewTitle" cssClass="form-control"/>
					<form:errors path="reviewTitle" cssClass="error" />
				</div>
                
                <div class="comment-area review-comment-area form-group">
                내용 <form:textarea path="reviewContent" rows="4"/>
                </div>
                    
                    <div class="text-center mt-4"> <button class="btn review-comment-btn-send px-5">작성완료<i class="fa fa-long-arrow-right ml-1"></i></button>
	            </div>
	            </form:form>
	            </div>
	      </div>
<!-- 	</section>
</div> -->

<%-- <h1 class="page-header mt-4"><i class="far fa-edit mt-4"></i>글 수정하기</h1>
<div class="panel panel-default">
	<div class="panel-body">
		<form:form modelAttribute="notice" role="form" action="?_csrf=${_csrf.token}"
			enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${cri.pageNum}"/>
			<input type="hidden" name="amount" value="${cri.amount}"/>
			<input type="hidden" name="type" value="${cri.type}"/>
			<input type="hidden" name="keyword" value="${cri.keyword}"/>
			<form:hidden path="noticeId"/>
			<form:hidden path="adminId"/>
			
			<div class="form-group">
				<form:label path="noticeTitle">제목</form:label>
				<form:input path="noticeTitle" cssClass="form-control"/> <!-- name은 VO에 있는 멤버변수 이름을 사용 -->	
				<form:errors path="noticeTitle" cssClass="error"/>
			</div>
			
			<div class="form-group">
				<form:label path="noticeContent">내용</form:label> <!-- id는 summernote 용 --> 
				<form:textarea path="noticeContent" cssClass="form-control"></form:textarea>
				<form:errors path="noticeContent" cssClass="error"/>
			</div>
			
			<button type="submit" class="btn btn-primary">
				<i class="fas fa-undo"></i>확인</button>	
			<button type="reset" class="btn btn-primary">
				<i class="fas fa-undo"></i>취소</button>	
			<a href="${cri.getLinkWithNoticeId('get', notice.noticeId)}" class="btn btn-dark btn-primary get">
				<i class="fas fa-list"></i>돌아가기</a>
		</form:form>
	</div>
</div> --%>

<%@ include file="../layouts/footer.jsp" %>