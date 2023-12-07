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
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.mypage.dto.Pagination;
import com.TeamFiestar.Fiestar.mypage.mapper.MyPageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper mapper;

	private final BCryptPasswordEncoder bcrypt;

	@Value("${my.member.webpath}")
	private String webpath;

	@Value("${my.member.location}")
	private String folderPath;

	// 회원 탈퇴
	@Override
	public int withdrawal(String memberPw, int memberNo) {

		String encPw = mapper.selectMemberPw(memberNo);

		if (!bcrypt.matches(memberPw, encPw)) {
			return 0;
		}

		return mapper.withdrawal(memberNo);
	}

	// 프로필 이미지 바꾸기
	@Override
	public int profile(Member loginMember, MultipartFile memberProfile) throws IllegalStateException, IOException {

		String backup = loginMember.getMemberProfile();
		String rename = null;
		if (memberProfile.getSize() > 0) {
			rename = Util.fileRename(memberProfile.getOriginalFilename());
			loginMember.setMemberProfile(webpath + rename);
		} else {
			loginMember.setMemberProfile(null);
		}

		int result = mapper.profile(loginMember);

		if (result > 0) {
			if (memberProfile.getSize() > 0) {
				memberProfile.transferTo(new File(folderPath + rename));
			} else {
				loginMember.setMemberProfile(backup);
			}
		}
		return result;
	}

	
	// 내가 작성한 게시글 조회
	@Override
	public Map<String, Object> selectMyFeedList(Member loginMember, int cp) {
		
		
		// 내가 작성한 게시글 개수 조회
		int listCount = mapper.listCount(loginMember);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit); 
		
		List<Board> boardList = mapper.selectMyFeedList(rowBounds, loginMember.getMemberNo());
		
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
		
		
		
		return null;
	}
	
}
