package com.jackpot.controller;

import com.jackpot.domain.AdminVO;
import com.jackpot.domain.MemberVO;
import com.jackpot.service.AdminService;
import com.jackpot.service.MemberService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/security")
@Log4j
public class SecurityController {

    @Autowired
    AdminService adminService;

    @Autowired
    MemberService memberService;

    @GetMapping("/admin/signup")//회원가입 페이지 호출
    public void signup(@ModelAttribute("admin") AdminVO admin) {
        log.info("회원가입 페이지 이동");
    }

    @PostMapping("/admin/signup")
    public String signup(//회원가입
                         @Valid @ModelAttribute("admin") AdminVO admin,
                         Errors errors) throws IOException {
        //1. 비밀번호-비밀번호 확인 일치 여부 판단
        if (!admin.getAdminLoginPwd().equals(admin.getAdminLoginPwd2())) {
            errors.rejectValue("password2", "비밀번호 불일치", "비밀번호 확인이 일치하지 않습니다.");
        }

        //2. 아이디 중복을 거르기
        if (!errors.hasFieldErrors("adminLoginId")) {
            if (adminService.get(admin.getAdminLoginId()) != null) {//id중복검사
                errors.rejectValue("adminLoginId", "ID 중복", "이미 사용중인 ID입니다.");
            }
            else if(memberService.get(admin.getAdminLoginId())!=null){
                errors.rejectValue("adminLoginId", "ID 중복", "이미 사용 중인 ID입니다.");
            }
        }

        if (errors.hasErrors()) {
            return "/admin/signup";//에러나면 다시 가입 화면으로
        }

        adminService.register(admin);//문제 없으면 db에 정보 등록

        return "redirect:/";//루트 페이지로 돌아가버리기
    }

    @GetMapping("/login")//로그인 페이지 호출
    public void login() {
        log.info("login page");
    }

//    @PostMapping("/admin/login")
//    public String adminLoginPost(AdminVO adminVO) {
//        log.info("login page");
//        return "/";
//    }

//    @GetMapping("/admin/logout")
//    public void adminLogout(){log.info("logout page");}

    @GetMapping("/member/info")
    public void get(HttpSession session, Model model) throws Exception {
        String id = (String) session.getAttribute("memberId");
        log.info(id);
        MemberVO member = memberService.get(id);
        model.addAttribute("memberVO", member);

        /*
         * model.addAttribute("memberVO",
         * service.get((String)session.getAttribute("id")));
         */

    }

    //회원가입
    @GetMapping("/member/signup")
    public void signup(@ModelAttribute("member") MemberVO member, Model model) {
        log.info("회원가입 페이지 이동");
//		member.setMemberId(1L);
    }

    @PostMapping("/member/signup")
    public String signup(
            @Valid
            @ModelAttribute("member") MemberVO member,
            Errors errors) throws IOException {
        log.info(member);
        //1. 비밀번호-비밀번호 확인 일치 여부 판단
        if (!member.getMemberLoginPwd().equals(member.getMemberLoginPwd2())) {
            errors.rejectValue("memberLoginPwd", "비밀번호 불일치", "비밀번호 확인이 일치하지 않습니다.");
        }

        //2. 아이디 중복을 거르기
        if (!errors.hasFieldErrors("memberLoginId")) {
            if (memberService.get(member.getMemberLoginId()) != null) {//id중복검사
                errors.rejectValue("memberLoginId", "ID 중복", "이미 사용 중인 ID입니다.");
            }
            else if(adminService.get(member.getMemberLoginId())!=null){
                errors.rejectValue("memberLoginId", "ID 중복", "이미 사용 중인 ID입니다.");
            }
        }

        if (errors.hasErrors()) {
            return "member/signup";//에러나면 다시 가입 화면으로
        }

        memberService.signup(member);

        return "redirect:/";//루트로 되돌리기
    }

    //회원 정보 수정
    @GetMapping("/member/update")
    public void update(@ModelAttribute("member") MemberVO member, Model model) {
        log.info("update get...");
        //member.setMemberId(1L);
    }

    @PostMapping("/member/update")
    public String update(MemberVO member) throws IOException {

        log.info("update post..." + member);
        memberService.update(member);
        return "redirect:/";
    }

    //회원 정보 삭제
    @GetMapping("/member/delete")
    public String delete(HttpSession session) throws IOException {
        log.info("delete get...");
        String id = (String) session.getAttribute("memberLoginId");
        if (id == null) {
            return "redirect:/";
        }
        return "/member/deleteForm";//삭제완료 화면 미구현
    }

    @PostMapping("/member/delete")
    public String delete(String memberLoginId, HttpSession session) throws IOException {
        log.info("delete post..." + memberLoginId);
        memberService.delete(memberLoginId);
        session.invalidate();
        return "redirect:/";
    }

    //로그인
//    @GetMapping("/member/login")
//    public String memberLogin() {
//        log.info("login page");
//        return "/member/login";
//    }

//    @PostMapping("/login")
//    public String memberLoginPost(MemberVO member) throws IOException {
//        log.info("Post: " + member);
//
//        return "/home";
//    }

    //로그아웃
    @GetMapping("/logout")
    public void memberLogout() {
        log.info("logout page");
    }
}
