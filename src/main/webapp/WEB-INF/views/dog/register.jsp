<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp" %>

<!-- summernote -->
<link rel="stylesheet" href="/resources/css/summernote/summernote-lite.min.css">
<script src="/resources/js/summernote/summernote-lite.min.js"></script>
<script src="/resources/js/summernote/lang/summernote-ko-KR.min.js"></script>

<script>
    $(document).ready(function() {
        $('#content').summernote({
            height: 300, // 에디터높이
            focus: true, // 에디터로딩후포커스를맞출지여부
            lang: "ko-KR",// 한글설정
        });
    });
    // 기본글꼴설정
    $('#content').summernote('fontName', 'Arial');
</script>

<%-- <sec:authentication property="principal.memberId" var="memberId"/> --%>

<h1 class="page-header mt-4"><i class="far fa-edit"></i>강아지 등록하기</h1>
<div class="panel panel-default">
    <div class="panel-body">
        <form:form modelAttribute="dog" role="form"
                   action="?_csrf=${_csrf.token}"
                   enctype="multipart/form-data">
            <!-- menu.jsp에서 sec: 으로 ${username} 설정을 해 두어서 쓸 수 있음.
            글 작성하기는 로그인해야만 보이는 화면이고, 그런 화면에는 menu가 반드시 있기 때문에 확정적으로 사용 가능 -->
            <form:hidden path="memberId" value="${memberId}"/>

            <input type="file" name="photo" />

            <div class="form-group">
                <form:label path="dogName">강아지 이름</form:label>
                <form:input path="dogName" cssClass="form-control"/> <!-- name은 VO에 있는 멤버변수 이름을 사용 -->
                <form:errors path="dogName" cssClass="error"/>
            </div>

            <div class="form-group">
                <form:label path="dogKind">강아지 종류</form:label>
                <form:input path="dogKind" cssClass="form-control"/> <!-- kind은 VO에 있는 멤버변수 이름을 사용 -->
                <form:errors path="dogKind" cssClass="error"/>
            </div>

            <div class="form-group">
                <form:label path="dogBirth">강아지 생일</form:label>
                <form:input path="dogBirth" cssClass="form-control"/> <!-- birth은 VO에 있는 멤버변수 이름을 사용 -->
                <form:errors path="dogBirth" cssClass="error"/>
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
            <a href="javascript:history.back()" class="btn btn-primary"> <!--history.back() : 캐시된 것을 가져옴-->
                <i class="fas fa-list"></i>목록</a>
        </form:form>
    </div>
</div>

<%@ include file="../layouts/footer.jsp" %>