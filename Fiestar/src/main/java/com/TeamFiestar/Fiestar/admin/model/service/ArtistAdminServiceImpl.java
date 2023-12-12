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
	
	@Override
		public List<ArtistNotice> ArtistNoticeList(Map<String, Object> map, int cp) {
		
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
			
			
			return noticeList;
		}
	
	@Override
	public int artistNoticeAdd(String artistGroupTitle, ArtistNotice notice) {
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		notice.setArtistGroupNo(artistGroupNo);
		return mapper.artistNoticeAdd(notice);
	}
	
	@Override
	public List<Report> selectReportList(String artistGroupTitle, Report report, int cp) {
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
		
		
		return mapper.selectReportList(map, rowBounds);
	}
	
	
	
	@Override
	public List<Purchase> selectPurchaseList(String artistGroupTitle) {
		int artistGroupNo = mapper.selectArtistGroupNo(artistGroupTitle);
		return mapper.selectPurchaseList(artistGroupNo);
	}

}
