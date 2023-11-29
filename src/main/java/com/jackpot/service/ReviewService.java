package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.ReviewCriteria;
import com.jackpot.domain.ReviewVO;

public interface ReviewService {

	public ReviewVO get(Long reviewId);
	
	public void register(ReviewVO review) throws IOException;
	
	public boolean modify(ReviewVO review) throws IOException;

	public boolean remove(Long reviewId);
	
	public List<ReviewVO> getList(ReviewCriteria cri);
	
	public int getTotal(ReviewCriteria cri);
	
	public int getAverage();
	
}
