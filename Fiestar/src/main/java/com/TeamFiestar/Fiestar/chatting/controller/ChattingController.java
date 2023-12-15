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

import com.TeamFiestar.Fiestar.chatting.model.dto.ChattingRoom;
import com.TeamFiestar.Fiestar.chatting.model.service.ChatService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@SessionAttributes({"loginMember","artistGroupNo","memberNickname", "liveChattingRoomNo"})
@RequiredArgsConstructor
public class ChattingController {
	
	private final ChatService service;

//	@GetMapping("chat")
//	public String chat(@SessionAttribute("loginMember") Member loginMember, Model model) {
////		model.addAttribute("memberNo", loginMember.getMemberNo());
//		return "chatting/chatting";
//	}
	
	@GetMapping("chatting/{artistGroupNo:[0-9]+}")
	public String chatting(@PathVariable("artistGroupNo") int artistGroupNo, 
			@SessionAttribute("loginMember") Member loginMember, Model model) {

//		List<String> userList = new ArrayList<>();
//		userList.add(loginMember.getMemberNickname());
//		
//		model.addAttribute("userList",userList);
		model.addAttribute("artistGroupNo",artistGroupNo);
		model.addAttribute("loginMember", loginMember);
		model.addAttribute("memberNickname",loginMember.getMemberNickname());
//		model.addAttribute("memberProfile", loginMember.getMemberProfile());
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
