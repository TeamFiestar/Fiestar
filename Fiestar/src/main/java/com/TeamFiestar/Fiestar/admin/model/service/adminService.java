package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface adminService {

	Map<String, Object> selectMember(Member member, int cp);

	List<Board> selectBoard(int memberNo);

	Map<String, Object> searchMember(Map<String, Object> paramMap, int cp);

	Map<String, Object> deleteMember(Member member, int cp);

	Map<String, Object> searchDeleteMember(Map<String, Object> paramMap, int cp);

	int update(Map<String, Object> paramMap);

//	int restoration(int memberNo);

	Map<String, Object> subscribeMember(Member member, int cp, int artistGroupNo);

	Map<String, Object> searchSubscribe(Map<String, Object> paramMap, int cp, int artistGroupNo);


	


}
