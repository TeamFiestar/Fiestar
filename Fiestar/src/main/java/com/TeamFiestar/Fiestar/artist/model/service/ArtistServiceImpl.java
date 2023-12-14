package com.TeamFiestar.Fiestar.artist.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.artist.model.dto.Artist;
import com.TeamFiestar.Fiestar.artist.model.mapper.ArtistMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService{
	private final ArtistMapper mapper;
	private final ArtistAdminMapper artistAdminMapper;
	
	@Override
	public Map<String, Object> artistMember(String artistGroupTitle) {
		String artistGroupBackimg = mapper.artistGroupBackimg(artistGroupTitle);
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		List<Artist> artist = mapper.artist(artistGroupNo);
		return null;
	}

}
