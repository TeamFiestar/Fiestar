package com.TeamFiestar.Fiestar.chatting.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.chatting.model.dto.Message;
import com.TeamFiestar.Fiestar.chatting.model.mapper.ChattingMapper;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	private final ChattingMapper mapper;
	private final ArtistAdminMapper artistAdminMapper;
	
	@Override
	public int insertMessage(Message msg) {
		return mapper.insertMessage(msg);
	}
	
	@Override
	public int selectMember(int i) {
		return mapper.selectMember(i);
	}
	
	@Override
	public int insertLiveMessage(Message msg) {
		return mapper.insertLiveMessage(msg);
	}
	
	@Override
	public int selectLiveChattingRoom(String artistGroupTitle) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		return mapper.selectLiveChattingRoom(artistGroupNo);
	}
	
	@Override
	public int createLiveChattingRoom(String artistGroupTitle) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		return mapper.createLiveChattingRoom(artistGroupNo);
	}
	
	@Override
	public int createChattingRoom(String artistGroupTitle) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		return mapper.createLiveChattingRoom(artistGroupNo);
	}
	
}
