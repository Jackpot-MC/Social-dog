package com.jackpot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jackpot.domain.NoticeVO;
import com.jackpot.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/notice")
@AllArgsConstructor
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping("/list") // View이름: notice/list (앞뒤 "/"과 확장자는 prefix, surfix가 붙여줌)
	public void list(Model model) {

	}
	
	@GetMapping("/register") // 로직이 없어서 Test X
	public void register(@ModelAttribute("notice") NoticeVO notice) { //BoardVO를 board로 부르겠다는 뜻. form 태그를 사용하기 위한 전제조건
		log.info("register");
	}

	@PostMapping("/register") // POST 요청의 리턴 타입은 String
	public String register(
			@Valid @ModelAttribute("notice") NoticeVO notice,
			Errors errors)  throws Exception {
		log.info("register: " + notice);
		
		return "redirect:/notice/list"; // 요청 url
	}
	
	@GetMapping({"/get", "/modify"}) //get : 상세보기, modify: 수정 화면으로 가기
	public void get(@RequestParam("noticeId") Long noticeId, Model model) {
		log.info("/get or modify");
		model.addAttribute("notice", service.get(noticeId));
	}
	
	@PostMapping("/modify")
	public String modify(
			@Valid @ModelAttribute("notice") NoticeVO notice,
			Errors errors) throws Exception{
		log.info("modify: " + notice);
		return "redirect:/notice/get";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("noticeId") Long noticeId) {
		log.info("remove..." + noticeId);
		
		service.remove(noticeId);
		
		return "redirect:/notice/list"; // 요청 url
	}
}
