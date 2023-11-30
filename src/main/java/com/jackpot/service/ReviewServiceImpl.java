package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jackpot.domain.ReviewCriteria;
import com.jackpot.domain.ReviewVO;
import com.jackpot.mapper.ReviewMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
@Transactional(rollbackFor=Exception.class)
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewMapper mapper;
	
	@Override
	public ReviewVO get(Long reviewId) {
		return mapper.read(reviewId);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void register(ReviewVO review) throws IOException {
		log.info("insert......." + review);
		
		mapper.insert(review);

	}

	@Override
	public boolean modify(ReviewVO review) throws IOException {
		log.info("update......." + review);
		int result= mapper.update(review);
		
		return result == 1;
	}

	@Override
	public boolean remove(Long noticeId) {
		log.info("remove......." + noticeId);
		
		return mapper.delete(noticeId) == 1;
	}

	@Override
	public List<ReviewVO> getList(ReviewCriteria cri) {
		log.info("get List with criteria: " + cri);
		
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(ReviewCriteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	@Override
	public int getAverage() {
		return mapper.getAverage();
	}

}
