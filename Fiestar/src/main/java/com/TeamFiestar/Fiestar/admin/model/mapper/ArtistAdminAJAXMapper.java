package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.Map;

import javax.xml.stream.events.Comment;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.media.model.dto.MediaComment;


@Mapper
public interface ArtistAdminAJAXMapper {

	ArtistNotice selectArtistNotice(Map<String, Object> map);

	int deleteNotice(int noticeNo);

	int updateNotice(ArtistNotice inputNotice);

	int updatePurchase(Purchase purchase);

	Purchase selectPurchaseDetails(int purchaseNo);

	Board selectBoardReport(int boardNo);

	Comment selectBoardCommentReport(int boardCommentNo);

	MediaComment selectMediaCommentReport(int mediaCommentNo);


}
