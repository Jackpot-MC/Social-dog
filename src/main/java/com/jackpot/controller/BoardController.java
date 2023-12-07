package com.jackpot.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jackpot.domain.BoardAttachmentVO;
import com.jackpot.domain.BoardVO;
import com.jackpot.domain.Criteria;
import com.jackpot.domain.MemberVO;
import com.jackpot.domain.PageDTO;
import com.jackpot.service.BoardService;
import com.jackpot.service.BoardServiceImpl;
import com.jackpot.service.MemberService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Log4j
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@Autowired
	private MemberService memberSerivce;
	
	@ModelAttribute("searchTypes")
	public Map<String, String> searchTypes(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "-- 검색대상선택--");
		map.put("T", "제목");
		map.put("W", "작성자");
		map.put("C", "내용");
		map.put("TC", "제목+내용");
		map.put("TW", "제목+작성자");
		map.put("TWC", "제목+작성자+내용");
		
		return map;	
	}
	
	@GetMapping("/list")
	public void list(@ModelAttribute("cri")
			Criteria cri, Model model) {

		log.info("list: " + cri);
		int total = service.getTotal(cri);
		log.info("total: " + total);
		
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}

	@GetMapping("/register")
	public void register(
			@ModelAttribute("board") BoardVO board) {
		log.info("register");
	}

	@PostMapping("/register")
	public String register(
			@Valid 
			@ModelAttribute("board") BoardVO board,
			Errors errors, 	MultipartFile boardPhoto,
			RedirectAttributes rttr) throws Exception {

		log.info("register: " + board);

		if(errors.hasErrors()) {
			return "board/register";
		}
		service.register(board, boardPhoto);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}

	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("bno") Long bno, 
					@ModelAttribute("cri") Criteria cri, Model model) {

		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}

	
	@PostMapping("/modify")
	public String modify(
			@Valid @ModelAttribute("board") BoardVO board, 
			Errors errors, MultipartFile boardPhoto,
			@ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) throws Exception{
		log.info("modify:" + board);
		
		if(errors.hasErrors()) {
			return "board/modify";
		}
		
//		if (service.modify(board, boardPhoto)) {
//			// Flash --> 1회성
//			rttr.addFlashAttribute("result", "success");
//			rttr.addAttribute("bno", board.getBno());
//			rttr.addAttribute("pageNum", cri.getPageNum());
//			rttr.addAttribute("amount", cri.getAmount());
//			// 수정 후 다시 목록으로 돌아왔을때 검색한 값이 유지되도록 함
//			rttr.addAttribute("type", cri.getType());
//			rttr.addAttribute("keyword", cri.getKeyword());
//		}
		service.modify(board, boardPhoto);
		return "redirect:" + cri.getLinkWithBno("/board/get", board.getBno());
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, 
						@ModelAttribute("cri") Criteria cri,	
						RedirectAttributes rttr) {

		log.info("remove..." + bno);
		
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			// 삭제 후 다시 목록으로 돌아왔을때 검색한 값이 유지되도록 함
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
		}
		
		service.remove(bno);
//		return "redirect:/board/list";
		return "redirect:" + cri.getLink("/board/list");
	}
	
	@GetMapping("/download/{no}")
	@ResponseBody //view를 사용하지 않고 직접 내보냄
	public void download(@PathVariable("no") Long no, 
			HttpServletResponse response) throws Exception{
		
		BoardAttachmentVO attach = service.getAttachment(no);
		attach.download(response);
	}
	
	@DeleteMapping("/remove/attach/{no}")
	@ResponseBody
	public String removeAttach(@PathVariable("no") Long no) throws Exception {
	service.removeAttachment(no);
	return "OK";
	}

    @GetMapping("/boardPhoto/{size}/{bno}")
    @ResponseBody
    public void boardPhoto(@PathVariable("size") String size,
                        @PathVariable("bno") String bno,
                        HttpServletResponse response) throws IOException {
        
        File src = new File(BoardServiceImpl.BOARDPHOTO_UPLOAD_DIR, "Board" + bno + ".png");
        
        if(!src.exists()) {    // 파일이 존재하지 않으면
            src = new File(BoardServiceImpl.BOARDPHOTO_UPLOAD_DIR, "unknown.png");
        }
        
        response.setHeader("Content-Type", "image/png");
        
        if(size.equals("lg")) {
            Thumbnails.of(src)
                    .size(1280, 720)
                    .toOutputStream(response.getOutputStream());
        } else {
            Thumbnails.of(src)
            .size(720, 1280)
            .toOutputStream(response.getOutputStream());
        }
    }
	
}
