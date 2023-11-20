package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.NoticeCriteria;
import com.jackpot.domain.NoticeVO;
import com.jackpot.domain.ReviewCriteria;
import com.jackpot.domain.ReviewVO;

public interface ReviewMapper {
	
	public void create(ReviewVO review);
	
	public ReviewVO get(Long reviewId);
	
	public int update(ReviewVO review);
	
	public int delete(Long reviewId); 
	
	public List<ReviewVO> getList();
	
	public List<ReviewVO> getListWithPaging(ReviewCriteria cri);
	
	public int getTotalCount(ReviewCriteria cri);
}
