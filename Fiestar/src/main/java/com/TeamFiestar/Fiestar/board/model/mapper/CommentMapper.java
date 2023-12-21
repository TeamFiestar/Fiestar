package com.TeamFiestar.Fiestar.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.board.model.dto.Comment;

@Mapper
public interface CommentMapper {


	int insert(Comment comment);

	int delete(int boardCommentNo);

	List<Comment> select(Map<String, Integer> map);

	int likeComment(Comment comment);

	int deleteLike(Comment comment);

	int likeClick(Map<String, Integer> map);

	int selectCommentLikeCount(int boardCommentNo);

	int insertReport(Report report);

}
