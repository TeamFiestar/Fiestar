package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface adminMapper {

	List<Member> selectMember();

	List<Board> selectBoard(int memberNo);

}
