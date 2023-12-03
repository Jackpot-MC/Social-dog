package com.jackpot.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackpot.domain.DogVO;
import com.jackpot.mapper.DogMapper;

import lombok.extern.log4j.Log4j;
import org.springframework.web.multipart.MultipartFile;

@Service
@Log4j
public class DogServiceImpl implements DogService {

	@Autowired
	DogMapper dogMapper;

	private static final String PHOTO_UPLOAD_DIR = "c:/upload/photo";

	@Override
	public void register(DogVO dog, MultipartFile photo) throws IOException {
		dogMapper.create(dog);

		if(!photo.isEmpty()) {
			File dest = new File(PHOTO_UPLOAD_DIR, dog.getDogId() + ".png");

			Thumbnails.of(photo.getInputStream())
					.size(50, 50)
					.toFile(dest);
		}

		Long dogId = dog.getDogId();
	}

	@Override
	public DogVO get(Long dogId) {
		DogVO dog = dogMapper.get(dogId);
		return dog;
	}

	@Override
	public Boolean modify(DogVO dog) {
		int result = dogMapper.update(dog);

		return result == 1;
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
