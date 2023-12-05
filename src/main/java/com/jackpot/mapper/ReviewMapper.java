package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.ReviewCriteria;
import com.jackpot.domain.ReviewVO;

public interface ReviewMapper {
	public ReviewVO read(Long reviewId); // PK 전달
	
	public void insert(ReviewVO review);
	
	public int update(ReviewVO review);
	
	public int delete(Long reviewId); // PK 전달
	
	public List<ReviewVO> getList();
	
	public List<ReviewVO> getListWithPaging(ReviewCriteria cri);
	
	public int getTotalCount(ReviewCriteria cri);
	
	public int getAverage();
	
	public Long getReviewId(String loginId);
}
