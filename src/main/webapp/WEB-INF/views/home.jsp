<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="layouts/header.jsp"%>

<div class="mt-4">
	<i class="fa-solid fa-map-location-dot"></i> 주소: ${member.memberAddress}
</div>

<!-- 카카오맵 자바스크립트 키 97a9015498ead4a6b2a0dd3237525dfb -->
<div id="map" style="width:100%; height:350px; background:gray"></div>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=97a9015498ead4a6b2a0dd3237525dfb&libraries=services"></script>
<script>
  let geocoder = new kakao.maps.services.Geocoder();
  let address = '${member.memberAddress}';
  
  let locals = [
	  <c:forEach var="local" items="${member.memberLocals}">
	  	{
	  		name: '${local.placeName}',
	  		coords: new kakao.maps.LatLng(${local.y}, ${local.x})
	  	},
	  </c:forEach>
  ];
  
  // 비동기 함수 (callback))
  geocoder.addressSearch(address, function(result, status){
	  if(status === kakao.maps.services.Status.OK) {
		  // 배열의 첫번째 위치로 이동
		  let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		  
		  let mapContainer = document.getElementById('map');  //  지도 생성
		  let mapOption = {//  지도 제어 코딩
				  center: coords, // 중심 좌표
				  level: 5 // 지도의 확대 레벨
		  };
		  
		  let map = new kakao.maps.Map(mapContainer, mapOption);
		  
/* 		  let marker = new kakao.maps.Marker({
			  map: map,
			  position: coords
		  }); */
		  
		  for(let local of locals) {
			  let marker = new kakao.maps.Marker({
				 map: map,
				 position: local.coords
			  });
		  }
		  
		  // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		  map.setCenter(coords);
	  } else {
		  alert("잘못된 주소입니다.");
	  }
  });
 
</script>

<%@ include file="layouts/footer.jsp"%>