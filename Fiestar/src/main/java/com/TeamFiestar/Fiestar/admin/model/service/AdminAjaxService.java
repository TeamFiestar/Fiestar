package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;
import java.util.Map;

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
}
