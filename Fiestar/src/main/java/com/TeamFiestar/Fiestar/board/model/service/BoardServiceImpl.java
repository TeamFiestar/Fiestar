package com.TeamFiestar.Fiestar.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;
	
	@Override
	public Map<String, Object> selectBoard(Map<String, Object> paramMap) {
		
		List<Board> boardList = mapper.selectBoard(paramMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardList", boardList);
		
		
		return map;
	}
	
	// 게시글 상세 조회
	@Override
	public Board detail(Map<String, Object> map) {
		return mapper.detail(map);
	}
	
	// 게시글 좋아요 여부 확인
	@Override
	public int likeCheck(Map<String, Object> map) {
		return mapper.likeCheck(map);
	}
	
	// 좋아요 처리
	@Override
	public int like(Map<String, Object> map) {
		
		int result = 0;
		
		if( (Integer) (map.get("check")) ==1) {
			result = mapper.deleteBoardLike(map);
		} else {
			
			result = mapper.insertBoardLike(map);
		}
		
		if(result == 0) {
			return -1;
			
		}
		return mapper.countBoardLike((Integer) map.get("boardNo") );
	}
	
	
	@Override
	public int updateReadCount(int boardNo) {
		return mapper.updateBoardCount(boardNo);
	}
	
	
}
