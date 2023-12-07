package com.TeamFiestar.Fiestar.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.Comment;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface MyPageMapper {

	// 암호화된 비밀번호 조회
	String selectMemberPw(int memberNo);

	// 회원 탈퇴
	int withdrawal(int memberNo);

	// 프로필 이미지 바꾸기
	int profile(Member loginMember);

	// 내가 작성한 개시글 갯수
	int listCount(Member loginMember);
	
	// 내가 작성한 개시글 조회
	List<Board> selectMyFeedList(RowBounds rowBounds, int memberNo);

	//내가 작성한 댓글 개수
	int commentCount(Member loginMember);

	List<Comment> MyCommentList(Member loginMember, RowBounds rowBounds);



	
	

}
