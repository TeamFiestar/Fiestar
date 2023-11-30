package com.TeamFiestar.Fiestar.chatting.DTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatRoom {
	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	 public static ChatRoom create(String name){
		 ChatRoom room = new ChatRoom();

	        room.roomId = UUID.randomUUID().toString();
	        room.name = name;
	        return room;
	    }
	
}
