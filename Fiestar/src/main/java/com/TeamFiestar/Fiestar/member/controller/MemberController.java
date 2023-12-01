package com.TeamFiestar.Fiestar.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.TeamFiestar.Fiestar.member.model.DTO.Member;
import com.TeamFiestar.Fiestar.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequiredArgsConstructor
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
	public String login(Member inputMember) {
		
		Member loginMember = service.login(inputMember);
		
		if (loginMember == null) {
			
			return "redirect:login";
		}
		
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
	
}
