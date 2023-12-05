package com.TeamFiestar.Fiestar.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	List<Board> selectBoard(Map<String, Object> paramMap);

}
