package com.TeamFiestar.Fiestar.artist.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.artist.model.dto.Artist;
import com.TeamFiestar.Fiestar.artist.model.service.ArtistService;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.notice.dto.ArtistGroupNotice;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("artistMember")
public class ArtistController {
	private final ArtistService service;
	
	@GetMapping("{artistGroupTitle}")
	public String artistMember(@PathVariable("artistGroupTitle") String artistGroupTitle,
			@SessionAttribute(value="loginMember", required = false) Member loginMember, Model model) {
		if(loginMember == null) {
			Map<String, Object> map = service.artistMember(artistGroupTitle);
			model.addAttribute("map", map);
			return "artistProfile/profile";
		}else {
			Map<String, Object> map = service.loginArtistMember(loginMember.getMemberNo(), artistGroupTitle);
			model.addAttribute("map", map);
			return "artistProfile/profile";			
		}
	}
	
	
	@PostMapping("{artistGroupTitle}")
	public String subscribe(@PathVariable("artistGroupTitle") String artistGroupTitle,
			@SessionAttribute(value="loginMember", required = true) Member loginMember, RedirectAttributes ra, Model model) {
		int result = service.subscribe(loginMember.getMemberNo(),artistGroupTitle);
		
		if(result > 0) {
			ra.addFlashAttribute("message", "구독 성공!!");
			return "redirect:/{artistGroupTitle}/feed";
		}else {
			ra.addFlashAttribute("message", "이미 구독중입니다.");
			return "redirect:/artistMember/{artistGroupTitle}";
		}
 
	}
	
	@GetMapping("{artistGroupTitle}/update")
	public String update(@PathVariable("artistGroupTitle") String artistGroupTitle,
			@SessionAttribute(value="loginMember", required = true) Member loginMember, Model model, RedirectAttributes ra) {
			
//			int result = service.update(artistGroupTitle, loginMember.getMemberNo());
		
//			if(result == 1) {
				Map<String, Object> map = service.artist(loginMember.getMemberNo());
//			}
			model.addAttribute("map", map);
	
		return "artistProfile/profileModify";
	}
	
	@PostMapping("{artistGroupTitle}/update")
	public String artistUpdate(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@RequestParam("artistGroupMain") MultipartFile artistGroupMain,
			@RequestParam("artistGroupLogo") MultipartFile artistGroupLogo,
			@RequestParam("artistGroupIntroduce") String artistGroupIntroduce,
			@RequestParam("artistProfileImg") List<MultipartFile> artistProfileImg,
			@RequestParam("Name") List<String> Name,
			@RequestParam("email") List<String> email, 
			@SessionAttribute("loginMember") Member loginMember,
			ArtistGroup1 artistGroup, 
			RedirectAttributes ra
			) throws IllegalStateException, IOException {
		int adminNo = loginMember.getMemberNo();
		int result = service.artistUpdate(artistGroupTitle, artistGroupMain,
				artistGroupLogo, artistGroupIntroduce, artistProfileImg, Name, email,
				 artistGroup, adminNo);
		
		if(result > 0) {
			ra.addFlashAttribute("message", "변경 성공!!");
			return "redirect:/artistMember/{artistGroupTitle}";
		}else {
			ra.addFlashAttribute("message", "변경 실패");
			return "redirect:/artistMember/{artistGroupTitle}/update";
		}
	}
	
	
	
	@GetMapping("{artistGroupTitle}/notice")
	public String artistNotice(@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		Map<String, Object> map = service.artistNotice(artistGroupTitle);
		
		model.addAttribute("map", map);
		model.addAttribute("artistGroupTitle", artistGroupTitle);
		return "artist/artistNotice";
	}
	
	
	@GetMapping("{artistGroupTitle}/notice/{artistGroupNoticeNo}")
	public String artistNoticeDetail(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model,
			@SessionAttribute(value="loginMember", required = true) Member loginMember,
			@PathVariable("artistGroupNoticeNo") int artistGroupNoticeNo) {
		List<ArtistGroupNotice> artistGroupNoticeList = service.artistNoticeDetail(artistGroupTitle, artistGroupNoticeNo);
		
		model.addAttribute("artistGroupNoticeList", artistGroupNoticeList);
		
		return "artistHomePage/artistNoticeDetail";
	
	}
	
}
