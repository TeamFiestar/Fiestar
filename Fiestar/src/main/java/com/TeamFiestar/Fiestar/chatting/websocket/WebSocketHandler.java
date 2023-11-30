package com.TeamFiestar.Fiestar.chatting.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
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
	
	public class WebSocketConfig implements WebSocketConfigurer {

	    private final ChatHandler chatHandler;

	    @Override
	    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
	        registry.addHandler(chatHandler, "/ws/chat")
	        .setAllowedOrigins("http://localhost:8080")
	        .withSockJS();
	    }
	    //.withSockJS() 추가
	    //setAllowedOrigins("*")에서 *라는 와일드 카드를 사용하면
	    //보안상의 문제로 전체를 허용하는 것보다 직접 하나씩 지정해주어야 한다고 한다.
	}
}
