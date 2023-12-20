package com.TeamFiestar.Fiestar.notice.cotroller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.notice.dto.ArtistGroupNotice;
import com.TeamFiestar.Fiestar.notice.service.ArtistGroupNoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArtistGroupNoticeController {

	private final ArtistGroupNoticeService service;
	
	@GetMapping("{artistGroupTitle}/notice")
	public String notice(@PathVariable("artistGroupTitle") String artistGroupTitle, Model model,
						@SessionAttribute(value="loginMember", required = true) Member loginMember, 
						@RequestParam(value="cp", required = false, defaultValue = "1") int cp) {
		Map<String, Object> map = service.notice(artistGroupTitle, cp);
		
		model.addAttribute("artistGroupTitle", artistGroupTitle);
		model.addAttribute("map", map);
		return "artistHomePage/artistNotice";
	}
	
	
	@GetMapping("{artistGroupTitle}/notice/{artistGroupNoticeNo}")
	public String noticeDetail(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model,
			@SessionAttribute(value="loginMember", required = true) Member loginMember,
			@PathVariable("artistGroupNoticeNo") int artistGroupNoticeNo) {
		List<ArtistGroupNotice> artistGroupNoticeList = service.noticeDetail(artistGroupTitle, artistGroupNoticeNo);
		
		model.addAttribute("artistGroupNoticeList", artistGroupNoticeList);
		
		return "artistHomePage/artistNoticeDetail";
	
	}
	
}
