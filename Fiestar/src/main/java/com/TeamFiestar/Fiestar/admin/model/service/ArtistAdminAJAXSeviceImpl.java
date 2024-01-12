package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.Map;

import javax.xml.stream.events.Comment;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminAJAXMapper;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.media.model.dto.MediaComment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistAdminAJAXSeviceImpl implements ArtistAdminAJAXService{
	
	private final ArtistAdminAJAXMapper mapper;
	
	@Override
	public ArtistNotice selectArtistNotice(Map<String, Object> map) {
		return mapper.selectArtistNotice(map);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return mapper.deleteNotice(noticeNo);
	}
	
	@Override
	public int updateNotice(ArtistNotice inputNotice) {
		return mapper.updateNotice(inputNotice);
	}
	
	@Override
	public int updatePurchase(Purchase purchase) {
		return mapper.updatePurchase(purchase);
	}
	
	// 구매목록 조회
	@Override
	public Purchase selectPurchaseDetails(int purchaseNo) {
		return mapper.selectPurchaseDetails(purchaseNo);
	}
	
	
	// 게시판 조회
	@Override
	public Board selectBoardReport(int boardNo) {
		return mapper.selectBoardReport(boardNo);
	}
	

	// 게시판 댓글 조회
	@Override
	public Comment selectBoardCommentReport(int boardCommentNo) {
		return mapper.selectBoardCommentReport(boardCommentNo);
	}
	
	
	// 미디어 댓글 조회
	@Override
	public MediaComment selectMediaCommentReport(int mediaCommentNo) {
		return mapper.selectMediaCommentReport(mediaCommentNo);
	}
	
	// 신고 삭제 처리
	@Override
	public int deleteReport(Map<String, Object> map) {
		
		String reportType = (String) map.get("reportType");
		
		int result = 0;
		
		if (reportType.equals("board")) {
			result = mapper.deleteBoard(map);
		}
		
		if (reportType.equals("boardComment")) {
			result = mapper.deleteBoardComment(map);
		}
		
		if (reportType.equals("mediaComment")) {
			result = mapper.deleteMediaComment(map);
		}
		
		// 신고 완료 처리
		
		int reportResult = 0;
		reportResult = mapper.proceedReport(map);
		
		return result;
	}
	
	
	@Override
	public int proceedReport(Map<String, Object> map) {
		return mapper.proceedReport(map);
	}
	
	

}
