package com.jackpot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jackpot.domain.ReviewVO;
import com.jackpot.mapper.ReviewMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReviewServiceImpl implements ReviewService {

	ReviewMapper reviewMapper;

	@Override
	public void register(ReviewVO review) {
		reviewMapper.create(review);
		Long reviewId = review.getReviewId();
	}

	@Override
	public ReviewVO get(Long reviewId) {
		ReviewVO review = reviewMapper.get(reviewId);
		return review;
	}

	@Override
	public boolean modify(ReviewVO review) {
		int result = reviewMapper.update(review);
		Long ReviewId = review.getReviewId();

		return reviewMapper.update(review) == 1;
	}

	@Override
	public boolean remove(Long reviewId) {
		return reviewMapper.delete(reviewId) == 1;
	}

	@Override
	public List<ReviewVO> getList() {
		return reviewMapper.getList();
	}

}
