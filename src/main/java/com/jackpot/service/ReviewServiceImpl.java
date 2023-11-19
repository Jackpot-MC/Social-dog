package com.jackpot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jackpot.domain.NoticeCriteria;
import com.jackpot.domain.ReviewCriteria;
import com.jackpot.domain.ReviewVO;
import com.jackpot.mapper.ReviewMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReviewServiceImpl implements ReviewService {

	ReviewMapper reviewMapper;

	@Override
	public void register(ReviewVO review) {
		log.info("insert......." + review);
		reviewMapper.create(review);
	}

	@Override
	public ReviewVO get(Long reviewId) {
		return reviewMapper.get(reviewId);
	}

	@Override
	public boolean modify(ReviewVO review) {
		log.info("update......." + review);
		return reviewMapper.update(review) == 1;
	}

	@Override
	public boolean remove(Long reviewId) {
		log.info("remove......." + reviewId);
		return reviewMapper.delete(reviewId) == 1;
	}

	@Override
	public List<ReviewVO> getList(ReviewCriteria cri) {
		log.info("get List with criteria: " + cri);
		return reviewMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(ReviewCriteria cri) {
		log.info("get total count");
		return reviewMapper.getTotalCount(cri);
	}
}
