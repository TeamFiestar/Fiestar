package com.TeamFiestar.Fiestar.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;

//@Configuration
//@EnableWebSocketMessageBroker
//@RequiredArgsConstructor
public class StompWebsocketConfig implements WebSocketMessageBrokerConfigurer{
	
//	  private final ChannelInterceptor[] stompHandler;
//	    @Override
//	    public void registerStompEndpoints(StompEndpointRegistry registry) {
//	        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*");
//	        // .withSockJS();
//	    }
//
//	    @Override
//	    public void configureMessageBroker(MessageBrokerRegistry registry) {
//	        registry.enableSimpleBroker("/queue", "/topic");
//	        registry.setApplicationDestinationPrefixes("/app");
//	    }
//
//	    @Override
//	    public void configureClientInboundChannel(ChannelRegistration registration) {
//	        registration.interceptors(stompHandler);
//	    }
	
	
//	@Override
//	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/chat")
//		.setAllowedOrigins("http://localhost:8888")
//		.withSockJS();
//	}
//	
//	@Override
//	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		registry.setApplicationDestinationPrefixes("/pub");
//		registry.enableSimpleBroker("/sub");
//	}
	
	
}
