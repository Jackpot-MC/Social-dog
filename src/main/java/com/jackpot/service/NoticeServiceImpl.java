package com.jackpot.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackpot.domain.NoticeVO;
import com.jackpot.mapper.NoticeMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper mapper;
	
	@Override
	public NoticeVO get(int noticeId) {
		return mapper.read(noticeId);
	}

	@Override
	public void register(NoticeVO notice) throws IOException {
		log.info("insert......." + notice);
		
		mapper.insert(notice);

	}

	@Override
	public void modify(NoticeVO notice) throws IOException {
		log.info("update......." + notice);
		
		mapper.update(notice);
	}

	@Override
	public boolean remove(int noticeId) {
		log.info("remove......." + noticeId);
		
		return mapper.delete(noticeId) == 1;
	}

}
