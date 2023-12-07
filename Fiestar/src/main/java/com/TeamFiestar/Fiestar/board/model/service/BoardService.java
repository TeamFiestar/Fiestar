package com.TeamFiestar.Fiestar.board.model.service;

import java.util.Map;

import com.TeamFiestar.Fiestar.board.model.dto.Board;

public interface BoardService {

	// 게시글 전체 조회
	Map<String, Object> selectBoard(Map<String, Object> paramMap);
//	Board selectBoard(Map<String, Object> map);

	// 게시글 상세조회
	Board detail(Map<String, Object> map);
	
	// 게시글 좋아여 여부 확인
	int likeCheck(Map<String, Object> map);

	// 게시글 조회수 증가
	int updateReadCount(int boardNo);
	
	// 게시글 좋아요 처리
	int like(Map<String, Object> map);

	int artistGroupNo(String artistGroupTitle);


}
