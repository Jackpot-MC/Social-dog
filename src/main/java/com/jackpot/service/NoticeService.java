package com.jackpot.service;

import java.io.IOException;

import com.jackpot.domain.NoticeVO;

public interface NoticeService {

	public NoticeVO get(int noticeId);
	
	public void register(NoticeVO notice) throws IOException;
	
	public void modify(NoticeVO notice) throws IOException;

	public boolean remove(int noticeId);
}
