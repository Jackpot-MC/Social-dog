<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="/resources/css/search_bar.css">
<%@include file="../layouts/header.jsp"%>
<link rel="stylesheet" href="/resources/css/notice/list.css" type="text/css" />

<!-- 검색 search.js로 연결 -->
<script src="/resources/js/search.js">
	
</script>
    
<style>

section.board-list {
	padding: 80px 0;
}
.page-title {
	margin-bottom: 60px;
}
.page-title h3 {
	font-size: 28px;
	color: #333333;
	font-weight: 400;
	text-align: center;
}
a{
    color: #000000;
    text-decoration: none;
    background-color: transparent;
}
.card-body {
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1.25rem;
    padding-top: 0;
}
.card-text {
    text-align: right!important;
    color: #6d615b;
}
section.board-list {
  padding: 80px 0;
}
.page-title {
  margin-bottom: 60px;
}
.page-title h3 {
  font-size: 28px;
  color: #333333;
  font-weight: 400;
  text-align: center;
}
.card-img-top {
    height: 200px;
    object-fit: cover;
}
.card-text {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.row {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    margin-right: -80px;
    margin-left: -80px;
}

</style>

<section class="board-list">
    <div class="page-title">
        <div class="container">
            <h3 style="padding-bottom:60px;">커뮤니티</h3>
    
		    <%@ include file="../common/search_bar.jsp"%>
		    
		    <div style="padding: 10px;"></div>
		    <div class="card-container" style="width: 900px; margin: auto;">
		        <form enctype="multipart/form-data" >
		        <div class="row">
		            <c:forEach var="board" items="${list}">
		                <div class="col-sm-6 col-md-4 mb-3">
		                    <div class="card" style="width: 100%">
		                        <a href="${cri.getLinkWithBno('get', board.bno)}">
		                            <img class="card-img-top" src="/board/boardPhoto/lg/${board.bno}"
		                            	onerror="this.src='https://images.template.net/76516/Free-Dog-Paw-Vectors-1.jpg'">
		                        </a>
		                        
		                        <div class="card-body">
		                            <div class="card-title" style="font-weight:700;">
		                                <a href="${cri.getLinkWithBno('get', board.bno)}">
		                                     <hr>${board.title}
		                                </a>
		                            </div>
		                             <c:choose>
										<c:when test="${fn:length(board.summary) gt 43}">
										 	<c:out value="${fn:substring(board.summary, 0, 42)}"/>...
										</c:when>
										<c:otherwise>
										 <c:out value="${board.summary}"></c:out>
										</c:otherwise>
									</c:choose>
		                            
		                            <div class="card-text" style="padding-top:8px;">
		                            ${board.loginId}
		                            <br><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>
		                            </div>
		                        </div>
		                        
		                    </div>
		                </div>
		            </c:forEach>
		            
		        </div></form>
		        <hr>
		        <div class="text-right">
		            <a href="register" class="btn btn-dark" style="height: 30px;"> <i
		                class="far fa-edit"></i> 글쓰기
		            </a>
		        </div>
		    </div>
		</div>
	</div>
		<%@ include file="../common/pagination.jsp"%>
</section>




<%@include file="../layouts/footer.jsp"%>
            


<%@include file="../layouts/footer.jsp"%>
