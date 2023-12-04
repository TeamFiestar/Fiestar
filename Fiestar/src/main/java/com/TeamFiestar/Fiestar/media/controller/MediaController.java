package com.TeamFiestar.Fiestar.media.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.media.model.service.MediaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("media")
@RequiredArgsConstructor
public class MediaController {
	
	private final MediaService service;
	
	@GetMapping("insert")
	public String mediaInsert() {
		return "media/mediaInsert";
	}
	
	@GetMapping("list")
	public String mediaList(
			@RequestParam(name="key", required = false, defaultValue = "1") int key,
			@RequestParam(name="mediaTitle", required = false, defaultValue = "") String mediaTitle
			,Model model) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("key", key);
		map.put("mediaTitle", mediaTitle);

		List<Media> mediaList = service.selectMediaList(map);
		
		
		return "media/mediaList";
	}
	
	
	
}
