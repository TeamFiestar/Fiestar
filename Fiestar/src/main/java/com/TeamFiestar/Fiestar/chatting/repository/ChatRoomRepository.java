//package com.TeamFiestar.Fiestar.chatting.repository;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.el.lang.FunctionMapperImpl.Function;
//import org.apache.el.stream.Stream;
//import org.springframework.stereotype.Repository;
//
//import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
//
//import jakarta.annotation.PostConstruct;
//
//@Repository
//public class ChatRoomRepository {
//
//	private final Map<String, ChatRoom> chatRoomMap;
//	
//	 public ChatRoomRepository() {
//	        chatRoomMap = Collections.unmodifiableMap(
//	                Stream.of(ChatRoom.create("1번방"), ChatRoom.create("2번방"), ChatRoom.create("3번방"))
//	                      .collect(Collectors.toMap(ChatRoom::getId, Function.identity())));
//
//	        chatRooms = Collections.unmodifiableCollection(chatRoomMap.values());
//	    }
//
//	    public ChatRoom getChatRoom(String id) {
//	        return chatRoomMap.get(id);
//	    }
//	    
//	    public Collection<ChatRoom> getChatRooms() {
//	        return chatRoomMap.values();
//	    }
//	
////	@PostConstruct
////	private void init() {
////		chatRoomMap = new LinkedHashMap<>();
////	}
////	
////	public List<ChatRoom> allRooms(){
////		List<ChatRoom> result = new ArrayList<>(chatRoomMap.values());
////		Collections.reverse(result);
////		
////		return result;
////	}
////	
////	public ChatRoom findRoomId(String id) {
////		return chatRoomMap.get(id);
////	}
////	
////	public ChatRoom createChatRoom(String name) {
////		ChatRoom room = ChatRoom.create(name);
////		chatRoomMap.put(room.getRoomId(), room);
////		
////		return room;
////	}
//	
//}
