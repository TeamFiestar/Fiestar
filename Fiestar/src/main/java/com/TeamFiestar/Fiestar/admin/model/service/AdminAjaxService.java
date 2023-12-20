package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.SiteNotice;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface AdminAjaxService {

	List<Board> selectBoard(int memberNo);
	
	Map<String, Object> selectDelMember(Member member, int cp);

	Map<String, Object> searchDelMember(Map<String, Object> paramMap, int cp);

	int update(Map<String, Object> paramMap);

	int withDraw(Map<String, Object> paramMap);

	Map<String, Object> selectMember(Member member, int cp);

	Map<String, Object> searchMember(Map<String, Object> paramMap, int cp);

	String nickname(int memberNo);

	int changeAuthority(Map<String, Object> paramMap);

	SiteNotice selectSiteNotice(Map<String, Object> map);

	int deleteNotice(int noticeNo);

	int updateNotice(SiteNotice inputNotice);

	List<Board> selectSubscribeBoard(String artistGroupTitle, int memberNo);

	int groupDelete(Map<String, Object> paramMap);

	
}
