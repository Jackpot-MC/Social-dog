package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.CommentVO;


public interface CommentMapper {
	List<CommentVO> readAll(Long bno);
	CommentVO get(Long no);
	
	void create(CommentVO vo);
	void update(CommentVO vo);
	void delete(Long no);

}
