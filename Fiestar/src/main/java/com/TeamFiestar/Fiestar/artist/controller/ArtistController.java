package com.TeamFiestar.Fiestar.artist.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
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
			@SessionAttribute("loginMember") Member loginMember) {
		Map<String, Object> map = service.artistMember(artistGroupTitle);
		return "artistProfile/profile";
	}
}
