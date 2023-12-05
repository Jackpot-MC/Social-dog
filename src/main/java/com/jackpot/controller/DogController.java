package com.jackpot.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jackpot.domain.DogVO;
import com.jackpot.domain.MemberVO;
import com.jackpot.service.DogService;
import com.jackpot.service.DogServiceImpl;
import com.jackpot.service.MemberService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/dog")
@Log4j
public class DogController {

    @Autowired
    private DogService service;

    @Autowired
    private MemberService memberService;
    
    //개 등록
    @GetMapping("/register") // 로직이 없어서 Test X
    public void register(@ModelAttribute("dog") DogVO dog) {
    	log.info("register");
    }

    @PostMapping("/register") // POST 요청의 리턴 타입은 String
    public String register(
            @ModelAttribute("dog") DogVO dog, Principal principal, Model model,
            Errors errors, MultipartFile avatar) throws Exception {
        log.info("register: " + dog);
        
        String loginId = principal.getName();
        dog.setMemberId(memberService.getMemberIdByLoginId(loginId));
    	//model.addAttribute("member", memberService.getMemberIdByLoginId(loginId));
    	
        if (errors.hasErrors()) {
            return "/dog/register";
        }
        service.register(dog, avatar);
        return "redirect:/security/mypage"; // 요청 url
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
    public void list(@ModelAttribute("dogId") DogVO dog, 
    		Model model) {
        log.info("list: " + dog);
        model.addAttribute("list", service.getList());
    }

    @GetMapping("/get")
    public void get(@RequestParam("memberId") Long memberId, Model model) {
        log.info("get ");
    }
    
    @GetMapping("/modify")
    public void modify(@ModelAttribute("dog") DogVO dog, Model model) {
        
    	model.addAttribute("dog", service.getDogByDogId(dog.getDogId()));
    	log.info("dog get modify---------------------- ");
        
    }
    
    @PostMapping("/modify")
    public String modify(
    		@Valid @ModelAttribute("dog") DogVO dog, Principal principal,
    		Errors errors, Model model, MultipartFile avatar) throws Exception {
    	
    	
        log.info("dog modify post------------" + dog);
        String loginId = principal.getName();
        dog.setMemberId(memberService.getMemberIdByLoginId(loginId));
        
        if(errors.hasErrors()) {
			return "dog/modify";
		}

        service.modify(dog, avatar);
        return "redirect:/security/mypage"; // 요청 url
    }
    
	@GetMapping("/avatar/{size}/{dogName}")
	@ResponseBody
	public void avatar(@PathVariable("size") String size,
						@PathVariable("dogName") String dogName,
						HttpServletResponse response) throws IOException {
		
		File src = new File(DogServiceImpl.AVATAR_UPLOAD_DIR, dogName + ".png");
		
		if(!src.exists()) {	// 파일이 존재하지 않으면
			src = new File(DogServiceImpl.AVATAR_UPLOAD_DIR, "unknown.png");
		}
		
		response.setHeader("Content-Type", "image/png");
		
		if(size.equals("sm")) {
			Thumbnails.of(src)
					.size(25, 25)
					.toOutputStream(response.getOutputStream());
		} else {
			Thumbnails.of(src)
			.size(50, 50)
			.toOutputStream(response.getOutputStream());
		}
	}
    
}
