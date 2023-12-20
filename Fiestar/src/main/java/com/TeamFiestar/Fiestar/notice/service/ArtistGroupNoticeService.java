package com.TeamFiestar.Fiestar.notice.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.notice.dto.ArtistGroupNotice;

public interface ArtistGroupNoticeService {

	Map<String, Object> notice(String artistGroupTitle, int cp);

	List<ArtistGroupNotice> noticeDetail(String artistGroupTitle, int artistGroupNoticeNo);

}
