package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;

public interface ArtistAdminService {

	Map<String, Object> ArtistNoticeList(Map<String, Object> map, int cp);

	int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice);

	Map<String, Object> selectReportList(String artistGroupTitle, Report report, int cp);

	Map<String, Object> selectPurchaseList(String artistGroupTitle, int cp);

}
