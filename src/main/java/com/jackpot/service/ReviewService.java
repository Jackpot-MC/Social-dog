package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.NoticeCriteria;
import com.jackpot.domain.ReviewCriteria;
import com.jackpot.domain.ReviewVO;

public interface ReviewService {
	// 등록
	public void register(ReviewVO review) throws IOException;

	// 조회
	public ReviewVO get(Long reviewId);

	// 수정
	public boolean modify(ReviewVO review) throws IOException;

	// 삭제
	public boolean remove(Long reviewId);

	public List<ReviewVO> getList(ReviewCriteria cri);
	
	public int getTotal(ReviewCriteria cri);
}
