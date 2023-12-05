package com.TeamFiestar.Fiestar.media.model.service;

import java.util.List;
import java.util.Map;

import javax.xml.stream.events.Comment;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.media.model.dto.MediaComment;
import com.TeamFiestar.Fiestar.media.model.mapper.MediaCommentMapper;

import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaCommentServiceImpl implements MediaCommentService{

	private final MediaCommentMapper mapper;
	
	@Override
	public int inputComment(MediaComment inputComment) {
		return mapper.inputComment(inputComment);
	}
	
	
	@Override
	public List<Comment> selectComment(Map<String, Integer> map) {
		return mapper.selectComment(map);
	}
	
}
