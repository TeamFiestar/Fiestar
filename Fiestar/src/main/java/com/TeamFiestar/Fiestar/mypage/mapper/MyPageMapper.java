
package com.TeamFiestar.Fiestar.mypage.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.Comment;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistSubscribe;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.member.model.dto.PurchaseList;



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
	
	// 내가 작성한 개시글 조회S
	List<Board> selectMyFeedList(RowBounds rowBounds, int memberNo);

	// 내가 작성한 게시글 삭제
//	int deleteBoard(String boardNo);
	
	//내가 작성한 댓글 개수
	int commentCount(Member loginMember);

	// 내가 작성한 댓글
	List<Comment> MyCommentList(Member loginMember, RowBounds rowBounds);

	// 내가 구독한 아티스트 페이지 개수
	int artistCount(Member loginMember);

	// 내가 구독한 아티스트 페이지 조회
	List<ArtistSubscribe> myArtistList(Member loginMember, RowBounds rowBounds);

	// 구매한 상품 개수 
	int purchaseCount(Member loginMember);
	

	// 내가 구매한 상품 목록 조회
	List<PurchaseList> myPurchaseList(Member loginMember, RowBounds rowBounds);



	
}



	
	

