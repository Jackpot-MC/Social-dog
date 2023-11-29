package com.jackpot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jackpot.domain.BoardAttachmentVO;
import com.jackpot.domain.BoardVO;
import com.jackpot.domain.Criteria;

public interface BoardService {

	public void register(BoardVO board, List<MultipartFile> files) throws Exception;
	public BoardVO get(Long bno);
	public boolean modify(BoardVO board, List<MultipartFile> files) throws Exception;
	public boolean remove(Long bno);
//	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
	
	public BoardAttachmentVO getAttachment(Long no);
	public boolean removeAttachment(Long no);
}
