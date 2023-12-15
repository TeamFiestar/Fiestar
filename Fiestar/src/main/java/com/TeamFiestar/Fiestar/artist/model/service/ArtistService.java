package com.TeamFiestar.Fiestar.artist.model.service;

import java.util.Map;

import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;

public interface ArtistService {

	Map<String, Object> artistMember(String artistGroupTitle);

	int subscribe(int memberNo, String artistGroupTitle);

	Map<String, Object> artist(int memberNo);

//	int update(String artistGroupTitle, int memberNo);

}
