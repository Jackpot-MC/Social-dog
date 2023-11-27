<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
      // js코드
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
          
		<div class="modal fade review-modal-center" id="review-comment-form" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog review-comment-dialog modal-dialog-centered" role="document">
		        <div class="modal-content review-comment-content">
		            <div class="text-right review-cross" data-dismiss="modal" aria-label="Close"> <i class="fa fa-times mr-2"></i> </div>
		            <div class="card-body review-comment-card-body text-center"> <i class="fa-solid fa-dog fa-2xl" style="color: #ffc107;"></i>
		                <div class="comment-box review-comment-box text-center mt-2">
		                <h4>리뷰를 작성해 주세요!</h4>
		                <div class="review-comment-rating">
			                <input type="radio" name="review-comment-rating" value="5" id="5"><label for="5">☆</label>
			                <input type="radio" name="review-comment-rating" value="4" id="4"><label for="4">☆</label>
			                <input type="radio" name="review-comment-rating" value="3" id="3"><label for="3">☆</label>
			                <input type="radio" name="review-comment-rating" value="2" id="2"><label for="2">☆</label>
			                <input type="radio" name="review-comment-rating" value="1" id="1"><label for="1">☆</label>
		                </div>
		                <div class="comment-area review-comment-area"> <textarea class="form-control review-comment-form-control" placeholder="즐거웠던 경험을 얘기해 주세요." rows="4"></textarea> </div>
		                    
		                    <div class="text-center mt-4"> <button class="btn review-comment-btn-send send px-5">작성완료<i class="fa fa-long-arrow-right ml-1"></i></button>
			            </div>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
		
		<c:forEach var="review" items="${list}">
            <div class="card">
                <div class="row d-flex">
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
                </div>
            </div>
           </c:forEach>
    </div>
</div>

</body>
</html>