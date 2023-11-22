package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.MemberVO;
import com.jackpot.domain.NoticeCriteria;
import com.jackpot.domain.NoticeVO;

public interface NoticeMapper {
	public NoticeVO read(Long noticeId); // PK 전달
	
	public void insert(NoticeVO notice);
	
	public int update(NoticeVO notice);
	
	public int delete(Long noticeId); // PK 전달
	
	public List<NoticeVO> getList();
	
	public List<NoticeVO> getListWithPaging(NoticeCriteria cri);
	
	public int getTotalCount(NoticeCriteria cri);

	public List<MemberVO> getAdminLoginIdMapper(MemberVO admin);
}
