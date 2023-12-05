<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../layouts/header.jsp"%>
	
<link rel="stylesheet" href="/resources/css/notice/list.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/main.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/appointment/list.css" type="text/css" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=531949ea4d79a7a9c534d7d834673f7a"></script>
</head>

<%-- <sec:authentication property="principal.adminId" var="adminId"/> --%>

<!-- 리뷰 수정하기 모달 윈도우 -->
<div class="content container mx-auto" style="width:100%; height:700px; position:relative;overflow:auto;">  
    <div class="row justify-content-center flex-column">
         <div class="card3 card-margin" style="padding:0;">
            <div class="text-left review-cross">
				<a href="/walk?mode=review">
            	<i class="fa-solid fa-arrow-left fa-lg ml-3" style="color: #FFC107;"></i></a>
			</div>
            <div class="card-body review-comment-card-body text-center"> <i class="fa-solid fa-dog fa-2xl" style="color: #ffc107;"></i>
                <div class="comment-box review-comment-box text-center mt-2">
                <h5>리뷰를 수정해 주세요!</h5>
                <form:form modelAttribute="review"
					id="review_comment_form">
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
                    
                    <div class="text-center mt-4">
						<button class="btn submit review-comment-btn-send px-5">작성완료
						<i class="fa fa-long-arrow-right ml-1"></i></button>
	            </div>
	            </form:form>
	            </div>
	        </div>
	    </div>
	</div>
</div>

<%@ include file="../layouts/footer.jsp"%>