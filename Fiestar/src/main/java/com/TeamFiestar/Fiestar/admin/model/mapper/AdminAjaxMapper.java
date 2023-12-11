package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Mapper
public interface AdminAjaxMapper {

	List<Board> selectBoard(int memberNo);
	
	int countDelMember(Member member);

	List<Member> delMember(RowBounds rowBounds);

	int countSearchDelMember(Map<String, Object> paramMap);

	List<Member> searchDelMember(RowBounds rowBounds);

	int update(Map<String, Object> paramMap);

	int withDraw(Map<String, Object> paramMap);

	int countMember(Member member);

	List<Member> selectMember(RowBounds rowBounds);

	int countSearchMember(Map<String, Object> paramMap);

	List<Member> searchMember(RowBounds rowBounds);

	String nickname(int memberNo);
}
