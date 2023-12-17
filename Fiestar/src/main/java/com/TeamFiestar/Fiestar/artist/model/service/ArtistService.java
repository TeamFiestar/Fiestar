package com.TeamFiestar.Fiestar.artist.model.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;

public interface ArtistService {

	Map<String, Object> artistMember(String artistGroupTitle);

	int subscribe(int memberNo, String artistGroupTitle);

	Map<String, Object> artist(int memberNo);

	int artistUpdate(String artistGroupTitle, MultipartFile artistGroupMainimg, MultipartFile artistGroupLogoimg,
			String artistGroupIntroduce, ArtistGroup1 artistGroup, int adminNo) throws IllegalStateException, IOException;

//	int update(String artistGroupTitle, int memberNo);

}
