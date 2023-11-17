package com.jackpot.controller;

import com.jackpot.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    AdminService adminService;


}
