package com.TeamFiestar.Fiestar.main.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.main.model.service.MainService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MainService service;
	
	@RequestMapping("/")
	public String mainPage(@SessionAttribute(value="loginMember", required = false) Member loginMember, Model model) {
		Map<String, Object> map = service.main();
		model.addAttribute("map", map);
		if(loginMember != null) {
			Map<String, Object> mapLogin = service.mainLogin(loginMember.getMemberNo());
			model.addAttribute("mapLogin", mapLogin);
			return "common/main";
		}
		return "common/main";
	}
	
	
	/** 로그인 하지않고 로그인 전용 페이지 접근 시
	 * @param ra
	 * @return redirect to mainPage
	 * \
	 */
	@GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		ra.addFlashAttribute("message","로그인 후 이용해주세요.");
		return "redirect:/";
	}
	
	/** 관리자 권한이 없는 상태에서 관리자 페이지 접근 시
	 * @param ra
	 * @return
	 */
	@GetMapping("adminError")
	public String adminError(RedirectAttributes ra) {
		ra.addFlashAttribute("message","정상 접근이 아닙니다");
		return "redirect:/";
	}
	
}
