package com.TeamFiestar.Fiestar.chatting.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
import com.TeamFiestar.Fiestar.chatting.DTO.Message;

public interface ChattingService {

	int insertMessage(Message msg);

	int checkChattingNo(Map<String, Integer> map);

	int createChattingRoom(Map<String, Integer> map);

	
	
}
