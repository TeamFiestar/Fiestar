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

}
