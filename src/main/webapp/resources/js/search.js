$(document).ready(function() {
	// Criteria 값을 넘겨주는 getLink() (UriComponentsBuilder)를 사용하게 되면서 필요 없어짐
/* 	let actionForm= $('#actionForm');
		$('a.page-link').on('click', function(e) {
			e.preventDefault();		// 해당 태그의 디폴트 액션 실행을 막음
			console.log('click');
			actionForm.find('input[name="pageNum"]')	//<input name="pageNum" ....>을 찾음
			.val($(this).attr('href'));		//input 내 <a href="1"> 등을 찾아 val값을 삽입
											// attr(속성명) => read / attr(속성명, 값) => write
											// this는 $('#actionForm')의 jQuery 모두를 가리킴
			actionForm.submit();
	});

		$('.move').on('click', function(e) {
			e.preventDefault();
			actionForm.append('<input type="hidden" name="bno"/>');
			actionForm.find('input[name="bno"]')
			.val($(this).attr('href'));
			actionForm.attr('action', '/board/get');
			actionForm.submit();
		}); */
	
	// required. 필수 요소 체크 코드
	let searchForm= $('#searchForm'); // 자손
		$('#searchForm button').on('click', function(e) { // 후손. 검색 버튼
			let type = searchForm.find('option:selected'); // : 은 상태 선택자
			if(!type.val()){
				alert('검색 종류를 선택하세요.');
				type.focus();
				return false; //submit 하지 말라는 뜻
			}
		
			let keyword = searchForm.find('input[name="keyword"]');
			if(!keyword.val()){
				alert('키워드를 입력하세요.');
				keyword.focus();
				return false;
			}
			
			searchForm.find('input[name="pageNum"]').val('1');
			e.preventDefault();
			
			searchForm.submit();
		
		});		
});