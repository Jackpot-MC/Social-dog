package com.jackpot.controller;

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

import com.jackpot.domain.ReviewCriteria;
import com.jackpot.domain.ReviewPageDTO;
import com.jackpot.domain.ReviewVO;
import com.jackpot.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {
	
	private ReviewService service;
	
	@GetMapping("/list") // View이름: review/list (앞뒤 "/"과 확장자는 prefix, surfix가 붙여줌)
	public void list(@ModelAttribute("cri") ReviewCriteria cri, Model model) {
		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));
		
		int total = service.getTotal(cri);
		log.info("total: " + total);
		
		model.addAttribute("pageMaker", new ReviewPageDTO(cri, total));
	}
	
	@GetMapping("/register") // 로직이 없어서 Test X
	public void register(@ModelAttribute("review") ReviewVO review) {
		log.info("register");
	}

	@PostMapping("/register") // POST 요청의 리턴 타입은 String
	public String register(
			@Valid @ModelAttribute("review") ReviewVO review,
			Errors errors,
			RedirectAttributes rttr) throws Exception {
		log.info("register: " + review);
		if(errors.hasErrors()) {
			return "review/register";
		}
		
		service.register(review);
		
		rttr.addFlashAttribute("result", review.getReviewId());
		
		return "redirect:/review/list"; // 요청 url
	}
	
	@GetMapping({"/get", "/modify"}) //get : 상세보기, modify: 수정 화면으로 가기
	public void get(@RequestParam("reviewId") Long reviewId, @ModelAttribute("cri") ReviewCriteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("review", service.get(reviewId));
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
		return "redirect:" + cri.getLinkWithReviewId("/review/get", review.getReviewId());
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("reviewId") Long reviewId,
			@ModelAttribute("cri") ReviewCriteria cri,
			RedirectAttributes rttr) {
		log.info("remove..." + reviewId);
		
		service.remove(reviewId);
		
		return "redirect:" + cri.getLink("/review/list"); // 요청 url
	}
}
