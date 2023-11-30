<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
.page-item:active{
background: #494a4b;
}
.page-link{
color: #494a4b;
}
.i{
color: #494a4b;
}
.page-item.active .page-link {
    z-index: 3;
    color: #fff;
    background-color: #494a4b;
    border-color: #494a4b;
}
</style>

<ul class="pagination justify-content-center">
	<c:if test="${pageMaker.cri.pageNum > 1}">
		<li class="page-item" >
			<a class="page-link" href="${pageMaker.cri.getLink(1)}"> <!-- 1은 페이지 번호 -->
				<i class="fa-solid fa-backward"></i>
			</a>
		</li>
	</c:if>
	
	<c:if test="${pageMaker.prev}">
		<li class="page-item">
			<a class="page-link" href="${cri.getLink(pageMaker.startPage-1)}">
				<i class="fa-solid fa-caret-left"></i>
			</a>
		</li>
	</c:if>
	
	<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
		<li class="page-item ${ pageMaker.cri.pageNum == num ? 'active' : '' }">
			<a class="page-link" href="${cri.getLink(num)}">${num}</a>
		</li>	
	</c:forEach>
	
	<c:if test="${pageMaker.next}">
		<li class="page-item">
			<a class="page-link" href="${cri.getLink(pageMaker.endPage+1)}">
				<i class="fa-solid fa-caret-right" ></i>
			</a>
		</li>
	</c:if>
	
	<c:if test="${pageMaker.cri.pageNum < pageMaker.totalPage}">
		<li class="page-item">
			<a class="page-link" href="${cri.getLink(pageMaker.totalPage)}">
				<i class="fa-solid fa-forward"></i>
			</a>
		</li>
	</c:if>
</ul>