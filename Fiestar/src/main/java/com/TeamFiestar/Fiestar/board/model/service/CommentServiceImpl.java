package com.TeamFiestar.Fiestar.board.model.service;

import java.util.List;

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
	public List<Comment> select(int boardNo) {
		return mapper.select(boardNo);
	}
	
	@Override
	public int insert(Comment comment) {
		return mapper.insert(comment);
	}
	
	@Override
	public int update(Comment comment) {
		return mapper.update(comment);
	}
	
	@Override
	public int delete(int commentNo) {
		return mapper.delete(commentNo);
	}
}
