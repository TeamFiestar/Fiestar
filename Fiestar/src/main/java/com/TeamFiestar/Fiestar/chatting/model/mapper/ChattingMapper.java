package com.TeamFiestar.Fiestar.chatting.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.chatting.model.dto.Message;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface ChattingMapper {

	int insertMessage(Message msg);

	int selectMember(int i);

}
