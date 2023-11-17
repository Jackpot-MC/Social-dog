package com.jackpot.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jackpot.domain.AdminVO;
import com.jackpot.domain.Criteria;
import com.jackpot.domain.NoticeVO;
import com.jackpot.domain.PageDTO;
import com.jackpot.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/notice")
@AllArgsConstructor
public class NoticeController {
	
	private NoticeService service;
	
	@ModelAttribute("searchTypes")
	public Map<String, String> searchTypes() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "-- 검색대상선택 --");
		map.put("T", "제목");
		map.put("A", "작성자");
		map.put("C", "내용");
		map.put("TC", "제목+내용");
		map.put("TA", "제목+작성자");
		map.put("TAC", "제목+작성자+내용");
		
		return map;		
	}
	
	@GetMapping("/list") // View이름: notice/list (앞뒤 "/"과 확장자는 prefix, surfix가 붙여줌)
	public void list(@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));
		
		int total = service.getTotal(cri);
		log.info("total: " + total);
		
		//model.addAttribute("pageMaker", new PageDTO(cri, 274)); // 임의로 273 요청
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("/register") // 로직이 없어서 Test X
	public void register(@ModelAttribute("notice") NoticeVO notice) {
		log.info("register");
	}

	@PostMapping("/register") // POST 요청의 리턴 타입은 String
	public String register(
			@Valid @ModelAttribute("notice") NoticeVO notice,
			Errors errors,
			RedirectAttributes rttr) throws Exception {
		log.info("register: " + notice);
		if(errors.hasErrors()) {
			return "notice/register";
		}
		
		service.register(notice);
		
		rttr.addFlashAttribute("result", notice.getNoticeId());
		
		return "redirect:/notice/list"; // 요청 url
	}
	
	@GetMapping({"/get", "/modify"}) //get : 상세보기, modify: 수정 화면으로 가기
	public void get(@RequestParam("noticeId") Long noticeId, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("notice", service.get(noticeId));
	}
	
	@PostMapping("/modify")
	public String modify(
			@Valid @ModelAttribute("notice") NoticeVO notice,
			Errors errors,
			@ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) throws Exception{
		log.info("modify:" + notice);
		if(errors.hasErrors()) {
			return "notice/modify";
		}
		if (service.modify(notice)) {
			// Flash --> 1회성
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:" + cri.getLinkWithNoticeId("/notice/get", notice.getNoticeId());
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("noticeId") Long noticeId,
			@ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		log.info("remove..." + noticeId);
		
		service.remove(noticeId);
		
		return "redirect:" + cri.getLink("/notice/list"); // 요청 url
	}
}
