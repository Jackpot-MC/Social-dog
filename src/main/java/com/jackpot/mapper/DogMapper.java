package com.jackpot.mapper;

import java.util.List;

import com.jackpot.domain.DogVO;

public interface DogMapper {
	
	public void create(DogVO dog);
	
	public DogVO get(Long dogId);
	
	public int modify(DogVO dog);
	
	public int delete(Long dogId); 
	
	public List<DogVO> getList();

	public List<DogVO> getListByMemberId(Long memberId);
}
