package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.Map;

import javax.xml.stream.events.Comment;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.media.model.dto.MediaComment;

public interface ArtistAdminAJAXService {

	ArtistNotice selectArtistNotice(Map<String, Object> map);

	int deleteNotice(int noticeNo);

	int updateNotice(ArtistNotice inputNotice);

	int updatePurchase(Purchase purchase);

	Purchase selectPurchaseDetails(int purchaseNo);

	Board selectBoardReport(int boardNo);

	Comment selectBoardCommentReport(int boardCommentNo);

	MediaComment selectMediaCommentReport(int mediaCommentNo);

	int deleteReport(Map<String, Object> map);

	int proceedReport(Map<String, Object> map);

}
