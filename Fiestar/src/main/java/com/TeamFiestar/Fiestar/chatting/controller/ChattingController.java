package com.TeamFiestar.Fiestar.chatting.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Select.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
import com.TeamFiestar.Fiestar.chatting.DTO.Message;
import com.TeamFiestar.Fiestar.chatting.service.ChattingService;
import com.TeamFiestar.Fiestar.member.model.DTO.Member;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ChattingController {

	private final ChattingService service;

	 @GetMapping("/chatting")
	    public String chatting(@SessionAttribute("loginMember") Member loginMember, Model model) {
	        
	        java.util.List<ChatRoom> roomList = service.selectRoomList(loginMember.getMemberNo());
	        model.addAttribute("roomList", roomList);
	        return "chatting/chatting";
	    }

	 @GetMapping("/chatting/enter")
	    @ResponseBody
	    public int chattingEnter(int targetNo, @SessionAttribute("loginMember") Member loginMember) {
	     
	        Map<String, Integer> map = new HashMap<String, Integer>();
	        
	        map.put("targetNo", targetNo);
	        map.put("loginMemberNo", loginMember.getMemberNo());
	        
	        int chattingNo = service.checkChattingNo(map);
	        
	        if(chattingNo == 0) {
	            chattingNo = service.createChattingRoom(map);
	        }
	        
	        return chattingNo;
	    }

}
