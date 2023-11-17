package com.jackpot.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.jackpot.domain.MemberVO;
import com.jackpot.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member")
@AllArgsConstructor
public class MemberSecurityController {

	@Autowired
	MemberService service;

	//메인 페이지 이동
	@GetMapping("/main")
	public void mainGet() {
		log.info("메인 페이지 이동");
	}

	//회원 정보 보기
	@GetMapping("/info")
	public void get(HttpSession session, Model model) throws Exception{
		String id = (String) session.getAttribute("memberId");
		log.info(id);
		MemberVO member = service.get(id);
		model.addAttribute("memberVO", member);

		/*
		 * model.addAttribute("memberVO",
		 * service.get((String)session.getAttribute("id")));
		 */

	}

	//회원가입
	@GetMapping("/signup")
	public void signup(@ModelAttribute("member") MemberVO member, Model model) {
		log.info("회원가입 페이지 이동");
		member.setMemberId(1L);
	}

	@PostMapping("/signup")
	public String signup(
			@Valid
			@ModelAttribute("member") MemberVO member,
			Errors errors, Model model) throws IOException {
		service.join(member);
		log.info("Post: " + member);
		return "/join/join_result";
//		return "redirect:/";
	}

	//회원 정보 수정
	@GetMapping("/update")
	public void update(@ModelAttribute("member") MemberVO member, Model model) {
		log.info("update get...");
		member.setMemberId(1L);
	}

	@PostMapping("/update")
	public String update(MemberVO member) throws IOException {

		log.info("update post..." + member);
		service.update(member);
		return "redirect:/";
	}

	//회원 정보 삭제
	@GetMapping("/delete")
	public String delete(HttpSession session) throws IOException {
		log.info("delete get...");
		String id = (String) session.getAttribute("memberLoginId");
		if(id == null) {
			return "redirect:/member/main";
		}
		return "/member/deleteForm";
	}

	@PostMapping("/delete")
	public String delete(String memberLoginId, HttpSession session) throws IOException {
		log.info("delete post..." + memberLoginId);
		service.delete(memberLoginId);
		session.invalidate();
		return "redirect:/member/main";
	}

	//로그인
	@GetMapping("/login")
	public void login() {
		log.info("login page");
	}

	@PostMapping("/login")
	public String loginPost(MemberVO member)throws IOException {
		log.info("Post: " + member);

		return "/home";
	}

	//로그아웃
	@GetMapping("/logout")
	public void logout() {
		log.info("logout page");
	}
}
