package com.TeamFiestar.Fiestar.artist.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.TeamFiestar.Fiestar.artist.model.service.ArtistService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArtistController {
	private final ArtistService service;
	
	@GetMapping("artistMember/{artistGroupTitle}")
	public String artistMember(@PathVariable("artistGroupTitle") String artistGroupTitle,
			@SessionAttribute(value="loginMember", required = false) Member loginMember, Model model) {
		Map<String, Object> map = service.artistMember(artistGroupTitle);
		model.addAttribute("map", map);
		return "artistProfile/profile";
	}
}
