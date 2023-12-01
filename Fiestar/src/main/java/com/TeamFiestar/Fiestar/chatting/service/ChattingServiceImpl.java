package com.TeamFiestar.Fiestar.chatting.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.WebSocketSession;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
import com.TeamFiestar.Fiestar.chatting.DTO.Message;
import com.TeamFiestar.Fiestar.chatting.mapper.ChattingMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChattingServiceImpl implements ChattingService{

	private final ChattingMapper mapper;
	
	@Override
	public int insertMessage(Message msg) {
		return mapper.insertMessage(msg);
	}
	
	@Override
	public int checkChattingNo(Map<String, Integer> map) {
        return mapper.checkChattingNo(map);
	}
	
	/** 채팅방 생성
	 *
	 */
	@Override
	public int createChattingRoom(Map<String, Integer> map) {
		int result = mapper.createChattingRoom(map);

        return result <= 0 ? 0 : (int)map.get("chattingNo"); 
	}
	
	/** 채팅방 목록 조회
	 *
	 */
	@Override
	public List<ChatRoom> selectRoomList(int memberNo) {
		return mapper.selectRoomList(memberNo);
	}
	
}
