package com.TeamFiestar.Fiestar.media.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.media.model.mapper.MediaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService{

	private final MediaMapper mapper;
	private final ArtistAdminMapper artistAdminMapper;
	
	// 미디어 리스트 조회
	@Override
	public List<Media> selectMediaList(Map<String, Object> map) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo( map.get("artistGroupTitle") );
		map.put("artistGroupNo", artistGroupNo);
		
		List<Media> mediaList = mapper.selectMediaList(map);
		return mediaList;
	}
	
	@Override
	public int insertMedia(Media inserMedia) {
		
		int result = mapper.insertMedia(inserMedia);
		
		return result;
	}
	
	// 미디어 디테일
	@Override
	public Media mediaDetail(Map<String, Object> map) {

		
		return mapper.mediaDetail(map);
	}
	
}
