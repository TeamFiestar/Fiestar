package com.TeamFiestar.Fiestar.admin.model.service;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminAJAXMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistAdminAJAXSeviceImpl implements ArtistAdminAJAXService{
	
	private final ArtistAdminAJAXMapper mapper;
	
	@Override
		public ArtistNotice selectArtistNotice(int artistGroupNo) {
			return mapper.selectArtistNotice(artistGroupNo);
		}

}
