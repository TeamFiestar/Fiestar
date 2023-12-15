package com.TeamFiestar.Fiestar.artist.model.service;

import java.util.Map;

import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;

public interface ArtistService {

	Map<String, Object> artistMember(String artistGroupTitle);

	int subscribe(int memberNo, String artistGroupTitle);

	ArtistGroup1 artist(String artistGroupTitle, int memberNo);

}
