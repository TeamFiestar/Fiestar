package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistAdminServiceImpl implements ArtistAdminService{
	
	private final ArtistAdminMapper mapper;
	
	@Override
		public List<ArtistNotice> ArtistNoticeList(String artistGroupTitle) {
			int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
			List<ArtistNotice> noticeList = mapper.selectNoticeList(artistGroupNo);
			
			return noticeList;
		}
	
	@Override
	public int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice) {
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		notice.setArtistGroupNo(artistGroupNo);
		return mapper.artistNoticeAdd(notice);
	}

}
