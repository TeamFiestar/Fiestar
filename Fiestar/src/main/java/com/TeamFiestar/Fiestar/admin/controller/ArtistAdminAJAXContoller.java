package com.TeamFiestar.Fiestar.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.events.Comment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.service.ArtistAdminAJAXService;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.media.model.dto.MediaComment;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("artistAdmin")
@RequiredArgsConstructor
public class ArtistAdminAJAXContoller {
	
	private final ArtistAdminAJAXService service;
	
	@GetMapping("selectNotice")
	@ResponseBody
	public ArtistNotice selectArtistNotice(@RequestParam("artistGroupNoticeNo") int artistGroupNoticeNo) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("artistGroupNoticeNo", artistGroupNoticeNo);
		
		return service.selectArtistNotice(map);
	}
	
	@PutMapping("deleteNotice")
	@ResponseBody
	public int deleteNotice(@RequestBody int noticeNo) {
		return service.deleteNotice(noticeNo);
	}

	// 공지사항 변경
	@PutMapping("updateNotice")
	@ResponseBody
	public int updateNotice(@RequestBody ArtistNotice inputNotice) {
		return service.updateNotice(inputNotice);
	}
	
	// 구매 상태 변경
	@PutMapping("updatePurchaseStatus")
	@ResponseBody
	public int updatePurchase(@RequestBody Purchase purchase) {
		
		int result = service.updatePurchase(purchase);
		
		return result;
	}
	
	// 구매리스트 조회
	@GetMapping("selectPurchaseDetails")
	@ResponseBody
	public Purchase selectPurchaseDetails(@RequestParam("purchaseNo") int purchaseNo) {
		
		Purchase purchase = service.selectPurchaseDetails(purchaseNo);
		
		return purchase;
	}
	
	
	// 게시판 신고 조회
	@GetMapping("selectBoardReport")
	public Board selectBoardReport(@RequestParam("boardNo") int boardNo) {
		
		return service.selectBoardReport(boardNo);
		
	}
	
	// 게시판 댓글 신고 조회
	@GetMapping("selectBoardCommentReport")
	public Comment selectBoardCommentReport(@RequestParam("boardCommentNo") int boardCommentNo) {
		
		return service.selectBoardCommentReport( boardCommentNo);
		
	}
	
	// 미디어 댓글 신고 조회
	@GetMapping("selectMediaCommentReport")
	public MediaComment selectMediaCommentReport(@RequestParam("mediaCommentNo") int mediaCommentNo) {
		
		return service.selectMediaCommentReport(mediaCommentNo);
		
	}
	
	// 신고 삭제 처리
	@PutMapping("deleteReport")
	public int deleteReport(@RequestBody Map<String, Object> map) {
		
		return service.deleteReport(map);
	}
	
	
	
	
	
	
	
}
