package com.TeamFiestar.Fiestar.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(Member inputMember);

	int signup(Member inputMember);

	Member login(String memberEmail);
	
}
