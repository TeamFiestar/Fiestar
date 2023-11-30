package com.TeamFiestar.Fiestar.chatting.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;

public interface ChattingService {

	ChatRoom createRoom(String name);

	void deleteRoom(String name);

	<T> Object sendMessage(WebSocketSession session, T message);

	ChatRoom findRoomId(String roomId);

	
}
