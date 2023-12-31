package com.TeamFiestar.Fiestar.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
//import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
@Slf4j
@SessionAttributes({"loginMember"})
public class MemberController {
	
	private final MemberService service;

	@GetMapping("login")
	public String login() {
		return "login/login";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "login/signup";
	}
	
	@PostMapping("login")
	public String login(Member inputMember, Model model) {
		
		Member loginMember = service.login(inputMember);
		
		if (loginMember == null) {
			
			return "redirect:login";
		}
		
		model.addAttribute("loginMember", loginMember);
		
		return "redirect:/";
	}
	
	@PostMapping("signup")
	public String signup(Member inputMember) {
		
		int result = service.signup(inputMember);
		
		if (result > 0) {
			return "redirect:/";
		}
		
		return "redirect:signup";
		
	}
	
	@GetMapping("login/{memberEmail}")
	public String quickLogin( @PathVariable("memberEmail") String memberEmail, Model model) {
		
		Member loginMember = service.quickLogin(memberEmail);
		
		model.addAttribute("loginMember", loginMember);
		
		return "redirect:/";
		
	
	}
	
	// 로그아웃
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}
	
	// 이메일 중복 체크
	@GetMapping("checkEmail")
	@ResponseBody
	public int checkEmail(@RequestParam("email") String email) {
		
		return service.checkEamil(email);
	}
	
	// 닉네임 중복 체크
	@GetMapping("checkNickname")
	@ResponseBody
	public int checkNickname(@RequestParam("nickname") String nickname) {
		return service.checkNickname(nickname);
	}
}
