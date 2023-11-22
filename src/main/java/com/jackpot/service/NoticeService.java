package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.MemberVO;
import com.jackpot.domain.NoticeCriteria;
import com.jackpot.domain.NoticeVO;

public interface NoticeService {

	public NoticeVO get(Long noticeId);
	
	public NoticeVO getLatest();
	
	public void register(NoticeVO notice) throws IOException;
	
	public boolean modify(NoticeVO notice) throws IOException;

	public boolean remove(Long noticeId);
	
	public List<NoticeVO> getList(NoticeCriteria cri);
	
	public int getTotal(NoticeCriteria cri);
	
	public List<MemberVO> getAdminLoginId(MemberVO admin);
}
