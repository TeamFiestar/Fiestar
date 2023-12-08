package com.TeamFiestar.Fiestar.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.BoardImg;

@Mapper
public interface EditBoardMapper {

	int insertUploadList(List<BoardImg> uploadList);

	int insertBoard(Board board);

}
