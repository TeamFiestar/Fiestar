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


	// 회원 탈퇴
	int withDrawal(String memberPw, int memberNo);

	// 게시글 삭제
	int deleteBoard(int memberNo, int boardNo);

	// 프로필 이미지 변경
	int profile(MultipartFile memberProfile, Member loginMember) throws IllegalStateException, IOException;

	// 댓글 삭제
	int delComment(int memberNo, int commentNo, String commentType);

	int info(Member loginMember, MultipartFile memberBackImage, Member updateMember, String[] MemberAddress)
			throws IllegalStateException, IOException;

	// 공지사항 조회
	Map<String, Object> siteNotice(int cp);

	// 공지사항 상세 조회
	String selectSiteNotice(Map<String, Object> map);



























}

