package com.TeamFiestar.Fiestar.chatting.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.TeamFiestar.Fiestar.chatting.DTO.Message;
import com.TeamFiestar.Fiestar.chatting.service.ChattingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChattingWebsocketHandler extends TextWebSocketHandler{
	
	private final ChattingService service;
	
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());

	 @Override
	 public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		 sessions.add(session);
	 }

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("전달받은 내용 : " + message.getPayload());
		
		ObjectMapper objectMapper = new ObjectMapper();
        
        Message msg = objectMapper.readValue( message.getPayload(), Message.class);
        // Message 객체 확인
        System.out.println(msg); 
        
        // DB 삽입 서비스 호출
        int result = service.insertMessage(msg);
        
        if(result > 0 ) {
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
            msg.setSendTime(sdf.format(new Date()) );
            
            log.info("세션 수 : " + sessions.size());
            
            // 전역변수로 선언된 sessions에는 접속중인 모든 회원의 세션 정보가 담겨 있음
            for(WebSocketSession s : sessions) {
                // WebSocketSession은 HttpSession의 속성을 가로채서 똑같이 가지고 있기 때문에
                // 회원 정보를 나타내는 loginMember도 가지고 있음.
                
                // 로그인된 회원 정보 중 회원 번호 얻어오기
//            	log.info(s.toString());
//            	log.info(s.getAttributes().toString());
//            	log.info(s.getAttributes().get("session").toString());
            	
            	HttpSession temp = (HttpSession)s.getAttributes().get("session");
//            	log.info(temp.getAttribute("loginMember").toString());
            	
                int loginMemberNo = ((Member)temp.getAttribute("loginMember")).getMemberNo();
                log.debug("loginMemberNo : " + loginMemberNo);
                
                
            }
        }
	
	}
	 
}
