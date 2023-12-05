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
	
}
