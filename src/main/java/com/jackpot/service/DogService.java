package com.jackpot.service;

import java.io.IOException;
import java.util.List;

import com.jackpot.domain.DogVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface DogService {
	// 등록
	public void register(DogVO dog, MultipartFile avatar) throws IOException;

	// 조회
	public DogVO get(Long memberId);

	// 수정
	public void modify(DogVO dog, MultipartFile avatar) throws IOException;

	// 삭제
	public boolean remove(Long dogId);

	public List<DogVO> getList();
	
	public List<DogVO> getListByMemberId(Long memberId);
	
}
