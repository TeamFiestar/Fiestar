package com.TeamFiestar.Fiestar.chatting.model.handler;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.TeamFiestar.Fiestar.chatting.model.dto.ChattingRoom;
import com.TeamFiestar.Fiestar.chatting.model.dto.Message;
import com.TeamFiestar.Fiestar.chatting.model.service.ChatService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;


//import java.util.Set;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
//import java.util.HashSet;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChattingLiveHandler extends TextWebSocketHandler{

	private final ChatService service;
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			sessions.add(session);
	}
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("내용 : " + message.getPayload());
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Message msg = objectMapper.readValue(message.getPayload(), Message.class);
		
		System.out.println(msg);
		int result = service.insertLiveMessage(msg);
		if(result > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd hh:mm");
            
            msg.setMessageSendTime(sdf.format(new Date()));
            
            log.info("세션 : " + sessions.size());
            
            for(WebSocketSession s : sessions) {
            	HttpSession temp = (HttpSession)s.getAttributes().get("session");
            	
            	int liveChattingRoomNo = (int) temp.getAttribute("liveChattingRoomNo");
            	log.debug("채팅방 번호 : " + liveChattingRoomNo);
            	
            	
            	if(liveChattingRoomNo == msg.getLiveChattingRoomNo()) {
            		s.sendMessage(new TextMessage(new Gson().toJson(msg)));
            		log.debug("채팅 보내짐");
            	}
            }

		}
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}
	
}
