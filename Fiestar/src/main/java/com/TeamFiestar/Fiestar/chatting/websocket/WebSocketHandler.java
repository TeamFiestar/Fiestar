package com.TeamFiestar.Fiestar.chatting.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
import com.TeamFiestar.Fiestar.chatting.DTO.Message;
import com.TeamFiestar.Fiestar.chatting.service.ChattingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler{

	private final ObjectMapper objectMapper;
	private final ChattingService service;
	private ChatRoom chatRoom;
	private String roomId;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("웹소켓 연결");
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("{}", payload);
		
		Message chatMessage = objectMapper.readValue(payload, Message.class);
		roomId = chatMessage.getRoomId();
		
		chatRoom = service.findRoomId(chatMessage.getRoomId());
		chatRoom.handlerActions(session, chatMessage, service);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		chatRoom.getSessions().remove(session);
		service.deleteRoom(roomId);
		log.info("웹소켓 닫힘");
	}
}
