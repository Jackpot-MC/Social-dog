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

<div class="container-fluid">
	<div class="card" style="background-color: #f7edd5">
		<a href="${cri.getLinkWithNoticeId('notice/get', noticeLatest.noticeId)}">
			<h3 class="blue-text mt-1 text-center">
				[
				<fmt:formatDate pattern="yyyy-MM-dd" value="${noticeLatest.regDate}" />
				}] ${noticeLatest.noticeTitle}
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
					<div class="option">
						<div>
							<form onsubmit="searchPlaces(); return false;">
								키워드 :
								<input type="text" value="공원" id="keyword" size="15" />
								<button type="submit">검색하기</button>
							</form>
						</div>
					</div>
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

			<div class="modal modal-fullscreen-xl" id="modal-fullscreen-xl" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Modal title</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<iframe id="embeddedTerm" src="https://place.map.kakao.com/8128910" style="width: 100%; height: 100%"></iframe>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f2ef21ecaf963e1478e980c3e9d76aab&libraries=services,clusterer"></script>
			<!-- 카카오맵 API .JS 코드 -->
			<script src="/resources/js/map.js"></script>
		</div>
	</div>
</div>

<%@include file="layouts/footer.jsp"%>
