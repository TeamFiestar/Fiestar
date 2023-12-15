package com.TeamFiestar.Fiestar.main.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

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
}
