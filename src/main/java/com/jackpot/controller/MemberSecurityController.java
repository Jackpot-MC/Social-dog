package com.jackpot.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private MemberService memberService;

    //메인 페이지 이동
    @GetMapping("/main")
    public void mainGet() {
        log.info("메인 페이지 이동");
    }

    //회원 정보 보기
    @GetMapping("/info")
    public void get(HttpSession session, Model model) throws Exception {
        String id = (String) session.getAttribute("memberId");
        log.info(id);
        MemberVO member = memberService.get(id);
        model.addAttribute("memberVO", member);
    }
    
    //로그인
    @GetMapping("/login")
    public void login() {
        log.info("login page");
    }
  
    //회원가입
    @GetMapping("/signup")
    public void signup(@ModelAttribute("member") MemberVO member) {
        log.info("회원가입 페이지 이동");
    }

    @PostMapping("/signup")
    public String signup(
            @Valid
            @ModelAttribute("member") MemberVO member,
            Errors errors) throws IOException {
    	log.info(member);
		//1. 비밀번호-비밀번호 확인 일치 여부 판단
		if (!member.getMemberLoginPwd().equals(member.getMemberLoginPwd2())) {
			errors.rejectValue("memberLoginPwd", "비밀번호 불일치", "비밀번호가 일치하지 않습니다.");
		}

		//2. 아이디 중복을 거르기
		if (!errors.hasFieldErrors("memberLoginId")) {
			if (memberService.get(member.getMemberLoginId()) != null) {//id중복검사
				errors.rejectValue("memberLoginId", "ID 중복", "이미 사용중인 아이디 입니다.");
			}
		}

		if (errors.hasFieldErrors()) {
			return "member/signup";//에러나면 다시 가입 화면으로
		}

		memberService.signup(member);

		return "redirect:/";//루트로 되돌리기
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
        memberService.update(member);
        return "redirect:/";
    }

    //회원 정보 삭제
    @GetMapping("/delete")
    public String delete(HttpSession session) throws IOException {
        log.info("delete get...");
        String id = (String) session.getAttribute("memberLoginId");
        if (id == null) {
            return "redirect:/";
        }
        return "/member/deleteForm";//삭제완료 화면 미구현
    }

    @PostMapping("/delete")
    public String delete(String memberLoginId, HttpSession session) throws IOException {
        log.info("delete post..." + memberLoginId);
        memberService.delete(memberLoginId);
        session.invalidate();
        return "redirect:/";
    }

	//마이페이지
    @GetMapping("/profile")
	public void profile(Model model,
			Principal principal) {
    	model.addAttribute("member", memberService.get(principal.getName()));
	}
	
	@PostMapping("/profile")
	public String profile(
			@ModelAttribute("member") MemberVO member,
			Errors errors) throws IOException {
		
		// 1. 비밀번호, 비밀번호 확인 일치 여부
		if(!member.getMemberLoginPwd().equals(member.getMemberLoginPwd())) {
			// 에러 추가
			errors.rejectValue("getMemberLoginPwd2", "비밀번호 불일치", "비밀번호가 일치하지 않습니다.");
		}
		if(errors.hasFieldErrors()) {
			return "member/profile";
		}
		memberService.update(member);
		return "redirect:/";
	}
}
