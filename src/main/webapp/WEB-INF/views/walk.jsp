<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="layouts/header.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="/resources/css/review/list.css"
	type="text/css" />
<link rel="stylesheet" href="/resources/css/appointment/list.css"
	type="text/css" />

</head>
<body>
	<header> </header>
	<script type="text/javascript">
		$(document).ready(function() {
			let mode = '${mode}';
			if (mode === 'review') {
				var href = 'appointment/walk_banner';
				$("#maindiv").load(href);
			} else {
				var href = 'appointment/walk_banner';
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
			
/*  			$('#maindiv').on('click', '.detail-btn', function(e) {
				var href = 'appointment/get?appointmentId=${appointment.appointmentId}';
				e.preventDefault();
				$("#maindiv").load(href);
			}); */
 			
			/* 	 $('#map').on('click', '.place_detail_btn', function(e) {
					 var href = place.place_url;
			 	    e.preventDefault();
			 	 	$("#maindiv").load(href);
				 }); */
		});
	</script>


	<section class="notice" style="padding-top:30px;">
		<div class="page-title">
			<div class="container">
				<div class="container-fluid">
					<div class="row mb-3">
						<div class="col-md-4 maindiv" id="maindiv">
							<%-- <%@ include file="review/list.jsp"%> --%>
						</div>

						<div class="col-md-8 themed-grid-col">
							<div class="map_wrap">
								<div id="map"
									style="width: 100%; height: 700px; position: relative; overflow: hidden;"></div>

								<!-- 키워드 검색 -->
								<div id="menu_wrap" class="bg_white">
									<div class="option">
										<div>
											<form onsubmit="searchPlaces(); return false;">
												키워드 : <input type="text" value="공원" id="keyword" size="15" />
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
									<li id="벤치" data-order="2"><span
										class="category_bg pharmacy"></span>벤치</li>
									<li id="배변봉투" data-order="3"><span class="category_bg oil"></span>배변봉투</li>
									<li id="동물병원" data-order="4"><span
										class="category_bg cafe"></span>동물병원</li>
								</ul>
							</div>

							<!-- 카카오맵 API 관련 코드 -->
							<script
								src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f2ef21ecaf963e1478e980c3e9d76aab&libraries=services,clusterer"></script>
							<script src="/resources/js/map.js"></script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="layouts/footer.jsp"%>