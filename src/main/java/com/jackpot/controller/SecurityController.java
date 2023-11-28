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
import com.jackpot.service.DogService;
import com.jackpot.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/security")
@Log4j
public class SecurityController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private DogService dogService;

    @GetMapping("/login")//로그인 페이지 호출
    public void login() {
        log.info("login page");
    }

    //로그아웃
    @GetMapping("/logout")
    public void memberLogout() {
        log.info("logout page");
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
		if (!member.getLoginPwd().equals(member.getLoginPwd2())) {
			errors.rejectValue("loginPwd", "비밀번호 불일치", "비밀번호가 일치하지 않습니다.");
		}

		//2. 아이디 중복을 거르기
		if (!errors.hasFieldErrors("loginId")) {
			if (memberService.get(member.getLoginId()) != null) {//id중복검사
				errors.rejectValue("loginId", "ID 중복", "이미 사용중인 ID입니다.");
			}
		}

		if (errors.hasErrors()) {
			return "/security/signup";//에러나면 다시 가입 화면으로
		}

        memberService.signup(member);
        return "redirect:/";//루트로 되돌리기
    }

    //회원 정보 수정
    @GetMapping("/update")
    public void update(@ModelAttribute("member") MemberVO member, Model model) {
        log.info("update get...");
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
        String id = (String) session.getAttribute("loginId");
        if (id == null) {
            return "redirect:/";
        }
        return "/security/deleteForm";//삭제완료 화면 미구현
    }

    @PostMapping("/delete")
    public String delete(String loginId, HttpSession session) throws IOException {
        log.info("delete post..." + loginId);
        memberService.delete(loginId);
        session.invalidate();
        return "redirect:/";
    }
    
    //마이페이지
    @GetMapping("/mypage")
    public void mypage(Model model, Principal principal) {
    	MemberVO member = memberService.get(principal.getName());
        model.addAttribute("member", member);
        model.addAttribute("dogList", dogService.getListByMemberId(member.getMemberId()));
        log.info("mypage");

    }
    
	//내정보수정
    @GetMapping("/profile")
	public void profile(Model model, Principal principal) {
    	model.addAttribute("member", memberService.get(principal.getName()));
	}

	@PostMapping("/profile")
	public String profile(
			@ModelAttribute("member") MemberVO member,
			Errors errors, Model model) throws IOException {

		if("".equals(member.getMemberName())) {
			errors.rejectValue("memberName", "공백", "이름을 입력해주세요.");
		}
		if("".equals(member.getMemberEmail())) {
			errors.rejectValue("memberEmail", "공백", "이메일을 입력해주세요.");
		}
		if("".equals(member.getMemberAddress())) {
			errors.rejectValue("memberAddress", "공백", "주소를 입력해주세요.");
		}
		if(errors.hasFieldErrors()) {
			return "/security/profile";
		}
		memberService.update(member);
		model.addAttribute("result", "success");
		return "/security/profile";
	}
	
    //비밀번호변경
    @GetMapping("/pwdupdate")
    public void pwdupdate(Model model, Principal principal) {
    	model.addAttribute("member", memberService.get(principal.getName()));
    }
    
    @PostMapping("/pwdupdate")
    public String pwdupdate(
    		@ModelAttribute("member") MemberVO member,
    		Errors errors, Model model) throws IOException {
    	
    	
    	// 비밀번호 공백 확인
    	if("".equals(member.getLoginPwd())) {
    		errors.rejectValue("loginPwd","공백", "비밀번호를 입력해주세요.");
    	}
    	// 비밀번호 확인
    	if(!member.getLoginPwd().equals(member.getLoginPwd2())) {
    		errors.rejectValue("loginPwd2","비밀번호 불일치", "비밀번호가 일치하지 않습니다.");
    	}
    	if(errors.hasFieldErrors()) {
    		log.info("fail=====================================================");
    		return "/security/pwdupdate";
    	}
    	memberService.pwdupdate(member);
    	model.addAttribute("result", "success");
    	log.info("updateed=====================================================");
    	return "/security/pwdupdate";
    }

}
