<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp" %>
<link rel="stylesheet" href="/resources/css/signup.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  
  
  
<script>

//유효성 검사 통과유무 변수
var LoginIdError = false;
var passwordError = false;
var passConfirm = false;
var nameError = false;
var bithDateError = false;
var emailError = false;
var addressError = false;


$(document).ready(function () {

	//회원가입 버튼(회원가입 기능 작동)
	$(".signup_button").click(function(){
		$("#signup_form").attr("action", "../member/signup");
		$("#signup_form").submit();
	});
	
    // Label effect
    $('input').focus(function () {

        $(this).siblings('label').addClass('active');
    });

    // Form validation
    $('input').blur(function () {
        // Id
        if ($(this).hasClass('memberLoginId')) {
            if ($(this).val().length === 0) {
                $(this).siblings('span.error').text('아이디를 입력해주세요.').fadeIn().parent('.form-group').addClass('hasError');
                LoginIdError = false;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                LoginIdError = true;
            }
        }

        // Password
        if ($(this).hasClass('memberLoginPwd')) {
            if ($(this).val().length < 6) {
                $(this).siblings('span.error').text('6자 이상의 비밀번호를 입력해주세요.').fadeIn().parent('.form-group').addClass('hasError');
                passwordError = false;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                passwordError = true;
            }
        }
        
        // Password confirmation
        if ($('.memberLoginPwd2').val() !== $('.memberLoginPwd').val()) {
            $('.memberLoginPwd2').siblings('.error').text('비밀번호가 일치하지 않습니다.').fadeIn().parent('.form-group').addClass('hasError');
            passConfirm = false;
        } else {
            $('.memberLoginPwd2').siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
            passConfirm = true;
        }
        
        // User Name
        if ($(this).hasClass('memberName')) {
            if ($(this).val().length === 0) {
                $(this).siblings('span.error').text('이름을 입력해주세요.').fadeIn().parent('.form-group').addClass('hasError');
                nameError = false;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                nameError = true;
            }
        }

        // Email
        if ($(this).hasClass('memberEmail')) {
            if ($(this).val().length == '') {
                $(this).siblings('span.error').text('이메일을 입력해주세요.').fadeIn().parent('.form-group').addClass('hasError');
                emailError = false;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                emailError = true;
            }
        }
        
        // BirthDate
        if ($(this).hasClass('memberBirth')) {
            if ($(this).val().length == '') {
                $(this).siblings('span.error').text('생년원일을 선택해주세요.').fadeIn().parent('.form-group').addClass('hasError');
                bithDateError = false;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                bithDateError = true;
            }
        }
        
        // Address
        if ($(this).hasClass('memberAddress')) {
            if ($(this).val().length == '') {
                $(this).siblings('span.error').text('주소를 입력해주세요.').fadeIn().parent('.form-group').addClass('hasError');
                addressError = false;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                addressError = true;
            }
        }

        // label effect
        if ($(this).val().length > 0) {
            $(this).siblings('label').addClass('active');
        } else {
            $(this).siblings('label').removeClass('active');
        }
    });
    
    
    
 	//이메일 선택 옵션
    const domainListEl = document.querySelector('#domain-list')
    const domainInputEl = document.querySelector('#domain-txt')
    // select 옵션 변경 시
    domainListEl.addEventListener('change', (event) => {
      // option에 있는 도메인 선택 시
      if(event.target.value !== "type") {
        // 선택한 도메인을 input에 입력하고 disabled
        domainInputEl.value = event.target.value
        domainInputEl.disabled = true
      } else { // 직접 입력 시
        // input 내용 초기화 & 입력 가능하도록 변경
        domainInputEl.value = ""
        domainInputEl.disabled = false
      }
    })  
    
});
</script>
<!-- <script>
//totalEmail 가져오기
$("#memberEmail").blur(function(){
	email();
});

$("#domain-txt").change(function(){
	email();
});

function email(){
	const email = $("#memberEmail".val();
	const middle = $("#middle").text();
	const address = $("#domain-txt").val();
	if(email != "" & address != ""){
		$("#totalEmail").val(email+middle+address);
	}
};

</script>
 -->

<script>
/* function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 주소변수 문자열과 합치기
                addr += extraAddr;
            
            } else {
                addr += ' ';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $(".address_input1").val(data.zonecode);
            $(".address_input2").val(addr);
            // 커서를 상세주소 필드로 이동한다.
            $(".address_input3").attr("readonly",false);
            $(".address_input3").focus();
            
        }
    }).open();
    
    
    //totalAddress 가져오기
    $(".address_input1").blur(function(){
    	address();
    });
    $(".address_input2").blur(function(){
    	address();
    });
    $(".address_input3").change(function(){
    	address();
    });
    
    function address(){
    	const input1 = $(".address_input1".val();
    	const input2 = $(".address_input2").val();
    	const input3 = $(".address_input3").val();
    	if(input1 != "" & input2 != ""){
    		$("#totalAddress").val(input1+input2+input3);
    	}
    };
} */
</script>

<div class="container">
	<section id="formHolder">

        <!-- Signup Form -->
        <div class="col-sm-6 form">
            <div class="signup form-peice">
            
               <form id="signup_form" method="post">
				<h1 class="mt-1">회원가입</h1>
				
                  <div class="form-group" id="info__id">
                     <label for="memberLoginId">아이디</label>
                     <input type="text" name="memberLoginId" id="memberLoginId" class="memberLoginId" required>
                     <span class="error"></span>
                  </div>

                  <div class="form-group">
                     <label for="memberLoginPwd">비밀번호</label>
                     <input type="password" name="memberLoginPwd" id="memberLoginPwd" class="memberLoginPwd" required>
                     <span class="error"></span>
                  </div>

                  <div class="form-group">
                     <label for="memberLoginPwd2">비밀번호 확인</label>
                     <input type="password" name="memberLoginPwd2" id="memberLoginPwd2" class="memberLoginPwd2" required>
                     <span class="error"></span>
                  </div>

                  <div class="form-group">
                     <label for="memberName">이름</label>
                     <input type="text" name="memberName" id="memberName" class="memberName" required>
                     <span class="error"></span>
                  </div>
                  
           	      <div class="form-group">
                     <label for="memberBirth">생년월일</label>
                     <input type="date" value="2000-01" name="memberBirth" id="memberBirth" class="memberBirth" required>
                     <span class="error"></span>
                  </div>

                  <div class="form-group">
                     <label for="memberEmail">이메일</label>
                     <div class="info" id="info_email">
                     <input type="text" name="memberEmail" id="memberEmail" class="memberEmail" required>
                     <span id="middle">@</span>
                     <input type="text" name="memberEmail" id="domain-txt" class="box">
                     <select class="box" id="domain-list">
                     	 <option value="type">직접 입력</option>
					 	 <option value="naver.com">naver.com</option>
						 <option value="google.com">gmail.com</option>
						 <option value="hanmail.net">hanmail.net</option>
						 <option value="nate.com">nate.com</option>
						 <option value="kakao.com">yahoo.com</option>
					 </select>
					 <input type="hidden" id="totalEmail" name="email" value="">
                     <span class="error"></span>
                     </div>
                  </div>

				  <div class="form-group">
                     <label for="memberAddress">주소</label>
                     <input type="text" name="memberAddress" id="memberAddress" class="memberAddress" required>
                     <span class="error"></span>
                  </div>
                  
                  <!-- <div class="form-group">
                     <label for="memberAddress">주소</label>
                     <input type="text" class="address_input1" name="address_input1" id="sample6_postcode" placeholder="우편번호">
					 <input type="button" class="address_button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					 <input type="text" class="address_input2" name="address_input1" id="sample6_address" placeholder="주소"><br>
					 <input type="text" class="address_input3" name="address_input1" id="sample6_detailAddress" placeholder="상세주소">
                     <input type="hidden" id="totalAddress" name="address" value="">
                     <span class="error"></span>
                  </div>   -->                
                                    
					<div class="CTA text-center">
	                    <input type="submit" value="Signup Now" class="signup_button">
	                    <br>
	                    <br>이미 회원이신가요?
	                    <a href="member_login" class="switch">로그인 하러가기</a>
	                </div>
               </form>
            </div><!-- End Signup Form -->
         </div>
   </section>
</div>
  
<%@ include file="../layouts/footer.jsp" %>