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

 <div class="container-fluid mx-auto" style="width:100%; height:700px; position:relative;overflow:auto;">
    <div class="row justify-content-center flex-column">
            <div class="card-rating text-center">
                <div class="row justify-content-center d-flex flex-column text-center">
                            <h1 class="pt-1">${average}</h1>
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
            
		<c:forEach var="review" items="${list}">
            <div class="card">
                <div class="row d-flex">
                    <div class=""> <img class="profile-pic" src="https://i.imgur.com/GJ5WWIB.jpeg"> </div>
                    <div class="d-flex flex-column">
                        <h3 class="mt-2 mb-0">${review.memberName}</h3>
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
                    <p class="content">${review.reviewContent}</p>
                </div>
            </div>
           </c:forEach>
    </div>
</div>    

</body>
</html>