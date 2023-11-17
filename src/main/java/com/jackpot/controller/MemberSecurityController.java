package com.jackpot.controller;

import com.jackpot.service.AdminService;
import com.jackpot.service.MemberService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberSecurityController {

    @Autowired
    MemberService memberService;



}
