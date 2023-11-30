package com.TeamFiestar.Fiestar.chatting.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;

import jakarta.annotation.PostConstruct;

@Repository
public class ChatRoomRepository {

	private Map<String, ChatRoom> chatRoomMap;
	
	@PostConstruct
	private void init() {
		chatRoomMap = new LinkedHashMap<>();
	}
	
	public List<ChatRoom> allRooms(){
		List<ChatRoom> result = new ArrayList<>(chatRoomMap.values());
		Collections.reverse(result);
		
		return result;
	}
	
	
}
