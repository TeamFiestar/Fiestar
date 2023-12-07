package com.TeamFiestar.Fiestar.media.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.ApplicationScope;

import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.media.model.service.MediaService;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MediaController {
	
	private final MediaService service;
	
	
	@GetMapping("{artistGroupTitle}/media/insert")
	public String mediaInsert(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		

		
		return "media/mediaInsert";
	}
	
	@GetMapping("{artistGroupTitle}/media/list")
	public String mediaList(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@RequestParam(name="key", required = false, defaultValue = "1") int key,
			@RequestParam(name="mediaTitle", required = false, defaultValue = "") String mediaTitle
			,Model model) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("key", key);
		map.put("mediaTitle", mediaTitle);

		List<Media> mediaList = service.selectMediaList(map);
		
		model.addAttribute("mediaList", mediaList);
		model.addAttribute("key", key);
		
		return "media/mediaList";
	}
	
	@GetMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/detail")
	public String mediaDetail(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model) {
		
		Media mediaDetail = service.mediaDetail(mediaNo);
		
		model.addAttribute("mediaDetail",mediaDetail);
		
		return "media/mediaDetail";
	}
	
	@PostMapping("{artistGroupTitle}/media/insert")
	public String mediaInsert(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			Media inserMedia) {
		
		int result = service.insertMedia(inserMedia);
		
		return "redirect:list";
	}
	
	
}
