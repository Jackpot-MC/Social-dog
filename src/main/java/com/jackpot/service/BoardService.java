package com.jackpot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jackpot.domain.BoardAttachmentVO;
import com.jackpot.domain.BoardVO;
import com.jackpot.domain.Criteria;

public interface BoardService {

	public BoardVO get(Long bno);
	public void register(BoardVO board, MultipartFile boardPhoto) throws Exception;

	public void modify(BoardVO board, MultipartFile boardPhoto) throws Exception;
	
	public boolean remove(Long bno);
	
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public BoardAttachmentVO getAttachment(Long no);
	
	public boolean removeAttachment(Long no);
}


