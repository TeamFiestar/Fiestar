package com.TeamFiestar.Fiestar.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.admin.model.service.ArtistProfileService;
import com.TeamFiestar.Fiestar.shop.model.dto.ArtistGroup;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("artistprofile")
@SessionAttributes("{artistGroupTitle}")
@RequiredArgsConstructor
public class ArtistProfileController {

	private final ArtistProfileService serive;

	
	@GetMapping("profileModify")
	public String updateProfile() {
		return "artistprofile/profileModify";
	}
	
	// 아티스트 프로필 수정 화면 전환
//	@GetMapping("{artistGroupTitle}/profileModify")
//	public String updateProfile(@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("artistGroupTitle", artistGroupTitle);
//		
//
//		return "artistprofile/profileModify";
//	
//	}
//	
//	// 아티스트 프로필 수정
//	@PostMapping("{artistGroupTitle}/update")
//	public String updateProfile(@PathVariable("artistGroupTitle") String artistGroupTitle, 
//			@ModelAttribute ArtistGroup artistGroup, RedirectAttributes ra,
//			@RequestParam("artistGroupBackImg") MultipartFile artistGroupBackImg,
//			@RequestParam("artistLogo") MultipartFile artistLogo) {
//		
//		return null;
//	}

}
