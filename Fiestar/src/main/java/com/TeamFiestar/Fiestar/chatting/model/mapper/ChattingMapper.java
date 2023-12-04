package com.TeamFiestar.Fiestar.chatting.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.chatting.model.dto.Message;

@Mapper
public interface ChattingMapper {

	int insertMessage(Message msg);

}
