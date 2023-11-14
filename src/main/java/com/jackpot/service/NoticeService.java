package com.jackpot.service;

import java.io.IOException;

import com.jackpot.domain.NoticeVO;

public interface NoticeService {

	public NoticeVO get(Long noticeId);
	
	public void register(NoticeVO notice) throws IOException;
	
	public int modify(NoticeVO notice) throws IOException;

	public boolean remove(Long noticeId);
}
