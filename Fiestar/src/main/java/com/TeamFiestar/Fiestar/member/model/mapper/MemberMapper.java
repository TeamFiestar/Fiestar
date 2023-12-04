package com.TeamFiestar.Fiestar.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.member.model.DTO.Member;

@Mapper
public interface MemberMapper {

	Member login(Member inputMember);

	int signup(Member inputMember);
	
}
