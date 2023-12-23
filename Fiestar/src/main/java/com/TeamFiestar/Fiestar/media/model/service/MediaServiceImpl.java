package com.TeamFiestar.Fiestar.media.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.media.model.mapper.MediaMapper;
import com.TeamFiestar.Fiestar.mypage.dto.Pagination;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService{

	private final MediaMapper mapper;
	private final ArtistAdminMapper artistAdminMapper;
	
	// 미디어 리스트 조회
	@Override
	public Map<String, Object> selectMediaList(Map<String, Object> map, int cp) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo( map.get("artistGroupTitle") );
		map.put("artistGroupNo", artistGroupNo);
		
		int listCount = mapper.mediaListCount(map);
		
		/* Pagination */
		Pagination pagination = new Pagination(cp, listCount, 12, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		/* Pagination */
		
		List<Media> mediaList = mapper.selectMediaList(map, rowBounds);
		
		map.put("mediaList", mediaList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	@Override
	public int insertMedia(Media inserMedia) {
		int result = mapper.insertMedia(inserMedia);
		return result;
	}
	
	@Override
	public int updateReadCount(int mediaNo) {
		return mapper.updateReadCount(mediaNo);
	}
	
	// 미디어 디테일
	@Override
	public Media mediaDetail(Map<String, Object> map) {

		return mapper.mediaDetail(map);
	}
	
	// 미디어 업데이트 이동
	@Override
	public Media updateMediaDetail(int mediaNo) {
		return mapper.updateMediaDetail(mediaNo);
	}
	
	// 미디어 업데이트
	@Override
	public int updateMedia(Media updateMedia) {
		return mapper.updateMedia(updateMedia);
	}
	
	// 미디어 삭제
	@Override
	public int deleteMedia(int mediaNo) {
		return mapper.deleteMedia(mediaNo);
	}
	
	
	@Override
	public int selectArtistAdminNo(String artistGroupTitle) {
		
		return mapper.selectArtistAdminNo(artistGroupTitle);
	}
	
}
