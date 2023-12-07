<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="layouts/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="/resources/css/review/list.css" type="text/css" />
<link rel="stylesheet" href="/resources/css/appointment/list.css" type="text/css" />

<script type="text/javascript">
	$(document).ready(function() {

	});
</script>

<div class="container">
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
	        <h2 class="our_text">최고의 반려견 간식</h2>
	        <h1 class="gifts_text">건강한 오리 육포</h1>
	        <p class="standerd_text">엄선된 오리 고기로만 만들었습니다.</p>
	      </div>
	    </div>
	    <div class="carousel-item">
	      <img src="/resources/images/banner02.png" class="d-block w-100" alt="...">
	      <div class="carousel-caption d-none d-md-block">
			<h2 class="our_text">소중한 반려견 사진 촬영은</h2>
	        <h1 class="gifts_text">앙꼬네 스튜디오</h1>
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
</div>

    <!-- Blog Section Begin -->
    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>소셜독 이용하기</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="/resources/images/introduce01.png" alt="">
                        </div>
                        <div class="blog__item__text">
                            <h5>주변 반려견들과 친구가 되어보세요!</h5>
                            <p>소셜독은 위치 기반의 반려견 산책 약속 기능을 제공합니다. 동네의 반려견들과 친구가 되어보세요.</p>
                        	<div class="redmore_bt"><a href="/walk">바로가기</a></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="/resources/images/introduce02.png" alt="">
                        </div>
                        <div class="blog__item__text">
                            <h5>다가오는 약속을 확인하세요.</h5>
                            <p>나의 반려견이 근처 반려견 친구를 만나는 소중한 시간입니다. 약속을 확인해 보세요!</p>
                        	<div class="redmore_bt"><a href="/appointment/my_appointment">바로가기</a></div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="/resources/images/introduce03.png" alt="">
                        </div>
                        <div class="blog__item__text">
                            <h5>나의 반려견을 자랑해 보세요.</h5>
                            <p>산책 약속에 다녀오셨나요? 소중했던 추억과 함께 나의 반려견을 자랑해 보세요.</p>
                        	<div class="redmore_bt"><a href="/board/list">바로가기</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->
    
    <!--================ About History Area  =================-->
    <section class="about_history_area section_gap">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>오늘의 강아지</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 d_flex align-items-center">
                    <div class="about_content">
                        <h2 class="title title_color mt-5"> <br>"자몽이" <br></h2>
                        <p>자몽이는 아주 순한 강아지예요.
                        <br>어제는 동네 친구를 만나 산책을 하고 왔어요.<br>
                        약속 장소는, 자몽이가 제일 좋아하는 카페 앞이었어요.<br>
                        서로 가장 좋아하는 간식을 가지고 만나기로 했답니다.</p>
                        <a href="/board/list" class="button_hover theme_btn_two mb-4">더 많은 강아지 보러가기</a>
                    </div>
                </div>
                <div class="col-md-6">
                    <img class="img-fluid" src="/resources/images/mydog.png" alt="img">
                </div>
            </div>
        </div>
    </section>
    <!--================ About History Area  =================-->

<%@include file="layouts/footer.jsp"%>
