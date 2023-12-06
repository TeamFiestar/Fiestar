package com.TeamFiestar.Fiestar.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface MyPageMapper {

	// 암호화된 비밀번호 조회
	String selectMemberPw(int memberNo);

	// 회원 탈퇴
	int withdrawal(int memberNo);

	// 프로필 이미지 바꾸기
	int profile(Member loginMember);

	// 프로필 배경 이미지 바꾸기
	int changeBackImg(Member loginMember);

	// 프로필 정보 바꾸기
	int info(Member updateMember);
	
	

}
