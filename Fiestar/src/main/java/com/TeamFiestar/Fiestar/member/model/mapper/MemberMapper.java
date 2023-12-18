package com.TeamFiestar.Fiestar.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(Member inputMember);

	int signup(Member inputMember);

	Member login(String memberEmail);
	
	/** 이메일 중복 체크
	 * @param email
	 * @return
	 */
	int checkEmail(String email);

	/** 닉네임 중복 체크
	 * @param nickname
	 * @return
	 */
	int checkNickname(String nickname);
	
}
