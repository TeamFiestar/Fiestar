package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.mapper.adminMapper;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class adminServiceImpl implements adminService{

	private final adminMapper mapper;
	
	@Override
	public List<Member> selectMember() {
		return mapper.selectMember();
	}
	
	@Override
	public List<Board> selectBoard(int memberNo) {
		return mapper.selectBoard(memberNo);
	}
}
