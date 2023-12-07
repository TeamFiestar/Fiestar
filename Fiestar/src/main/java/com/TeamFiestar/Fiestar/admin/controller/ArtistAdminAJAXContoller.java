package com.TeamFiestar.Fiestar.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.service.ArtistAdminAJAXService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("artistAdmin")
@RequiredArgsConstructor
public class ArtistAdminAJAXContoller {
	
	private final ArtistAdminAJAXService service;
	
	@GetMapping("selectNotice")
	@ResponseBody
	public ArtistNotice selectArtistNotice(@RequestParam int artistGroupNo) {
		
		return service.selectArtistNotice(artistGroupNo);
	}

	
	
}
