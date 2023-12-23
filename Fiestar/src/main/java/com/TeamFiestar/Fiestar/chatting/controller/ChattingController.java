package com.TeamFiestar.Fiestar.chatting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.chatting.model.dto.ChattingRoom;
import com.TeamFiestar.Fiestar.chatting.model.service.ChatService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@SessionAttributes({"loginMember","artistGroupNo","memberNickname", "liveChattingRoomNo"})
@RequiredArgsConstructor
public class ChattingController {
	
	private final ChatService service;
	private final ArtistAdminMapper artistAdminMapper;
	private final MemberService memberService;

	
	@GetMapping("chatting/{artistGroupTitle}")
	public String chatting(@PathVariable("artistGroupTitle") String artistGroupTitle, 
			@SessionAttribute("loginMember") Member loginMember, Model model,
			RedirectAttributes ra
			) {
		
		int subscribe = memberService.checkSubscribeGroupTitle(artistGroupTitle, loginMember.getMemberNo());
		if (subscribe == 0) {
			String message = "구독 후 이용해 주십시오";
			ra.addFlashAttribute("message", message);
			return "redirect:/";
		}
		
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		String artistGroupImage = service.artistGroupImage(artistGroupTitle);
		
		model.addAttribute("artistGroupImage", artistGroupImage);
		model.addAttribute("artistGroupNo",artistGroupNo);
		model.addAttribute("loginMember", loginMember);
		model.addAttribute("memberNickname",loginMember.getMemberNickname());
		return "chatting/chatting";
	}
	
	@PostMapping("chatting/createChattingRoom")
	@ResponseBody
	public int createLiveChattingRoom(@RequestBody String artistGroupTitle, Model model) {
		int liveChattingRoomNo = service.createLiveChattingRoom(artistGroupTitle);
		model.addAttribute("liveChattingRoomNo",liveChattingRoomNo);
		return liveChattingRoomNo;
	}
	
	@GetMapping("chatting/selectLiveChattingRoom")
	@ResponseBody
	public int selectLiveChattingRoom(@RequestParam("artistGroupTitle") String artistGroupTitle, Model model) {
		int liveChattingRoomNo = service.selectLiveChattingRoom(artistGroupTitle);
		model.addAttribute("liveChattingRoomNo",liveChattingRoomNo);
		return liveChattingRoomNo;
	}
	
	
}
