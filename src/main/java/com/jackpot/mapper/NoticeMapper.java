package com.jackpot.mapper;

import com.jackpot.domain.NoticeVO;

public interface NoticeMapper {
	public NoticeVO read(Long noticeId); // PK 전달
	
	public void insert(NoticeVO notice);
	
	public int update(NoticeVO notice);
	
	public int delete(Long noticeId); // PK 전달
}
