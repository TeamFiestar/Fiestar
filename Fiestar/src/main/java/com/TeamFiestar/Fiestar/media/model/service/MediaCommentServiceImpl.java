package com.TeamFiestar.Fiestar.media.model.service;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.media.model.mapper.MediaCommentMapper;

import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaCommentServiceImpl implements MediaCommentService{

	private final MediaCommentMapper mapper;
	
	
}
