package com.TeamFiestar.Fiestar.chatting.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.TeamFiestar.Fiestar.chatting.model.dto.ChattingRoom;
import com.TeamFiestar.Fiestar.member.model.DTO.Member;

@Controller
@SessionAttributes({"loginMember"})
public class ChattingController {

	@GetMapping("/chat")
	public String chat() {
		return "chatting/chatting";
	}
	
	
}
