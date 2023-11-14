package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.ReviewVO;

public interface ReviewMapper {
	
	public void create(ReviewVO review);
	
	public ReviewVO get(Long reviewId);
	
	public int update(ReviewVO review);
	
	public int delete(Long reviewId); 
	
	public List<ReviewVO> getList();
}
