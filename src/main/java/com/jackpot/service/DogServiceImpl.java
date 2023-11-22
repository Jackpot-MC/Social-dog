package com.jackpot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackpot.domain.DogVO;
import com.jackpot.mapper.DogMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class DogServiceImpl implements DogService {

	@Autowired
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
