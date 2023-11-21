package com.jackpot.controller;

import com.jackpot.domain.AdminVO;
import com.jackpot.domain.NoticeCriteria;
import com.jackpot.service.AdminService;
import com.jackpot.service.NoticeService;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
public class AdminHomeController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	NoticeService noticeService;
		
	private static final Logger logger = LoggerFactory.getLogger(AdminHomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/admin-home", method = RequestMethod.GET)
	public String home(Locale locale,
					   @ModelAttribute("admin") AdminVO admin, @ModelAttribute("cri") NoticeCriteria cri, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("admin", adminService.get(admin.getAdminLoginId()));
		model.addAttribute("list", noticeService.getList(cri));
		
		return "admin-home";
	}
	
}
