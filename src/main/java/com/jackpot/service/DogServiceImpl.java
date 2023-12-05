package com.jackpot.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jackpot.domain.DogVO;
import com.jackpot.mapper.DogMapper;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Log4j
public class DogServiceImpl implements DogService {
	
	public static final String AVATAR_UPLOAD_DIR = "c:\\Temp\\upload\\tmp";

	@Autowired
	DogMapper dogMapper;

	@Override
	public void register(DogVO dog, MultipartFile avatar) throws IOException {
		dogMapper.create(dog);
//		Long dogId = dog.getDogId();
		
		// avatar 이미지 저장
		if(!avatar.isEmpty()) {
			File dest = new File(AVATAR_UPLOAD_DIR, dog.getDogName() + ".png");
			
			Thumbnails.of(avatar.getInputStream())
	//		.sourceRegion(Positions.CENTER, 550, 550)
			.size(250, 250)
			.toFile(dest);
		} else { log.info("No avatar.................");}
	}

	@Override
	public DogVO get(Long memberId) {
		return dogMapper.get(memberId);
	}

	@Override
	public void modify(DogVO dog, MultipartFile avatar) throws IOException {
		dogMapper.modify(dog);
		
		// avatar 이미지 저장
		if(!avatar.isEmpty()) {
			File dest = new File(AVATAR_UPLOAD_DIR, dog.getDogName() + ".png");
			
			Thumbnails.of(avatar.getInputStream())
	//		.sourceRegion(Positions.CENTER, 550, 550)
			.size(250, 250)
			.toFile(dest);
		} else { log.info("No avatar.................");}
	}

	@Override
	public boolean remove(Long dogNo) {
		return dogMapper.delete(dogNo) == 1;
	}

	@Override
	public List<DogVO> getList() {
		return dogMapper.getList();
	}

	@Override
	public List<DogVO> getListByMemberId(Long memberId) {
		return dogMapper.getListByMemberId(memberId);
	}


}
