package com.jackpot.mapper;

import com.jackpot.domain.NoticeVO;

public interface NoticeMapper {
	public NoticeVO read(int noticeId); // PK 전달
	
	public void insert(NoticeVO notice);
	
	public int update(NoticeVO notice);
	
	public int delete(int noticeId); // PK 전달
}
