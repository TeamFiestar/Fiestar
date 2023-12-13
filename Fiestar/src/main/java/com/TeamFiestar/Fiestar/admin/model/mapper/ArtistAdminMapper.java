package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;

@Mapper
public interface ArtistAdminMapper {

	int selectArtistGroupNo(Object object);

	List<ArtistNotice> selectNoticeList(Map<String, Object> map, RowBounds rowBounds);

	int artistNoticeAdd(ArtistNotice notice);

	List<Report> selectReportList(Map<String, Object> map, RowBounds rowBounds);

	int reportListCount(Map<String, Object> map);

	int noticeListCount(Map<String, Object> map);

	List<Purchase> selectPurchaseList(int artistGroupNo, RowBounds rowBounds);

	int orderListCount(int artistGroupNo);

	
	

}
