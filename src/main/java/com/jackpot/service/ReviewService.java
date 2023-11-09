package com.jackpot.service;

import java.util.List;

import com.jackpot.domain.ReviewVO;

public interface ReviewService {
	// 등록
	public void register(ReviewVO review);

	// 조회
	public ReviewVO get(Long reviewId);

	// 수정
	public boolean modify(ReviewVO review);

	// 삭제
	public boolean remove(Long reviewId);

	public List<ReviewVO> getList();
}
