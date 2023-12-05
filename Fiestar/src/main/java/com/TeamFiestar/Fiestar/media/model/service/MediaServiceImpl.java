package com.TeamFiestar.Fiestar.media.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.media.model.mapper.MediaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService{

	private final MediaMapper mapper;
	
	@Override
	public List<Media> selectMediaList(Map<String, Object> map) {
		
		List<Media> mediaList = mapper.selectMediaList(map);
		return mediaList;
	}
	
	@Override
	public int insertMedia(Media inserMedia) {
		
		int result = mapper.insertMedia(inserMedia);
		
		return result;
	}
	
}
