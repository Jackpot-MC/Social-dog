package com.jackpot.controller;

import com.jackpot.domain.*;
import com.jackpot.service.DogService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/dog")
@Log4j
public class DogController {

    @Autowired
    private DogService service;

    //개 등록
    @GetMapping("/register") // 로직이 없어서 Test X
    public void register(@ModelAttribute("dog") DogVO dog) {
        log.info("register");
    }

    @PostMapping("/register") // POST 요청의 리턴 타입은 String
    public String register(
            @Valid @ModelAttribute("dog") DogVO dog,
            Errors errors) throws Exception {
        log.info("register: " + dog);
        if (errors.hasErrors()) {
            return "dog/register";
        }
        service.register(dog);
        return "redirect:/dog/list"; // 요청 url
    }

    //개 삭제
    @PostMapping("/remove")
    public String remove(@RequestParam("dogId") Long dogId) {
        log.info("remove..." + dogId);
        service.remove(dogId);
        return "/deleteForm";
    }

    //개 리스트 조회
    @GetMapping("/list") // View이름: notice/list (앞뒤 "/"과 확장자는 prefix, surfix가 붙여줌)
    public void list(@ModelAttribute("review") ReviewVO review, Model model) {
        log.info("list: " + review);
        model.addAttribute("list", service.getList());
    }

    //개 상세 조회
    @GetMapping({"/get", "/modify"}) //get : 상세보기, modify: 수정 화면으로 가기
    public void get(@RequestParam("dogId") Long dogId, Model model) {
        log.info("/get or modify");
        model.addAttribute("dog", service.get(dogId));
    }

    //개 정보 수정
    @PostMapping("/modify")
    public String modify(
            @Valid @ModelAttribute("dog") DogVO dog,
            Errors errors) throws Exception {
        log.info("modify:" + dog);
        if (errors.hasErrors()) {
            return "dog/modify";
        }
        return "redirect: /dog/list";
    }
}
