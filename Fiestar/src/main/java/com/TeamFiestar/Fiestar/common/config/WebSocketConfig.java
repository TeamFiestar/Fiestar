package com.TeamFiestar.Fiestar.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.TeamFiestar.Fiestar.chatting.websocket.ChattingWebsocketHandler;
import com.TeamFiestar.Fiestar.main.model.websocket.TestWebsocketHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket // 웹소캣 활성화
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{
	
	private final ChattingWebsocketHandler chattingWebsocketHandler;
	private final TestWebsocketHandler testWebsocketHandler;
	
	private final HandshakeInterceptor handshakeInterceptor;
	// 웹소캣 핸들러 등록
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		// addHandler : 웹소캣 핸들러 객체 지정, 매핑될 주소 지정
		// setAllowedOriginPatterns : 웹소캣 요청 허용 주소 패턴 작성
		// withSockJS : SockJS 지원 + 브라우저 호환성 증가
		// addInterceptors : 요청을 핸들러로 전달할 때 가로채기 역할을 할 객체 추가
		registry.addHandler(testWebsocketHandler, "/testSock")
				.setAllowedOriginPatterns("http://localhost/","http://127.0.0.1/","http://192.168.10.18/")
				.withSockJS();
		registry.addHandler(chattingWebsocketHandler, "/chattingSock")
		.addInterceptors(handshakeInterceptor)
		.setAllowedOriginPatterns("http://localhost/","http://127.0.0.1/","http://192.168.10.18/")
		.withSockJS();
		
	}
}