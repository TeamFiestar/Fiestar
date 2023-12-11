package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;

@Mapper
public interface ArtistAdminMapper {

	int selectArtistGroupNo(String artistGroupTitle);

	List<ArtistNotice> selectNoticeList(int artistGroupNo);

	int artistNoticeAdd(ArtistNotice notice);

	List<Report> selectReportList(int artistGroupNo);
	
	

}
