package com.TeamFiestar.Fiestar.chatting.model.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.TeamFiestar.Fiestar.chatting.model.service.ChatService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChattingUserHandler extends TextWebSocketHandler{

	private final ChatService service;
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
//	private List<WebSocketSession> sessionLists = Collections.synchronizedList(new ArrayList<>());
	
	private Map<String, Integer> userMap = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		
		HttpSession mySession = (HttpSession)session.getAttributes().get("session");
		int myArtistGroupNo = (int)mySession.getAttribute("artistGroupNo");
		
		
		userMap.put(session.getId(), myArtistGroupNo);
		
		
		List<String> nickNameList = new ArrayList<>();
		
		for(WebSocketSession s : sessions) {
			HttpSession temp = (HttpSession) s.getAttributes().get("session");
			Member loginMember = (Member)temp.getAttribute("loginMember");
			
			int artistGroupNo = (int)temp.getAttribute("artistGroupNo");
			
			if(myArtistGroupNo == artistGroupNo) {
				log.debug("회원 닉네임 : " + loginMember.getMemberNickname());
				nickNameList.add(loginMember.getMemberNickname());
			}
		}
		
		
		
		
		for(WebSocketSession s : sessions) {
			HttpSession temp = (HttpSession) s.getAttributes().get("session");
		
			int artistGroupNo = (int)temp.getAttribute("artistGroupNo");
        	log.debug("아티스트 그룹 번호 : " + artistGroupNo);
			
        	if(myArtistGroupNo == artistGroupNo) {
        		s.sendMessage(new TextMessage(new Gson().toJson(nickNameList)));
        		log.debug("참가");
        	}
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	
	}
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		sessions.remove(session);
		
		int myArtistGroupNo = userMap.remove(session.getId());

		
		HttpSession mySession = (HttpSession)session.getAttributes().get("session");
		
		Member myLoginMember = (Member)mySession.getAttribute("loginMember");
		String myNickname = myLoginMember.getMemberNickname(); 
		
		log.debug(myArtistGroupNo + " / " + myNickname + "나감");		
		
		
		for(WebSocketSession s : sessions) {
			HttpSession temp = (HttpSession) s.getAttributes().get("session");
		
			int artistGroupNo = (int)temp.getAttribute("artistGroupNo");
			
        	if(myArtistGroupNo == artistGroupNo) {
        		s.sendMessage(new TextMessage(new Gson().toJson(myNickname)));
        		
        	}
		}
		
		
		
	}
}
