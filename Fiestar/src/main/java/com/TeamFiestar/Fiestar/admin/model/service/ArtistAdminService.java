package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;

public interface ArtistAdminService {

	List<ArtistNotice> ArtistNoticeList(String artistGroupTitle);

	int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice);

	List<Report> selectReportList(String artistGroupTitle);

}
