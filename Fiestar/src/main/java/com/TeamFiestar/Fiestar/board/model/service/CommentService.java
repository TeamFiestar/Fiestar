package com.TeamFiestar.Fiestar.board.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.board.model.dto.Comment;

public interface CommentService {


	int insert(Comment comment);

	int delete(int boardCommentNo);

	List<Comment> select(Map<String, Integer> map);

	int likeComment(Comment comment);

	int deleteLike(Comment comment);

	int likeClick(Map<String, Integer> map);

	int insertReport(Report report);

}
