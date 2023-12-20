package com.TeamFiestar.Fiestar.artist.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.artist.model.dto.Artist;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.notice.dto.ArtistGroupNotice;

public interface ArtistService {

	Map<String, Object> artistMember(String artistGroupTitle);
	Map<String, Object> loginArtistMember(int memberNo, String artistGroupTitle);

	int subscribe(int memberNo, String artistGroupTitle);

	Map<String, Object> artist(int memberNo);

	int artistUpdate(String artistGroupTitle, MultipartFile artistGroupMain, MultipartFile artistGroupLogo,
			String artistGroupIntroduce, List<MultipartFile> artistProfileImg, 
			List<String> name, List<String> email, ArtistGroup1 artistGroup, int adminNo) throws IllegalStateException, IOException;

	Map<String, Object> artistNotice(String artistGroupTitle);

	List<ArtistGroupNotice> artistNoticeDetail(String artistGroupTitle, int artistGroupNoticeNo);


//	int update(String artistGroupTitle, int memberNo);

}
