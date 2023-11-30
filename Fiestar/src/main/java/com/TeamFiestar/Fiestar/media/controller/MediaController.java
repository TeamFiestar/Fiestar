package com.TeamFiestar.Fiestar.media.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("media")
public class MediaController {
	
	@GetMapping("insert")
	public String mediaInsert() {
		return "media/mediaInsert";
	}
	
	@GetMapping("list")
	public String mediaList() {
		return "media/mediaList";
	}
	
	
}
