package com.TeamFiestar.Fiestar.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.mapper.BoardMapper;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
@PropertySource("classpath:/config.properties")
public class BoardServiceImpl implements BoardService {

	private final BoardMapper mapper;

	@Override
	public Map<String, Object> selectBoard(Map<String, Object> paramMap) {

		List<Board> boardList = mapper.selectBoard(paramMap);

		Map<String, Object> map = new HashMap<>();

		if (paramMap.containsKey("boardNo")) {
			Board boardDetail = mapper.boardDetail(map);
			map.put("boardDetail", boardDetail);
		}

		map.put("boardList", boardList);

		return map;
	}

	// 게시글 상세 조회
	@Override
	public Board boardDetail(Map<String, Object> map) {

		//	if (map.containsKey("boardNo")) {
		//		Board boardDetail = mapper.boardDetail(map);
		//		map.put("boardDetail",boardDetail);
		//	}
		Board board = mapper.boardDetail(map);
		
		board.setCommentList(mapper.selectCommentList(map));
		return board;
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

		if ((Integer) (map.get("likeCheck")) == 1) {
			result = mapper.deleteBoardLike(map);
		} else {

			result = mapper.insertBoardLike(map);
		}

		if (result == 0) {
			return -1;
		}
		return result;
	}

	@Override
	public int artistGroupNo(String artistGroupTitle) {
		return mapper.artistGroupNo(artistGroupTitle);
	}

	@Override
	public Map<String, Object> selectArtistBoard(Map<String, Object> paramMap) {

		List<Board> boardList = mapper.selectArtistBoard(paramMap);
		

		Map<String, Object> map = new HashMap<>();

		if (paramMap.containsKey("boardNo")) {
			Board boardDetail = mapper.boardDetail(map);
			map.put("boardDetail", boardDetail);
		}

		map.put("boardList", boardList);
		
		
		return map;
	}
	
	@Override
	public Map<String, Object> artistGroup(ArtistGroup1 artistGroup) {
		
		ArtistGroup1 artistGroup1 = mapper.artistGroup(artistGroup);
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("artistGroup", artistGroup1);
		
		return map2;
	}
	
	@Override
	public int insertReport(Report report) {
		return mapper.insertReport(report);
	}


}
