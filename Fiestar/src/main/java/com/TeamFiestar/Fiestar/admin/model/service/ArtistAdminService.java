package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;

public interface ArtistAdminService {

	List<ArtistNotice> ArtistNoticeList(Map<String, Object> map, int cp);

	int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice);

	List<Report> selectReportList(String artistGroupTitle, Report report, int cp);

	List<Purchase> selectPurchaseList(String artistGroupTitle);

}
