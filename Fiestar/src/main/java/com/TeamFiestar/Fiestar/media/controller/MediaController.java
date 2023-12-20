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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.ApplicationScope;

import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.media.model.service.MediaService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

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
	
	
	@GetMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/update")
	public String updateMediaDetail(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		Media media = service.updateMediaDetail(mediaNo);
		model.addAttribute("media",media);
		
		return "media/mediaUpdate";
	}
	
	// 미디어 리스트 조회
	@GetMapping("{artistGroupTitle}/media/list")
	public String mediaList(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@RequestParam(name="key", required = false, defaultValue = "1") int key,
			@RequestParam(name="mediaTitle", required = false, defaultValue = "") String mediaTitle
			,Model model) {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("key", key);
		map.put("mediaTitle", mediaTitle);
		map.put("artistGroupTitle", artistGroupTitle);

		List<Media> mediaList = service.selectMediaList(map);
		
		model.addAttribute("mediaList", mediaList);
		model.addAttribute("key", key);
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		return "media/mediaList";
	}
	
	// 미디어 상세 조회
	@GetMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/detail")
	public String mediaDetail(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		
		Map<String, Object> map = new HashMap<>();
		if (loginMember != null) {
			int memberNo = loginMember.getMemberNo();
			map.put("memberNo", memberNo);
			
		}
		map.put("mediaNo", mediaNo);
		
		Media mediaDetail = service.mediaDetail(map);
		
		model.addAttribute("mediaDetail",mediaDetail);
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		return "media/mediaDetail";
	}
	
	// 라이브 이동 
	@GetMapping("{artistGroupTitle}/media/live")
	public String mediaLive(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		return "media/mediaLive";
	}
	
	
	
	
	
	
	
	
	// 미디어 삽입
	@PostMapping("{artistGroupTitle}/media/insert")
	public String mediaInsert(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			Media inserMedia) {
		
		int result = service.insertMedia(inserMedia);
		
		return "redirect:list";
	}
	
	// 미디어 삭제
	@PostMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/delete")
	public String mediaDelete(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model) {
		
		int result = service.deleteMedia(mediaNo);
		
		return "redirect:../list";
	}
	
	// 미디어 업데이트
	@PostMapping("{artistGroupTitle}/media/{mediaNo:[0-9]+}/update")
	public String mediaUpdate(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("mediaNo") int mediaNo, Model model,
			Media updateMedia) {
		
		int result = service.updateMedia(updateMedia);
		
		return "redirect:detail";
	}
	
	
}
