package com.jackpot.controller;

import com.jackpot.service.DogService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dog")
@Log4j
public class DogController {

    @Autowired
    private DogService dogService;


}
