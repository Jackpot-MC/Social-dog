const replyAddable = `
	<button class="btn btn-light btn-sm reply-add-show-btn">
		<i class="fa-solid fa-pen-to-square"></i> 답글
	</button>
`;

const commentUpdatable = `
	<button class="btn btn-light btn-sm comment-update-show-btn">
		<i class="fa-solid fa-pen-to-square"></i> 수정
	</button>
	<button class="btn btn-light btn-sm comment-delete-btn">
		<i class="fa-solid fa-times"></i> 삭제
	</button>
`;

function createCommentTemplate(comment, loginId) {
	return `
		<div class = "comment my-3" data-no="${comment.no}" data-loginId="${comment.loginId}">
			<div class="comment-title my-2 d-flex justify-content-between">
				<div>
					<strong class="loginId">
						<img src = "/security/avatar/sm/${comment.loginId}"
							class="avatar-sm">
							${comment.loginId}
					</strong>
					<span class="text-muted ml-3 comment-date">
						${moment(comment.regDate).format('YYYY-MM-DD hh:mm')}
					rr</span>
				</div>
				<div class="btn-group">
					${loginId && (loginId != comment.loginId) ? replyAddable : ''}
					${loginId && (loginId == comment.loginId) ? commentUpdatable : ''}
				</div>
			</div>
				<div class="comment-body">
					<div class="comment-content">${comment.content}</div>
				</div>
				<div class="reply-list ml-5">
					<!-- 답글 목록 출력 영역 -->
				</div>
			</div>
	`;
}

async function loadComments(bno, loginId) {	// 글번호, 로그인 유저
	let comments = [];
	
	// API로 불러오기
	comments = await rest_get(COMMENT_URL); 	// await가 빠졌기 때문에 promise 객체. 그래서 for 루프를 못 돌림
	
	for(let comment of comments) {
		const commentEl= $(createCommentTemplate(comment, loginId));
		$('.comment-list').append(commentEl);

		let replyListEl= commentEl.find('.reply-list');
		for(let reply of comment.replyList) {
			let replyEl = $(createReplyTemplate(reply, loginId));
			replyListEl.append(replyEl);
		};
	}
}

async function createComment(bno, loginId) {
	const content = $('.new-comment-content').val(); //val은 form 요소에서 값을 얻어옴
	console.log(content);
	
	if(!content) {
		alert('내용을 입력하세요.');
		$('.new-comment-content').focus();
		return;
	}
	
	if(!confirm('댓글을 추가할까요?')) return;
	let comment = { bno, loginId , content }
	console.log(comment);
	
	// REST로 등록
	comment = await rest_create(COMMENT_URL, comment);
	
	// 등록 성공 후 DOM 처리
	const commentEl = createCommentTemplate(comment, loginId);
	$('.comment-list').prepend($(commentEl));
	$('.new-comment-content').val('');
}

function createCommentEditTemplate(comment) {
	return `
		<div class = "bg-light p-2 rounded comment-edit-block">
			<textarea class="form-control mb-1 comment-editor">${comment.content}</textarea>
				<div class="text-right">
					<button class="btn btn-light btn-sm py-1 comment-update-btn">
						<i class="fa-solid fa-check"></i> 확인
					</button>
					<button class="btn btn-light btn-sm py-1 comment-update-cancel-btn">
						<i class="fa-solid fa-undo"></i> 취소
					</button>
				</div>
		</div>
	`;
}

// 댓글 수정 화면 보여주기
function showUpdateComment(e) {
	const commentEl= $(this).closest('.comment');
	const no = commentEl.data("no");
	
	const contentEl= commentEl.find('.comment-content');
	const comment = { no, content: contentEl.html().trim() };	//trim으로 공백 제거
	console.log(comment);
	
	contentEl.hide();
	commentEl.find('.btn-group').hide();
	
	const template = createCommentEditTemplate(comment);
	const el = $(template);
	commentEl.find('.comment-body').append(el);
}

// 댓글 수정하기
async function updateComment(commentEl, loginId) {
	if(!confirm('수정할까요?')) return;
	
	const editContentEl = commentEl.find('.comment-edit-block');	//수정 창
	const content = editContentEl.find('.comment-editor').val();	//수정 내용
	const no = parseInt(commentEl.data("no"));
	
	let comment = { no, loginId, content };
	console.log('수정', comment);
	
	// COMMENT UPDATE API 호출.....
	comment = await rest_modify(COMMENT_URL + comment.no, comment);
	
	const contentEl = commentEl.find('.comment-content')
	editContentEl.remove();
	contentEl.html(comment.content);	// 변경된 내용으로 화면 내용 수정
	contentEl.show();
	
	commentEl.find('.btn-group').show();
}

// 댓글 수정 취소
function cancelCommentUpdate(e) {
	const commentEl = $(this).closest('.comment');
	commentEl.find('.comment-content')
			 .css('display', 'block');
	
	commentEl.find('.comment-edit-block').remove();
	commentEl.find('.btn-group').show();
}

// 댓글 삭제
async function deleteComment(e) {
	if(!confirm('댓글을 삭제할까요?')) return;
	
	const comment = $(this).closest('.comment')
	const no = comment.data("no");
	
	// api 호출
	await rest_delete(COMMENT_URL + no);
	
	comment.remove();
}