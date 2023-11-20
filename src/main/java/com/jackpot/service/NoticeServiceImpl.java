package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jackpot.domain.AdminVO;
import com.jackpot.domain.NoticeCriteria;
import com.jackpot.domain.NoticeVO;
import com.jackpot.mapper.NoticeMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
@Transactional(rollbackFor=Exception.class)
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeMapper mapper;
	
	@Override
	public NoticeVO get(Long noticeId) {
		return mapper.read(noticeId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void register(NoticeVO notice) throws IOException {
		log.info("insert......." + notice);
		
		mapper.insert(notice);

	}

	@Override
	public boolean modify(NoticeVO notice) throws IOException {
		log.info("update......." + notice);
		int result= mapper.update(notice);
		
		return result == 1;
	}

	@Override
	public boolean remove(Long noticeId) {
		log.info("remove......." + noticeId);
		
		return mapper.delete(noticeId) == 1;
	}

	@Override
	public List<NoticeVO> getList(NoticeCriteria cri) {
		log.info("get List with criteria: " + cri);
		
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(NoticeCriteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<AdminVO> getAdminLoginId(AdminVO admin) {
		log.info("get AdminLoginId");
		
		return mapper.getAdminLoginIdMapper(admin);
	}

}
