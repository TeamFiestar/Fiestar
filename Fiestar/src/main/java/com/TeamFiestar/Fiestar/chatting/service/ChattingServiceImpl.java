package com.TeamFiestar.Fiestar.chatting.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
import com.TeamFiestar.Fiestar.chatting.DTO.Message;

@Service
public class ChattingServiceImpl implements ChattingService{

	@Override
	public int insertMessage(Message msg) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int checkChattingNo(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int createChattingRoom(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
