package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface adminService {

	Map<String, Object> selectMember(Member member, int cp);

	List<Board> selectBoard(int memberNo);

	Map<String, Object> searchMember(Map<String, Object> paramMap, int cp);


}
