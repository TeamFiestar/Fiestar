
package com.TeamFiestar.Fiestar.mypage.mapper;

import java.util.List; 
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.admin.model.dto.SiteNotice;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.Comment;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistSubscribe;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.member.model.dto.PurchaseList;




@Mapper
public interface MyPageMapper {

	// 내가 작성한 개시글 갯수
	int boardListCount(int memberNo);
	
	// 내가 작성한 개시글 조회
	List<Board> selectMyFeedList(int memberNo, RowBounds rowBounds);

	
	//내가 작성한 댓글 개수
	int commentCount(Member loginMember);

	// 내가 작성한 댓글
	List<Comment> MyCommentList(Member loginMember, RowBounds rowBounds);

	// 내가 구독한 아티스트 페이지 개수
	int artistCount(int memberNo);

	// 내가 구독한 아티스트 페이지 조회
	List<ArtistSubscribe> myArtistList(int memberNo, RowBounds rowBounds);


	// 암호화된 비밀번호 조회
	String selectPw(int memberNo);

	// 회원 탈퇴
	int withDrawal(int memberNo);

	// 내가 작성한 게시글 삭제
	int deleteBoard(Map<String, Object> map);

	// 프로필 이미지 변경
	int profile(Member loginMember);

	// 미디어 댓글 삭제
	int deleteMediaComment(Map<String, Object> map);

	// 피드 댓글 삭제
	int deleteBoardComment(Map<String, Object> map);

	int info(Member updateMember);

	// 공지사항 개수 조회
	int nocticeCount();

	// 공지사항 조회
	List<SiteNotice> siteNoticeList(RowBounds rowBounds);

	// 공지사항 상세 조회
	SiteNotice selectNotice(Map<String, Object> map);







	









	




	
}



	
	

