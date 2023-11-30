<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="layouts/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/appointment/list.css" type="text/css" />

<script type="text/javascript">
   $(document).ready(function () {
  	 let mode = '${mode}';
  	 if(mode === 'review') {
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
					<li id="치킨" data-order="0"><span class="category_bg bank"></span>치킨</li>
					<li id="공원" data-order="1"><span class="category_bg mart"></span>공원</li>
					<li id="동물병원" data-order="2"><span class="category_bg pharmacy"></span>동물병원</li>
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
			<script>
        //마커를 담을 배열
        var markers = [];

        //마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
        var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
          contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
          markers = [], // 마커를 담을 배열입니다
          currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
          };

        // 지도를 생성합니다
        var map = new kakao.maps.Map(mapContainer, mapOption);

        // 장소 검색 객체를 생성합니다
        var ps = new kakao.maps.services.Places(map);

        // ------------------ 장소 검색 목록으로 표출 ---------------------

        // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

        // 키워드로 장소를 검색합니다
        searchPlaces();

        // 키워드 검색을 요청하는 함수1 - 내 위치 기준 일부만
        async function searchPlaces() {
          var keyword = document.getElementById('keyword').value;
          const currentCoordinate = await getCurrentCoordinate();
          var options = {
            location: currentCoordinate,
            radius: 10000,
            sort: kakao.maps.services.SortBy.DISTANCE,
          };
          if (!keyword.replace(/^\s+|\s+$/g, '')) {
            alert('키워드를 입력해주세요!');
            return false;
          }
          // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
          ps.keywordSearch(keyword, placesSearchCB, options);
        }

        // 키워드 검색을 요청하는 함수2 - 전국
        /* function searchPlaces() {
    var keyword = document.getElementById('keyword').value;
    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }
    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch(keyword, placesSearchCB);
} */

        // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
        function placesSearchCB(data, status, pagination) {
          if (status === kakao.maps.services.Status.OK) {
            // 정상적으로 검색이 완료됐으면
            // 검색 목록과 마커를 표출합니다
            displayPlaces(data);

            // 페이지 번호를 표출합니다
            displayPagination(pagination);
          } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert('검색 결과가 존재하지 않습니다.');
            return;
          } else if (status === kakao.maps.services.Status.ERROR) {
            alert('검색 결과 중 오류가 발생했습니다.');
            return;
          }
        }

        // 검색 결과 목록과 마커를 표출하는 함수입니다
        function displayPlaces(places) {
          var listEl = document.getElementById('placesList'),
            menuEl = document.getElementById('menu_wrap'),
            fragment = document.createDocumentFragment(),
            bounds = new kakao.maps.LatLngBounds(),
            listStr = '';

          // 검색 결과 목록에 추가된 항목들을 제거합니다
          removeAllChildNods(listEl);

          // 지도에 표시되고 있는 마커를 제거합니다
          removeMarker();

          for (var i = 0; i < places.length; i++) {
            // 마커를 생성하고 지도에 표시합니다
            var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
              marker = addMarker(placePosition, i),
              itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            bounds.extend(placePosition);

            // 마커와 검색결과 항목에 mouseover 했을때
            // 해당 장소에 인포윈도우에 장소명을 표시합니다
            // mouseout 했을 때는 인포윈도우를 닫습니다
            (function (marker, title) {
              kakao.maps.event.addListener(marker, 'mouseover', function () {
                displayInfowindow(marker, title);
              });

              kakao.maps.event.addListener(marker, 'mouseout', function () {
                infowindow.close();
              });

              itemEl.onmouseover = function () {
                displayInfowindow(marker, title);
              };

              itemEl.onmouseout = function () {
                infowindow.close();
              };
            })(marker, places[i].place_name);

            fragment.appendChild(itemEl);
          }

          // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
          listEl.appendChild(fragment);
          menuEl.scrollTop = 0;

          // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
          map.setBounds(bounds);
        }

        // 검색결과 항목을 Element로 반환하는 함수입니다
        function getListItem(index, places) {
          var el = document.createElement('li'),
            itemStr =
              '<span class="markerbg marker_' +
              (index + 1) +
              '"></span>' +
              '<div class="info">' +
              '   <h5>' +
              places.place_name +
              '</h5>';

          if (places.road_address_name) {
            itemStr +=
              '    <span>' +
              places.road_address_name +
              '</span>' +
              '   <span class="jibun gray">' +
              places.address_name +
              '</span>';
          } else {
            itemStr += '    <span>' + places.address_name + '</span>';
          }

          itemStr +=
            '  <span class="tel">' + places.phone + '</span>' + '</div>';

          el.innerHTML = itemStr;
          el.className = 'item';

          return el;
        }

        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        function addMarker(position, idx, title) {
          var imageSrc =
              'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
            imgOptions = {
              spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
              spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
              offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(
              imageSrc,
              imageSize,
              imgOptions
            ),
            marker = new kakao.maps.Marker({
              position: position, // 마커의 위치
              image: markerImage,
            });

          marker.setMap(map); // 지도 위에 마커를 표출합니다
          markers.push(marker); // 배열에 생성된 마커를 추가합니다

          return marker;
        }

        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        function removeMarker() {
          for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
          }
          markers = [];
        }

        // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
        function displayPagination(pagination) {
          var paginationEl = document.getElementById('pagination'),
            fragment = document.createDocumentFragment(),
            i;

          // 기존에 추가된 페이지번호를 삭제합니다
          while (paginationEl.hasChildNodes()) {
            paginationEl.removeChild(paginationEl.lastChild);
          }

          for (i = 1; i <= pagination.last; i++) {
            var el = document.createElement('a');
            el.href = '#';
            el.innerHTML = i;

            if (i === pagination.current) {
              el.className = 'on';
            } else {
              el.onclick = (function (i) {
                return function () {
                  pagination.gotoPage(i);
                };
              })(i);
            }

            fragment.appendChild(el);
          }
          paginationEl.appendChild(fragment);
        }

        // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
        // 인포윈도우에 장소명을 표시합니다
        function displayInfowindow(marker, title) {
          var content =
            '<div style="padding:5px;z-index:1;">' + title + '</div>';

          infowindow.setContent(content);
          infowindow.open(map, marker);
        }

        // 검색결과 목록의 자식 Element를 제거하는 함수입니다
        function removeAllChildNods(el) {
          while (el.hasChildNodes()) {
            el.removeChild(el.lastChild);
          }
        }

        // ------------------ 카테고리별 장소 검색-------------------------
        // 지도에 idle 이벤트를 등록합니다
        kakao.maps.event.addListener(map, 'idle', searchPlaces1);

        // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
        contentNode.className = 'placeinfo_wrap';

        // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
        // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
        addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
        addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

        // 커스텀 오버레이 컨텐츠를 설정합니다
        placeOverlay.setContent(contentNode);

        // 각 카테고리에 클릭 이벤트를 등록합니다
        addCategoryClickEvent();

        // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
        function addEventHandle(target, type, callback) {
          if (target.addEventListener) {
            target.addEventListener(type, callback);
          } else {
            target.attachEvent('on' + type, callback);
          }
        }

        // 카테고리 검색을 요청하는 함수입니다
        async function searchPlaces1() {
          if (!currCategory) {
            //이 부분을 바꿔주면 무한루프에서 벗어날 수 있다?!
            return;
          }
          console.log(currCategory);
          // 커스텀 오버레이를 숨깁니다
          //placeOverlay.setMap(null);

          // 지도에 표시되고 있는 마커를 제거합니다
          //removeMarker1();
          /*  
    ps.categorySearch(currCategory, placesSearchCB1, {useMapBounds:true}); */
          var keyword = currCategory; //임시로 입력한 키워드
          //document.getElementById('keyword').value;
          const currentCoordinate = await getCurrentCoordinate();
          var options = {
            location: currentCoordinate,
            radius: 10000,
            sort: kakao.maps.services.SortBy.DISTANCE,
          };
          if (!keyword.replace(/^\s+|\s+$/g, '')) {
            alert('키워드를 입력해주세요!');
            return false;
          }
          // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
          ps.keywordSearch(keyword, placesSearchCB, options);
        }

        // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
        function placesSearchCB1(data, status, pagination) {
          if (status === kakao.maps.services.Status.OK) {
            // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
            displayPlaces1(data);
          } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
          } else if (status === kakao.maps.services.Status.ERROR) {
            // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
          }
        }

        // 지도에 마커를 표출하는 함수입니다
        function displayPlaces1(places) {
          // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
          // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
          var order = document
            .getElementById(currCategory)
            .getAttribute('data-order');

          for (var i = 0; i < places.length; i++) {
            // 마커를 생성하고 지도에 표시합니다
            var marker = addMarker1(
              new kakao.maps.LatLng(places[i].y, places[i].x),
              order
            );

            // 마커와 검색결과 항목을 클릭 했을 때
            // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
            (function (marker, place) {
              kakao.maps.event.addListener(marker, 'click', function () {
                displayPlaceInfo(place);
              });
            })(marker, places[i]);
          }
        }

        // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
        function addMarker1(position, order) {
          var imageSrc =
              'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
            imgOptions = {
              spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
              spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
              offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(
              imageSrc,
              imageSize,
              imgOptions
            ),
            marker = new kakao.maps.Marker({
              position: position, // 마커의 위치
              image: markerImage,
            });

          marker.setMap(map); // 지도 위에 마커를 표출합니다
          markers.push(marker); // 배열에 생성된 마커를 추가합니다

          return marker;
        }

        // 지도 위에 표시되고 있는 마커를 모두 제거합니다
        function removeMarker1() {
          for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
          }
          markers = [];
        }

        // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
        function displayPlaceInfo(place) {
          var content =
            '<div class="placeinfo" id="links">' +
            '   <a class="title" href="' +
            place.place_url +
            '" target="_blank" title="' +
            place.place_name +
            '">' +
            place.place_name +
            '</a>';

          if (place.road_address_name) {
            content +=
              '    <span title="' +
              place.road_address_name +
              '">' +
              place.road_address_name +
              '</span>' +
              '  <span class="jibun" title="' +
              place.address_name +
              '">(지번 : ' +
              place.address_name +
              ')</span>';
          } else {
            content +=
              '    <span title="' +
              place.address_name +
              '">' +
              place.address_name +
              '</span>';
          }

          content +=
            '    <span class="tel">' +
            place.phone +
            '</span>' +
            '<button type="button" class="place_btn appointment_btn"> 약속보기 </button>' +
            '<button type="button" class="place_btn review_btn"> 리뷰보기 </button>' +
            '<button type="button" class="place_btn place_detail_btn" data-toggle="modal" data-target="#modal-fullscreen-xl"> 상세보기 </button>' +
            '</div>' +
            '<div class="after"></div>';

          contentNode.innerHTML = content;
          placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
          placeOverlay.setMap(map);
        }

        // 각 카테고리에 클릭 이벤트를 등록합니다
        function addCategoryClickEvent() {
          var category = document.getElementById('category'),
            children = category.children;

          for (var i = 0; i < children.length; i++) {
            children[i].onclick = onClickCategory;
          }
        }

        // 카테고리를 클릭했을 때 호출되는 함수입니다
        function onClickCategory() {
          var id = this.id,
            className = this.className;

          placeOverlay.setMap(null);

          if (className === 'on') {
            currCategory = '';
            changeCategoryClass();
            removeMarker1();
          } else {
            currCategory = id;
            changeCategoryClass(this);
            searchPlaces1();
          }
        }

        // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
        function changeCategoryClass(el) {
          var category = document.getElementById('category'),
            children = category.children,
            i;

          for (i = 0; i < children.length; i++) {
            children[i].className = '';
          }

          if (el) {
            el.className = 'on';
          }
        }

        const getCurrentCoordinate = async () => {
          return new Promise((res, rej) => {
            // HTML5의 geolocaiton으로 사용할 수 있는지 확인합니다.
            if (navigator.geolocation) {
              // GeoLocation을 이용해서 접속 위치를 얻어옵니다.
              navigator.geolocation.getCurrentPosition(function (position) {
                console.log(position);
                const lat = position.coords.latitude; // 위도
                const lon = position.coords.longitude; // 경도

                const coordinate = new kakao.maps.LatLng(lat, lon);
                res(coordinate);
              });
            } else {
              rej(new Error('현재 위치를 불러올 수 없습니다.'));
            }
          });
        };

        // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
        if (navigator.geolocation) {
          // GeoLocation을 이용해서 접속 위치를 얻어옵니다
          navigator.geolocation.getCurrentPosition(function (position) {
            var lat = position.coords.latitude, // 위도
              lon = position.coords.longitude; // 경도

            var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
              message = '<div style="padding:3px;">내 위치</div>'; // 인포윈도우에 표시될 내용입니다

            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition, message);
          });
        } else {
          // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

          var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
            message = 'geolocation을 사용할수 없어요..';

          displayMarker(locPosition, message);
        }

        // 마커를 표시할 위치와 title 객체 배열입니다
        var positions = [
          {
            title: '갑사화장실',
            latlng: new kakao.maps.LatLng(36.36594682, 127.1862795),
          },
          {
            title: '신원사화장실',
            latlng: new kakao.maps.LatLng(36.33485768, 127.1836061),
          },
          {
            title: '수통골화장실',
            latlng: new kakao.maps.LatLng(36.34515556, 127.288587),
          },
          {
            title: '동학사화장실',
            latlng: new kakao.maps.LatLng(36.35289085, 127.2204177),
          },
        ];

        // 마커 이미지의 이미지 주소입니다
        var imageSrc =
          'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';

        var markers = [];

        var clusterer = new kakao.maps.MarkerClusterer({
          map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
          minLevel: 5, // 클러스터 할 최소 지도 레벨
          markers: markers,
        });

        for (var i = 0; i < positions.length; i++) {
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage,
            // 마커 이미지
          });

          clusterer.addMarker(marker);
        }

        //------------------------------------------
        // 지도에 마커와 인포윈도우를 표시하는 함수입니다
        function displayMarker(locPosition, message) {
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map,
            position: locPosition,
          });

          var iwContent = message, // 인포윈도우에 표시할 내용
            iwRemoveable = true;

          // 인포윈도우를 생성합니다
          var infowindow = new kakao.maps.InfoWindow({
            content: iwContent,
            removable: iwRemoveable,
          });

          // 인포윈도우를 마커위에 표시합니다
          infowindow.open(map, marker);

          // 지도 중심좌표를 접속위치로 변경합니다
          map.setCenter(locPosition);
        }
      </script>
		</div>
	</div>
</div>

<%@include file="layouts/footer.jsp"%>
