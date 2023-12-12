package com.TeamFiestar.Fiestar.chatting.model.service;

import com.TeamFiestar.Fiestar.chatting.model.dto.Message;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface ChatService {

	int insertMessage(Message msg);

	int selectMember(int i);

	int insertLiveMessage(Message msg);
	
	int selectLiveChattingRoom(String artistGroupTitle);
	
	int createLiveChattingRoom(String artistGroupTitle);

}
