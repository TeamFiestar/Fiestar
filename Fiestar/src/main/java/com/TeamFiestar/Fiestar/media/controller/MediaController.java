package com.TeamFiestar.Fiestar.media.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.media.model.service.MediaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("media")
@Slf4j
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
		
		model.addAttribute("mediaList", mediaList);
		model.addAttribute("key", key);
		
		log.debug("mediaTitle : " + mediaList);
		
		return "media/mediaList";
	}
	
	@PostMapping("insert")
	public String mediaInsert(
			Media inserMedia) {
		
		int result = service.insertMedia(inserMedia);
		
		return "redirect:list";
	}
	
	
	
}
