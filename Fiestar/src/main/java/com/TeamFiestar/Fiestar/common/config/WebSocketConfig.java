
//package com.TeamFiestar.Fiestar.common.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//import com.TeamFiestar.Fiestar.chatting.model.handler.ChattingWebsocketHandler;
//import com.TeamFiestar.Fiestar.common.interceptor.ChattingHandShakeInterceptor;
//import com.TeamFiestar.Fiestar.main.model.websocket.TestWebsocketHandler;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSocket
//@RequiredArgsConstructor
//public class WebSocketConfig implements WebSocketConfigurer{
//
//	private final ChattingWebsocketHandler chattingWebsocketHandler;
//	private final TestWebsocketHandler testWebsocketHandler;
//	
//	private final ChattingHandShakeInterceptor chattingHandShakeInterceptor;
//	
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		
//		registry.addHandler(testWebsocketHandler, "/testSock")
//		.setAllowedOriginPatterns("https://localhost/","https://127.0.0.1/")
//		.addInterceptors(chattingHandShakeInterceptor)
//		.withSockJS();
//		
//		registry.addHandler(chattingWebsocketHandler, "/chatSock")
//			.setAllowedOriginPatterns("https://localhost/","https://127.0.0.1/")
//			.addInterceptors(chattingHandShakeInterceptor)
//			.withSockJS();
//	}
//	
//}

package com.TeamFiestar.Fiestar.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.TeamFiestar.Fiestar.chatting.model.handler.ChattingUserHandler;
import com.TeamFiestar.Fiestar.chatting.model.handler.ChattingWebsocketHandler;
import com.TeamFiestar.Fiestar.common.interceptor.ChattingHandShakeInterceptor;
import com.TeamFiestar.Fiestar.main.model.websocket.TestWebsocketHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{

	private final ChattingWebsocketHandler chattingWebsocketHandler;
	private final TestWebsocketHandler testWebsocketHandler;
	private final ChattingUserHandler chattingUserHandler;
	
	private final ChattingHandShakeInterceptor chattingHandShakeInterceptor;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		registry.addHandler(testWebsocketHandler, "/testSock")
		.setAllowedOriginPatterns("https://localhost/","https://127.0.0.1/")
		.addInterceptors(chattingHandShakeInterceptor)
		.withSockJS();
		
		registry.addHandler(chattingWebsocketHandler, "/chatSock")
			.setAllowedOriginPatterns("https://localhost/","https://127.0.0.1/","http://192.168.10.18/")
			.addInterceptors(chattingHandShakeInterceptor)
			.withSockJS();
		
		registry.addHandler(chattingUserHandler, "/userSock")
		.setAllowedOriginPatterns("https://localhost/","https://127.0.0.1/","http://192.168.10.18/")
		.addInterceptors(chattingHandShakeInterceptor)
		.withSockJS();
		
	}
	
}

