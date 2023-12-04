package com.TeamFiestar.Fiestar.chatting.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.TeamFiestar.Fiestar.chatting.model.dto.ChattingRoom;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Controller
@SessionAttributes({"loginMember"})
public class ChattingController {

	@GetMapping("chat")
	public String chat(@SessionAttribute("loginMember") Member loginMember, Model model) {
//		model.addAttribute("memberNo", loginMember.getMemberNo());
		return "chatting/chatting";
	}
	
	@GetMapping("chatting/{artistGroupNo:[0-9]+}")
	public String chatting(@PathVariable("artistGroupNo") int artistGroupNo, @SessionAttribute("loginMember") Member loginMember, Model model) {

		model.addAttribute("artistGroupNo",artistGroupNo);
		return "chatting/chatting";
	}
	
}
