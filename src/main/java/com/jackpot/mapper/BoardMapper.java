package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.BoardAttachmentVO;
import com.jackpot.domain.BoardVO;
import com.jackpot.domain.Criteria;


public interface BoardMapper {
	public int getTotalCount(Criteria cri);

//	@Select("select * from tbl_board")
	//전체목록 얻기
	public List<BoardVO> getList();

	//페이지 목록 추출
	public List<BoardVO> getListWithPaging(Criteria cri);

	public void insert(BoardVO board); //입력
	//추가하고 primary key 얻음
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(long bno); // 출력
	public int delete(long bno); // 삭제
	public int update(BoardVO board);
	
	
	//파일업로드 관련
	public void insertAttachment(BoardAttachmentVO attach); //삽입
	public List<BoardAttachmentVO> getAttachmentList(Long bno); //리스트
	public BoardAttachmentVO getAttachment(Long no); //가져오기
	public int removeAttachment(Long no); //삭제
}

