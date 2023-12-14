package com.TeamFiestar.Fiestar.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamFiestar.Fiestar.board.model.dto.Comment;
import com.TeamFiestar.Fiestar.board.model.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentMapper mapper;
	
	
	@Override
	public int insert(Comment comment) {
		return mapper.insert(comment);
	}
	
	
	@Override
	public int delete(int boardCommentNo) {
		return mapper.delete(boardCommentNo);
	}
	
	@Override
	public List<Comment> select(Map<String, Integer> map) {
		return mapper.select(map);
	}
	
	@Override
	public int likeComment(Comment comment) {
		return mapper.likeComment(comment);
	}
	
	@Override
	public int deleteLike(Comment comment) {
		return mapper.deleteLike(comment);
	}
	
	@Override
	public int likeClick(Map<String, Integer> map) {
		return mapper.likeClick(map);
	}
}
