package com.TeamFiestar.Fiestar.mypage.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface MyPageService {
	
	// 내가 작성한 게시글 조회
	Map<String, Object> selectMyFeedList(Member loginMember, int cp);


	// 내가 작성한 댓글 조회
	Map<String, Object> MyCommentList(Member loginMember, int cp);

	// 내가 구독한 아티스트
	Map<String, Object> myArtistList(Member loginMember, int cp);

	// 구매목록
	Map<String, Object> myPurchaseList(Member loginMember, int cp);

	// 회원 탈퇴
	int withDrawal(String memberPw, int memberNo);

	// 게시글 삭제
	int deleteBoard(int memberNo, int boardNo);

	// 프로필 이미지 변경
	int profile(MultipartFile memberProfile, Member loginMember) throws IllegalStateException, IOException;

	// 프로필 배경 이미지 변경
	int backImg(MultipartFile memberBackImage, Member loginMember) throws IllegalStateException, IOException;











}

