package com.TeamFiestar.Fiestar.mypage.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.Comment;
import com.TeamFiestar.Fiestar.common.utility.Util;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistSubscribe;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.member.model.dto.PurchaseList;
import com.TeamFiestar.Fiestar.mypage.dto.Pagination;
import com.TeamFiestar.Fiestar.mypage.mapper.MyPageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper mapper;

	private final BCryptPasswordEncoder bcrypt;

	@Value("${my.member.webpath}")
	private String webpath;

	@Value("${my.member.location}")
	private String folderPath;

	// 내가 작성한 게시글 조회
	@Override
	public Map<String, Object> selectMyFeedList(Member loginMember, int cp) {

		// 내가 작성한 게시글 개수 조회
		int listCount = mapper.boardListCount(loginMember.getMemberNo());

		Pagination pagination = new Pagination(cp, listCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, limit);

		List<Board> boardList = mapper.selectMyFeedList(loginMember.getMemberNo(),rowBounds);

		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);

		return map;

	}

	// 내가 작성한 댓글 조회
	@Override
	public Map<String, Object> MyCommentList(Member loginMember, int cp) {

		// 내가 작성한 댓글 수 조회
		int listCount = mapper.commentCount(loginMember);

		Pagination pagination = new Pagination(cp, listCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, limit);

		List<Comment> commentList = mapper.MyCommentList(loginMember, rowBounds);

		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("commentList", commentList);

		return map;
	}

	// 내가 구독한 아티스트
	@Override
	public Map<String, Object> myArtistList(Member loginMember, int cp) {

		int listCount = mapper.artistCount(loginMember.getMemberNo());

		Pagination pagination = new Pagination(cp, listCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, limit);

		List<ArtistSubscribe> artistList = mapper.myArtistList(loginMember.getMemberNo(), rowBounds);

		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("artistList", artistList);

		return map;
	}

	// 구매목록
	@Override
	public Map<String, Object> myPurchaseList(Member loginMember, int cp) {

		int listCount = mapper.purchaseCount(loginMember);

		Pagination pagination = new Pagination(cp, listCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, limit);

		List<PurchaseList> purchaseList = mapper.myPurchaseList(loginMember, rowBounds);

		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("purchaseList", purchaseList);

		return map;

	}

	// 회원 탈퇴
	@Override
	public int withDrawal(String memberPw, int memberNo) {

		String encPw = mapper.selectPw(memberNo);

		if (!bcrypt.matches(memberPw, encPw)) {
			return 0;
		}

		return mapper.withDrawal(memberNo);
	}

	// 게시글 삭제
	@Override
	public int deleteBoard(int memberNo, int boardNo) {

		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("boardNo", boardNo);

		return mapper.deleteBoard(map);

	}
	
	// 프로필 이미지 변경
	@Override
	public int profile(MultipartFile memberProfile, Member loginMember) throws IllegalStateException, IOException {

		String backup = loginMember.getMemberProfile();
		
		String rename = null;
		
		if(memberProfile.getSize() > 0) {
			rename = Util.fileRename(memberProfile.getOriginalFilename());
			loginMember.setMemberProfile(webpath + rename);
		} else {
			loginMember.setMemberProfile(null);
		}
		
		int result = mapper.profile(loginMember);
		
		if(result > 0) {
			if(memberProfile.getSize() > 0) {
				memberProfile.transferTo(new File(folderPath + rename));
			} else {
				loginMember.setMemberProfile(backup);
			}
		}
		
		return result;
	}
	
	// 프로필 정보 변경
	@Override
	public int backImg(MultipartFile memberBackImage, Member loginMember) throws IllegalStateException, IOException {
		
		String backup = loginMember.getMemberBackImage();
		
		String rename = null;
		
		if(memberBackImage.getSize() > 0) {
			rename = Util.fileRename(memberBackImage.getOriginalFilename());
			loginMember.setMemberBackImage(webpath + rename);
		} else {
			loginMember.setMemberBackImage(backup);
		}
		
		int result = mapper.backImg(loginMember);
		
		if(result > 0) {
			if(memberBackImage.getSize() > 0) {
				memberBackImage.transferTo(new File(folderPath + rename));
			} else {
				loginMember.setMemberBackImage(backup);
			}
		}
		
		return result;
	}
	

}
