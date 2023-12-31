package com.TeamFiestar.Fiestar.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.Comment;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;

@Mapper
public interface BoardMapper {

	List<Board> selectBoard(Map<String, Object> paramMap);


	int likeCheck(Map<String, Object> map);

	int insertBoardLike(Map<String, Object> map);

	int deleteBoardLike(Map<String, Object> map);

	int countBoardLike(Integer integer);

	int artistGroupNo(String artistGroupTitle);

	List<Board> selectArtistBoard(Map<String, Object> paramMap);

	Board boardDetail(Map<String, Object> map);


	List<Comment> selectCommentList(Map<String, Object> map);


	ArtistGroup1 artistGroup(ArtistGroup1 artistGroup);


	int insertReport(Report report);

}
