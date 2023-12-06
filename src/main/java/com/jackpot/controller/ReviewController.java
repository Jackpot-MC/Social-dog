package com.jackpot.controller;

import java.security.Principal;
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

import com.jackpot.domain.MemberVO;
import com.jackpot.domain.ReviewCriteria;
import com.jackpot.domain.ReviewPageDTO;
import com.jackpot.domain.ReviewVO;
import com.jackpot.service.MemberService;
import com.jackpot.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {
	
	private ReviewService service;
	private MemberService memberService;
	
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
	
	@GetMapping({"/list", "/register"}) // View이름: notice/list (앞뒤 "/"과 확장자는 prefix, surfix가 붙여줌)
	public void list(@ModelAttribute("cri") ReviewCriteria cri,
			@ModelAttribute("review") ReviewVO review,
			Principal principal,
			Model model) {
		
		/* member.setLoginId(principal.getName()); */

		log.info("list..........................: " + cri);
		model.addAttribute("list", service.getList(cri));
		
		model.addAttribute("average", service.getAverage());
		/* model.addAttribute("member", member); */
		
		log.info("member...................: " + memberService.get(principal.getName()));
		log.info("principal.......................: " + principal);
		model.addAttribute("member", memberService.get(principal.getName()));
		
		log.info("reviewModify: " + review);
		
		int total = service.getTotal(cri);
		log.info("total: " + total);
		
		//model.addAttribute("pageMaker", new PageDTO(cri, 274)); // 임의로 273 요청
		model.addAttribute("pageMaker", new ReviewPageDTO(cri, total));
	}
	
	/*
	 * @GetMapping("/register") // 로직이 없어서 Test X public void
	 * register(@ModelAttribute("review") ReviewVO review) { log.info("register"); }
	 */

	@PostMapping({"/list", "/register"}) // POST 요청의 리턴 타입은 String
	public String list(
			@Valid @ModelAttribute("review") ReviewVO review,
			Principal principal,
			Errors errors,
			RedirectAttributes rttr) throws Exception {
		log.info("register: " + review);
		if(errors.hasErrors()) {
			return "/";
		}
		
		service.register(review);
		
		rttr.addFlashAttribute("result", review.getReviewId());
		
		log.info("register: " + review);
		
		return "redirect:/walk?mode=review"; // 요청 url
	}
	
	@GetMapping({"/get", "/modify"}) //get : 상세보기, modify: 수정 화면으로 가기
	public void get(@RequestParam("reviewId") Long reviewId, 
			@ModelAttribute("cri") ReviewCriteria cri,
			Principal principal, Model model) {		
		
		log.info("/get or modify.............." + service.get(reviewId));
		model.addAttribute("review", service.get(reviewId));
		
		log.info("principal.......................: " + principal);
		model.addAttribute("member", memberService.get(principal.getName()));
	}
	
	@PostMapping("/modify")
	public String modify(
			@Valid @ModelAttribute("review") ReviewVO review,
			Errors errors,
			@ModelAttribute("cri") ReviewCriteria cri,
			RedirectAttributes rttr) throws Exception{
		log.info("modify:" + review);
		if(errors.hasErrors()) {
			return "review/modify";
		}
		if (service.modify(review)) {
			// Flash --> 1회성
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/walk?mode=review";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("reviewId") Long reviewId,
			@ModelAttribute("cri") ReviewCriteria cri,
			RedirectAttributes rttr) {
		log.info("remove..." + reviewId);
		
		service.remove(reviewId);
		
		return "redirect:/walk?mode=review"; // 요청 url
	}
}
