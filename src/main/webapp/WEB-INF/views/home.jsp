<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="layouts/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/appointment/list.css" type="text/css" />

<script type="text/javascript">
	$(document).ready(function() {
		let mode = '${mode}';
		if (mode === 'review') {
			var href = 'review/list .content';
			$("#maindiv").load(href);
		} else {
			var href = 'appointment/list .content';
			$("#maindiv").load(href);
		}

		$('#map').on('click', '.review_btn', function(e) {
			var href = 'review/list .content';
			e.preventDefault();
			$("#maindiv").load(href);
		});

		$('#map').on('click', '.appointment_btn', function(e) {
			var href = 'appointment/list .content';
			e.preventDefault();
			$("#maindiv").load(href);
		});

		/* 	 $('#map').on('click', '.place_detail_btn', function(e) {
				 var href = place.place_url;
		 	    e.preventDefault();
		 	 	$("#maindiv").load(href);
			 }); */
	});
</script>

<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="/resources/images/banner01.png" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
        <h2 class="our_text">새로 나온 반려견 간식</h2>
        <h1 class="gifts_text">건강한 오리 육포</h1>
        <p class="standerd_text">엄선된 오리 고기로만 만들었습니다.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="/resources/images/banner02.png" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
		<h2 class="our_text">소중한 반려견 사진 촬영은</h2>
        <h1 class="gifts_text">앙꼬네 애견 스튜디오</h1>
        <p class="standerd_text">더 빠른 시간을 살아가는 반려견들을 위한 촬영 스튜디오입니다.</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="/resources/images/banner03.png" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
		<h2 class="our_text">놀러오세요</h2>
        <h1 class="gifts_text">댕댕이 놀이터</h1>
        <p class="standerd_text">가끔 마음껏 달리고 싶은 대형견들을 위한 놀이터</p>
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-target="#carouselExampleCaptions" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-target="#carouselExampleCaptions" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </button>
</div>

<div class="container-fluid">
	<div class="card" style="background-color: #f7edd5">
		<a href="${cri.getLinkWithNoticeId('notice/get', noticeLatest.noticeId)}">
			<h3 class="blue-text mt-1 text-center">
				[
				<fmt:formatDate pattern="yyyy-MM-dd" value="${noticeLatest.regDate}" />
				] ${noticeLatest.noticeTitle}
			</h3>
		</a>
	</div>
</div>

<div class="container-fluid">
	<div class="row mb-3">
		<div class="col-md-4 maindiv" id="maindiv">
			<%-- <%@ include file="review/list.jsp"%> --%>
		</div>

		<div class="col-md-8 themed-grid-col">
			<div class="map_wrap">
				<div id="map" style="width: 100%; height: 700px; position: relative; overflow: hidden;"></div>

				<!-- 키워드 검색 -->
				<div id="menu_wrap" class="bg_white">
<!-- 					<div class="option">
						<div>
							<form onsubmit="searchPlaces(); return false;">
								키워드 :
								<input type="text" value="공원" id="keyword" size="15" />
								<button type="submit">검색하기</button>
							</form>
						</div>
					</div> -->
					<hr />
					<ul id="placesList"></ul>
					<div id="pagination"></div>
				</div>

				<!-- 카테고리 -->
				<ul id="category">
					<li id="공원" data-order="0"><span class="category_bg bank"></span>공원</li>
					<li id="화장실" data-order="1"><span class="category_bg mart"></span>화장실</li>
					<li id="벤치" data-order="2"><span class="category_bg pharmacy"></span>벤치</li>
					<li id="배변봉투" data-order="3"><span class="category_bg oil"></span>배변봉투</li>
					<li id="동물병원" data-order="4"><span class="category_bg cafe"></span>동물병원</li>
				</ul>
			</div>

			<!-- 카카오맵 API 관련 코드 -->
			<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f2ef21ecaf963e1478e980c3e9d76aab&libraries=services,clusterer"></script>
			<script src="/resources/js/map.js"></script>
		</div>
	</div>
</div>

<%@include file="layouts/footer.jsp"%>
