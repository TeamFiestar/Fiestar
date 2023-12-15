package com.TeamFiestar.Fiestar.chatting.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.chatting.model.dto.Message;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface ChattingMapper {

	int insertMessage(Message msg);

	int selectMember(int i);

	int insertLiveMessage(Message msg);

	int selectLiveChattingRoom(int artistGroupNo);

	int createLiveChattingRoom(Map<String, Integer> map);

	String artistGroupImage(String artistGroupTitle);

}
