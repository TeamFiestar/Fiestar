package com.TeamFiestar.Fiestar.chatting.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamFiestar.Fiestar.chatting.model.dto.Message;
import com.TeamFiestar.Fiestar.chatting.model.mapper.ChattingMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	private final ChattingMapper mapper;
	
	@Override
	public int insertMessage(Message msg) {
		return mapper.insertMessage(msg);
	}
	
}
