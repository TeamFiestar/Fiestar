package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface adminMapper {

//	List<Member> selectMember();

	List<Board> selectBoard(int memberNo);

	int countList(Member member);

	List<Member> selectMember(RowBounds rowBounds);

	int countMember(Map<String, Object> paramMap);

	List<Member> searchMember(RowBounds rowBounds, Map<String, Object> paramMap);

	int countDeleteMember(Member member);

	List<Member> deleteMember(RowBounds rowBounds);

	int countDeleteList(Map<String, Object> paramMap);

	List<Member> searchDelete(RowBounds rowBounds, Map<String, Object> paramMap);

	

}
