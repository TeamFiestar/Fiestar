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
	
	
	// 내가 작성한 게시글 조회
	Map<String, Object> selectMyFeedList(Member loginMember, int cp);

//	// 내가 작성한 게시글 삭제
//	int deleteBoard(int memberNo, String boardNo);

	// 내가 작성한 댓글 조회
	Map<String, Object> MyCommentList(Member loginMember, int cp);

	// 내가 구독한 아티스트
	Map<String, Object> myArtistList(Member loginMember, int cp);

	// 구매목록
	Map<String, Object> myPurchaseList(Member loginMember, int cp);





}

