package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

public interface adminService {

	List<Member> selectMember();

	List<Board> selectBoard(int memberNo);

}
