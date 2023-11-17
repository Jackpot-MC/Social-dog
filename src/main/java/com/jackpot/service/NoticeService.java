package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.AdminVO;
import com.jackpot.domain.Criteria;
import com.jackpot.domain.NoticeVO;

public interface NoticeService {

	public NoticeVO get(Long noticeId);
	
	public void register(NoticeVO notice) throws IOException;
	
	public boolean modify(NoticeVO notice) throws IOException;

	public boolean remove(Long noticeId);
	
	public List<NoticeVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public List<AdminVO> getAdminLoginId(AdminVO admin);
}
