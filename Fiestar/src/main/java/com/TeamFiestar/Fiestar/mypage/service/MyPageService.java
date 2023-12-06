package com.TeamFiestar.Fiestar.mypage.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface MyPageService {

	// 회원 탈퇴
	int withdrawal(String memberPw, int memberNo);

	// 프로필 이미지 바꾸기
	int profile(Member loginMember, MultipartFile memberProfile) throws IllegalStateException, IOException;

	// 프로필 배경 이미지 바꾸기
	int changeBackImg(Member loginMember, MultipartFile memberBackImage) throws IllegalStateException, IOException;

	// 프로필 정보 바꾸기
	int info(Member updateMember, String[] memberAddress);

}
