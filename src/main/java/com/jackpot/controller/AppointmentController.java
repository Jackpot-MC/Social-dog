package com.jackpot.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jackpot.domain.AppointmentCriteria;
import com.jackpot.domain.AppointmentPageDTO;
import com.jackpot.domain.AppointmentVO;
import com.jackpot.domain.MemberVO;
import com.jackpot.domain.PlaceVO;
import com.jackpot.domain.ReviewCriteria;
import com.jackpot.service.AppointmentService;
import com.jackpot.service.DogService;
import com.jackpot.service.DogServiceImpl;
import com.jackpot.service.MemberService;
import com.jackpot.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Log4j
/* @RequestMapping("/appointment") */
@AllArgsConstructor
public class AppointmentController {

	private AppointmentService service;

	private MemberService memberService;

	private NoticeService noticeService;

	private DogService dogService;

	@ModelAttribute("searchTypes")
	public Map<String, String> searchTypes() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "-- 검색대상선택 --");
		map.put("N", "약속 이름");
		map.put("H", "주최자");
		map.put("L", "약속 장소");
		map.put("NH", "약속 이름 + 주최자");
		map.put("NL", "약속 이름 + 약속 장소");
		map.put("NHL", "약속 이름 + 주최자 + 약속 장소");

		return map;
	}

	@GetMapping("/appointment/list") // View이름: appointment/list (앞뒤 "/"과 확장자는 prefix, surfix가 붙여줌)
	public void list(@ModelAttribute("cri") AppointmentCriteria cri, Principal principal, Model model)
			throws IOException {
		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));

		int total = service.getTotal(cri);
		log.info("total: " + total);

		model.addAttribute("pageMaker", new AppointmentPageDTO(cri, total));

		String loginId = principal.getName();

		log.info("getMemberId");
		model.addAttribute("memberId", service.getMemberId(loginId));

		model.addAttribute("memberList", memberService.getList());
		log.info("memberList................" + memberService.getList());

		MemberVO member = memberService.get(principal.getName());
		model.addAttribute("dogList", service.getDogList());
	}

	@GetMapping("/appointment/register") // 로직이 없어서 Test X
	public void register(@ModelAttribute("appointment") AppointmentVO appointment,
			@ModelAttribute("place") PlaceVO place, Principal principal, Model model) {
		String loginId = principal.getName();

		Long hostId = service.getMemberId(loginId);

		log.info("setHostId" + hostId);
		model.addAttribute("hostId", hostId);

		log.info("register" + appointment);

	}

	@PostMapping("/appointment/register") // POST 요청의 리턴 타입은 String
	public String register(@Valid @ModelAttribute("appointment") AppointmentVO appointment, Errors errors,
			@Valid @ModelAttribute("place") PlaceVO place, Errors errors2, RedirectAttributes rttr) throws Exception {

		log.info("register: " + appointment);
		if (errors.hasErrors()) {
			return "appointment/register";
		}

		/* service.register(appointment, place); */
		service.register(appointment);

		/* service.registerPlace(place); */

		rttr.addFlashAttribute("result", appointment.getAppointmentId());

		return "redirect:/walk"; // 요청 url
	}

	@GetMapping({ "/appointment/get", "/appointment/modify", "/appointment/my_appointment_get" }) // get : 상세보기, modify:
																									// 수정 화면으로 가기
	public void get(@RequestParam("appointmentId") Long appointmentId, @ModelAttribute("cri") AppointmentCriteria cri,
			Principal principal, Model model) throws IOException {

		String loginId = principal.getName();

		Long memberId = service.getMemberId(loginId);

		log.info("/get or modify");
		model.addAttribute("appointment", service.get(appointmentId));

		log.info("getParticipantList");
		model.addAttribute("list", service.getParticipantList(appointmentId));

		log.info("getMemberId");
		model.addAttribute("memberId", memberId);

		log.info("checkAttendance");
		model.addAttribute("checkAttendance", service.checkAttendance(appointmentId, memberId));

		MemberVO member = memberService.get(principal.getName());
		model.addAttribute("member", member); 
		model.addAttribute("dogList", dogService.getListByMemberId(member.getMemberId()));		

		/*
		 * model.addAttribute("memberList", memberService.getList());
		 * model.addAttribute("dogList", service.getDogList());
		 */
	}

	@PostMapping("/appointment/modify")
	public String modify(@Valid @ModelAttribute("appointment") AppointmentVO appointment, Errors errors,
			RedirectAttributes rttr) throws Exception {
		log.info("modify:" + appointment);
		if (errors.hasErrors()) {
			log.info(errors);
			return "appointment/modify";
		}
		if (service.modify(appointment)) {
			// Flash --> 1회성
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/appointment/get?appointmentId=" + appointment.getAppointmentId();
	}

	@PostMapping("/appointment/remove")
	public String remove(@RequestParam("appointmentId") Long appointmentId,
			@ModelAttribute("cri") AppointmentCriteria cri, RedirectAttributes rttr) {
		log.info("remove..." + appointmentId);

		service.remove(appointmentId);

		return "redirect:" + cri.getLink("/walk"); // 요청 url
	}

	@PostMapping("/appointment/attend")
	public String attend(@RequestParam("appointmentId") Long appointmentId, @RequestParam("memberId") Long memberId,
			Model model) {

		model.addAttribute("memberId", memberId);
		model.addAttribute("appointmentId", appointmentId);

		service.attend(appointmentId, memberId);

		return "redirect:/appointment/get?appointmentId=" + appointmentId;
	}

	@GetMapping("/appointment/attend_appointment") // View이름: appointment/list (앞뒤 "/"과 확장자는 prefix, surfix가 붙여줌)
	public void attend_appointment(@ModelAttribute("cri") AppointmentCriteria cri, Model model) {
		log.info("list: " + cri);
		model.addAttribute("list", service.getList(cri));

		int total = service.getTotal(cri);
		log.info("total: " + total);

		model.addAttribute("pageMaker", new AppointmentPageDTO(cri, total));
	}

	@GetMapping("/appointment/my_appointment") // View이름: appointment/list (앞뒤 "/"과 확장자는 prefix, surfix가 붙여줌)
	public void my_appointment(Principal principal, Model model) throws IOException {
		String loginId = principal.getName();
		Long memberId = service.getMemberId(loginId);
		log.info("my_appointment: " + memberId);

		model.addAttribute("my_appointment_list", service.getMyList(memberId));

		MemberVO member = memberService.get(principal.getName());
		model.addAttribute("member", member);
		model.addAttribute("dogList", dogService.getListByMemberId(member.getMemberId()));
		
		model.addAttribute("memberList", memberService.getList());
		model.addAttribute("dogListAll", service.getDogList());

	}

	@PostMapping("/appointment/absent")
	public String absent(@RequestParam("appointmentId") Long appointmentId, @RequestParam("memberId") Long memberId,
			Model model) {

		model.addAttribute("memberId", memberId);
		model.addAttribute("appointmentId", appointmentId);

		service.absent(appointmentId, memberId);

		return "redirect:/appointment/get?appointmentId=" + appointmentId;
	}

	@GetMapping("/walk")
	public void walk(@ModelAttribute("mode") String mode, @ModelAttribute("cri") ReviewCriteria cri,
			@ModelAttribute("member") MemberVO member, Model model) {

		model.addAttribute("member", memberService.get(member.getMemberAddress()));

	}

	@GetMapping("/appointment/walk_banner")
	public void walk_banner() {

	}
}
