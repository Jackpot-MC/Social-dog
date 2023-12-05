<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" href="/resources/css/profile.css" type="text/css" />

<sec:authentication property="principal.username" var="username" />

<!-- Modal Body -->
<div class="modal-body">
	<div class="modal-body" style="padding: 50px;">
		<div class="dogModify">

			<h4 style="padding-bottom: 30px; text-align: center;">
				<i class="far fa-edit"></i>강아지 정보 수정
			</h4>

			<form action="/dog/modify?_csrf=${_csrf.token}" name="modify" method="post">
				<input type="hidden" id="id" name="dogId" value="${dog.dogId}"/> 
				
				<div class="form-group row">
					<label class="">이름</label>
					<input type="text" class="form-control" name="dogName" value="${dog.dogName}">
				</div>

				<div class="form-group row">
                    <label class="">견종</label> 
                    <input type="text" class="form-control" name="dogKind" value="${dog.dogKind}">				
                </div>
				
				<div class="form-group row">
					<label>생일</label> 
					<input type="date" class="form-control" name="dogBirth" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${dog.dogBirth}"/>'>
				</div>
				
				<div class="form-group row">
                    <label class="">강아지 소개</label> 
                    <input type="text" class="form-control" name="dogDescription" value="${dog.dogDescription}">				
                </div>
				
				<div class="form-group row">
                    <label class="">강아지 프로필 사진</label> 
                    <input type="file" class="form-control" name="dogPhotoPath" value="${dog.dogPhotoPath}">				
                </div>
				
				<div style="float: right;">
                    <button class="btn btn-dark" type="submit">수정</button>					
                    <input type="button" class="btn btn-dark" value="취소" data-dismiss="modal">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Modal body 끝 -->
