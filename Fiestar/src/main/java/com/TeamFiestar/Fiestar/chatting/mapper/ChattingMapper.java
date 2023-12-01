package com.TeamFiestar.Fiestar.chatting.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.chatting.DTO.ChatRoom;
import com.TeamFiestar.Fiestar.chatting.DTO.Message;

@Mapper
public interface ChattingMapper {

	int insertMessage(Message msg);

	int checkChattingNo(Map<String, Integer> map);

	int createChattingRoom(Map<String, Integer> map);

	List<ChatRoom> selectRoomList(int memberNo);
	
}
