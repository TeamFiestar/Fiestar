package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.Map;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;

public interface ArtistAdminAJAXService {

	ArtistNotice selectArtistNotice(Map<String, Object> map);

	int deleteNotice(int noticeNo);

	int updateNotice(ArtistNotice inputNotice);

}
