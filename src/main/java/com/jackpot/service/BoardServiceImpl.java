package com.jackpot.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jackpot.domain.BoardAttachmentVO;
import com.jackpot.domain.BoardVO;
import com.jackpot.domain.Criteria;
import com.jackpot.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    public static final String AVATAR_UPLOAD_DIR = "c:/Temp/upload/tmp";

	@Autowired
	private BoardMapper mapper;

    
	@Override
	public BoardVO get(Long bno) {
		BoardVO board = mapper.read(bno);

		List<BoardAttachmentVO> list = mapper.getAttachmentList(bno);
		board.setAttaches(list);
		return board;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void register(BoardVO board, MultipartFile avatar) throws Exception {
		mapper.insertSelectKey(board);

		// avatar 이미지 저장
		if(!avatar.isEmpty()) {
			File dest = new File(AVATAR_UPLOAD_DIR, board.getTitle() + ".png");
			
			Thumbnails.of(avatar.getInputStream())
	//		.sourceRegion(Positions.CENTER, 550, 550)
			.size(1280, 720)
			.toFile(dest);
		} else { log.info("No avatar.................");}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void modify(BoardVO board, MultipartFile avatar) throws Exception {
		mapper.modify(board);
		
		// avatar 이미지 저장
		if(!avatar.isEmpty()) {
			File dest = new File(AVATAR_UPLOAD_DIR, board.getTitle() + ".png");
			
			Thumbnails.of(avatar.getInputStream())
	//		.sourceRegion(Positions.CENTER, 550, 550)
			.size(1280, 720)
			.toFile(dest);
		} else { log.info("No avatar.................");}
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove......" + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with criteria: " + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	@Override
	public BoardAttachmentVO getAttachment(Long no) {
		return mapper.getAttachment(no);
	}

	@Override
	public boolean removeAttachment(Long no) {
		return mapper.removeAttachment(no) == 1;
	}
	
}
