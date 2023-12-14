package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.mypage.dto.Pagination;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistAdminServiceImpl implements ArtistAdminService{
	
	private final ArtistAdminMapper mapper;
	
	// 아티스트 공지사항 조회
	@Override
	public Map<String, Object> ArtistNoticeList(Map<String, Object> map, int cp) {
	
		int artistGroupNo = mapper.selectArtistGroupNo(map.get("artistGroupTitle"));
	
		map.put("artistGroupNo", artistGroupNo);
		
		int listCount = mapper.noticeListCount(map);
		
		/* Pagination */
		Pagination pagination = new Pagination(cp, listCount, 10, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		/* Pagination */

		List<ArtistNotice> noticeList = mapper.selectNoticeList(map, rowBounds);
		
		map.put("noticeList", noticeList);
		map.put("pagination", pagination);
		
		
		return map;
	}
	
	// 아티스트 공지사항 삽입
	@Override
	public int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice) {
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		notice.setArtistGroupNo(artistGroupNo);
		return mapper.artistNoticeAdd(notice);
	}
	
	// 아티스트 신고 조회
	@Override
	public Map<String, Object> selectReportList(String artistGroupTitle, Report report, int cp) {
		Map<String, Object> map = new HashMap<>();   
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);   
		
		map.put("artistGroupNo",artistGroupNo);
		map.put("report",report);
		int listCount = mapper.reportListCount(map);
		
		/* Pagination */
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);   
		/* Pagination */
		
		List<Report> reportList = mapper.selectReportList(map, rowBounds);
		
		map.put("reportList", reportList);
		map.put("pagination", pagination);
		
		
		return map;
	}
	
	
	
	@Override
	public Map<String, Object> selectPurchaseList(String artistGroupTitle, int cp) {
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		
		int listCount = mapper.orderListCount(artistGroupNo);
		
		/* Pagination */
		Pagination pagination = new Pagination(cp, listCount, 8, 10);
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);   
		/* Pagination */
		
		List<Purchase> purchaseList = mapper.selectPurchaseList(artistGroupNo, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("purchaseList", purchaseList);
		map.put("pagination", pagination);
		
		return map;
	}

}
