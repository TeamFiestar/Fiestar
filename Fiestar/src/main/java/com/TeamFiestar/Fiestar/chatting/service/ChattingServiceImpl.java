package com.TeamFiestar.Fiestar.chatting.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;

@Service
public class ChattingServiceImpl implements ChattingService{

	@Override
	public ChatRoom createRoom(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteRoom(String name) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public <T> Object sendMessage(WebSocketSession session, T message) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ChatRoom findRoomId(String roomId) {
		// TODO Auto-generated method stub
		return null;
	}
}
