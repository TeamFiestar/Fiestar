package com.TeamFiestar.Fiestar.chatting.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.TeamFiestar.Fiestar.chatting.DTO.Message;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChattingController {

	private final SimpMessagingTemplate template;
	
	@MessageMapping(value = "/chat/enter")
	public void enter(Message message) {
		template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
	}
	
	@MessageMapping(value = "/chat/message")
	public void message(Message message) {
		template.convertAndSend("/sub/chat/room" + message.getRoomId(), message);
	}
	
}
