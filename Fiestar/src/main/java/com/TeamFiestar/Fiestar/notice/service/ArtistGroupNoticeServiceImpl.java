package com.TeamFiestar.Fiestar.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.AdminPagination;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.notice.dto.ArtistGroupNotice;
import com.TeamFiestar.Fiestar.notice.mapper.ArtistGroupNoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistGroupNoticeServiceImpl implements ArtistGroupNoticeService{

	private final ArtistGroupNoticeMapper mapper; 
	private final ArtistAdminMapper artistAdminMapper;
	
	@Override
	public Map<String, Object> notice(String artistGroupTitle, int cp) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		
		int countNoticeList = mapper.countNoticeList(artistGroupNo);
		
		AdminPagination pagination = new AdminPagination(countNoticeList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ArtistGroupNotice> artistGroupNoticeList = mapper.notice(rowBounds, artistGroupNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("artistGroupNoticeList", artistGroupNoticeList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	
	@Override
	public List<ArtistGroupNotice> noticeDetail(String artistGroupTitle, int artistGroupNoticeNo) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		Map<String, Object> map = new HashMap<>();
		map.put("artistGroupNo", artistGroupNo);
		map.put("artistGroupNoticeNo", artistGroupNoticeNo);
		List<ArtistGroupNotice> artistGroupNoticeList = mapper.noticeDetail(map);
		
		return artistGroupNoticeList;
	}
}
