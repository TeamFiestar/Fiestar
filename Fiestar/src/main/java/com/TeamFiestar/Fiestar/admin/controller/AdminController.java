package com.TeamFiestar.Fiestar.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TeamFiestar.Fiestar.admin.model.service.adminService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

	private final adminService service;
	
	@GetMapping("selectMember")
	public String member(Model model) {
		
		List<Member> memberList = service.selectMember();
		model.addAttribute("memberList",memberList);
		return "admin/selectMember";
	}
	
	
	@GetMapping("notice")
	public String admin() {
		return "admin/artistNotice";
	}
	
	@GetMapping("register")
	public String register() {
		return "admin/goods";
	}
}
