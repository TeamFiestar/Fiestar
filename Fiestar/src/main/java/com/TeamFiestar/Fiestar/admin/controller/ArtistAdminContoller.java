package com.TeamFiestar.Fiestar.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("artistAdmin")
@RequiredArgsConstructor
public class ArtistAdminContoller {
	
	@GetMapping("{artistGroupTitle}/notice")
	public String artistNotice(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		return "admin/artistNotice";
	}

}
