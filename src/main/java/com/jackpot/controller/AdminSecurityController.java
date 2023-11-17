package com.jackpot.controller;

import com.jackpot.domain.AdminVO;
import com.jackpot.service.AdminService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminSecurityController {

    @Autowired
    AdminService adminService;

    @GetMapping("/login")//로그인 페이지 호출
    public void login(){
        log.info("login page");
    }

    @GetMapping("/signup")//회원가입 페이지 호출
    public void signup(@ModelAttribute("admin")AdminVO admin){

    }

    @PostMapping("/signup")
    public String signup(//회원가입
            @Valid @ModelAttribute("admin") AdminVO admin,
            Errors errors) throws IOException{
        //1. 비밀번호-비밀번호 확인 일치 여부 판단
        if(!admin.getAdminLoginPwd().equals(admin.getAdminLoginPwd2())){
            errors.rejectValue("password2", "비밀번호 불일치", "비밀번호 확인이 일치하지 않습니다.");
        }

        //2. 아이디 중복을 거르기
        if(!errors.hasFieldErrors("adminLoginId")){
            if(adminService.get(admin.getAdminLoginId()) != null){//id중복검사
                errors.rejectValue("adminLoginId", "ID 중복", "이미 사용중인 ID입니다.");
            }
        }

        if(errors.hasErrors()){
            return "admin/signup";//에러나면 다시 로그인 화면으로
        }

        adminService.register(admin);//문제 없으면 db에 정보 등록

        return "redirect:/";//루트 페이지로 돌아가버리기
    }

    @GetMapping("/profile")
    public void profile(){}


}
