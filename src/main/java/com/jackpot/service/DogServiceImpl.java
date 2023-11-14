package com.jackpot.service;

import java.util.List;

import com.jackpot.domain.DogVO;
import com.jackpot.mapper.DogMapper;

public class DogServiceImpl implements DogService {

	DogMapper dogMapper;

	@Override
	public void register(DogVO dog) {
		dogMapper.create(dog);
		Long dogId = dog.getDogId();
	}

	@Override
	public DogVO get(Long dogId) {
		DogVO dog = dogMapper.get(dogId);
		return dog;
	}

	@Override
	public void modify(DogVO dog) {
		int result = dogMapper.update(dog);
		Long DogId = dog.getDogId();
		
//		return 
		dogMapper.update(dog);
	}

	@Override
	public boolean remove(Long dogNo) {
		return dogMapper.delete(dogNo) == 1;
	}

	@Override
	public List<DogVO> getList() {
		return dogMapper.getList();
	}

}
