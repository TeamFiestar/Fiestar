package com.TeamFiestar.Fiestar.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public String admin() {
		return "admin/artistNotice";
	}
}
