<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Document</title>
	<link
		rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet"/>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />

	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
		<header>
		</header>

 <script type="text/javascript">  
/*  $(window).on('load', function () {
		load('#card-review', '1');

		$("#review-load-btn").on("click", function () {
			load('#card-review', '1', '#review-load-btn');
		})
	});
	function load(id, cnt, btn) {
		var girls_list = id + " .card-review:not(.active)";
		var girls_length = $(girls_list).length;
		var girls_total_cnt;
		if (cnt < girls_length) {
			girls_total_cnt = cnt;
		} else {
			girls_total_cnt = girls_length;
			$(btn).hide();
		}
		$(girls_list + ":lt(" + girls_total_cnt + ")").addClass("active");
	} */

	$(document).ready(async function(){		// fetch()를 사용하는 함수에 async 작성. 비동기 함수임을 선언
		$('.remove').click(function(){ //post라서 .list, .modify와 달리 별도 처리 필요
				if(!confirm('정말 삭제할까요?')) return;
				
				//form을 얻어서 submit() 호출
				//console.log(document.forms);
				document.forms.removeForm.submit();
			});
	
</script>

 <div class="content container-fluid mx-auto" style="width:100%; height:700px; position:relative;overflow:auto;">    
    <div class="row justify-content-center flex-column">
            <div class="card text-center">
                <div class="row justify-content-center d-flex flex-column text-center">
                            <h1>${average}</h1>
                        <div>                            
                        	<c:forEach begin="1" end="${average}">
                            <span class="fa fa-star star-active ml-1"></span>
                    		</c:forEach>
                    		<c:forEach begin="1" end="${5-average}">
                            <span class="fa fa-star star-inactive ml-1"></span>
                    		</c:forEach>
                    	</div>
                </div>
            </div>
            
        <button type="button" class="btn review-comment-btn" data-toggle="modal" data-target="#review-comment-form"> 리뷰 작성하기 </button>
          
		<div class="modal fade" id="review-comment-form" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog modal-dialog-centered" role="document">
		        <div class="modal-content review-comment-content">
		            <div class="text-right review-cross" data-dismiss="modal" aria-label="Close"> <i class="fa fa-times mr-2"></i> </div>
		            <div class="card-body review-comment-card-body text-center"> <i class="fa-solid fa-dog fa-2xl" style="color: #ffc107;"></i>
		                <div class="comment-box review-comment-box text-center mt-2">
		                <h4>리뷰를 작성해 주세요!</h4>
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
		                    
		                    <div class="text-center mt-4"> <button class="btn review-comment-btn-send px-5">작성완료<i class="fa fa-long-arrow-right ml-1"></i></button>
			            </div>
			            </form:form>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
		
<!-- 		<div id="card-review" class="main">
		<div class="lists"> -->
		<c:forEach var="review" items="${list}" begin="0" end="4">
            <div class="card">
                <div class="row d-flex lists__item card-review">
                    <div class=""> <img class="profile-pic" src="https://i.imgur.com/GJ5WWIB.jpeg"> </div>
                    <div class="d-flex flex-column">
                        <span class="mt-2 mb-0" style="color: #36260d;font-size: 22px;font-weight: 500;">${review.memberName}</span>
                        <div>
                            <p class="text-left"><span class="text-muted mr-2">${review.rating}</span>
                            <c:forEach begin="1" end="${review.rating}">
                            <span class="fa fa-star star-active-in-review"></span>
                    		</c:forEach>
                    		<c:forEach begin="1" end="${5-review.rating}">
                            <span class="fa fa-star star-inactive"></span>
                    		</c:forEach>
                    		</p>
                    	</div>
                    </div>
                    <div class="ml-auto">
                        <p class="text-muted pt-5 pt-sm-3"><fmt:formatDate pattern="MM-dd" value="${review.regDate}"/></p>
                    </div>
                </div>
                <div class="row text-left flex-column">
                    <h5 class="blue-text mt-1">"${review.reviewTitle}"</h5>
                    <p class="content-text">${review.reviewContent}</p>
                
                <div class="ml-auto">
				<c:if test="${review.memberLoginId eq member.loginId}">
	                   <a href="#" class="btn review-modify" data-toggle="modal" data-target="#review-modify-form">
	                   <i class="fa-solid fa-pen-to-square" style="color: #cfd8dc;"></i></a>
	                   <a href="#" class="btn remove">
	                   <i class="fa-solid fa-trash" style="color: #cfd8dc;"></i></a>
				</c:if>
				</div>
                </div>
            </div>
           </c:forEach>
<!--            </div>
           </div> -->

           <button type="button" class="btn review-load-btn" id="review-load-btn"> 더보기 </button>
           
           <form action="remove" method="post" name="removeForm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="reviewId" value="${review.reviewId}" />
				<input type="hidden" name="pageNum" value="${cri.pageNum}" />
				<input type="hidden" name="amount" value="${cri.amount}" />
				<input type="hidden" name="type" value="${cri.type}" />
				<input type="hidden" name="keyword" value="${cri.keyword}" />
			</form>
    </div>
</div>

</body>
</html>