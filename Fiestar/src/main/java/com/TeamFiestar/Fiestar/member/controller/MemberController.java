package com.TeamFiestar.Fiestar.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class MemberController {

	@GetMapping("login")
	public String login() {
		return "login/login";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "login/signup";
	}
	
	@PostMapping("login")
	public String login(String email, String pw) {
		
		return "redirect:/";
	}
	
}
