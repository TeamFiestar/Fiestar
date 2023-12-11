package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.AdminPagination;
import com.TeamFiestar.Fiestar.admin.model.mapper.AdminAjaxMapper;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAjaxServiceImpl implements AdminAjaxService{

	private final AdminAjaxMapper mapper;
	
	@Override
	public List<Board> selectBoard(int memberNo) {
		return mapper.selectBoard(memberNo);
	}
	
	@Override
	public String nickname(int memberNo) {
		return mapper.nickname(memberNo);
	}
	
	@Override
	public Map<String, Object> selectDelMember(Member member, int cp) {
		int countList = mapper.countDelMember(member);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> deleteList = mapper.delMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("deleteList", deleteList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	@Override
	public Map<String, Object> searchDelMember(Map<String, Object> paramMap, int cp) {
		int countList = mapper.countSearchDelMember(paramMap);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> deleteList = mapper.searchDelMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("deleteList", deleteList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	
	@Override
	public int update(Map<String, Object> paramMap) {
		return mapper.update(paramMap);
	}
	
	@Override
	public int withDraw(Map<String, Object> paramMap) {
		return mapper.withDraw(paramMap);
	}
	
	@Override
	public Map<String, Object> selectMember(Member member, int cp) {
		int countList = mapper.countMember(member);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> selectList = mapper.selectMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("selectList", selectList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	@Override
	public Map<String, Object> searchMember(Map<String, Object> paramMap, int cp) {
		int countList = mapper.countSearchMember(paramMap);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> selectList = mapper.searchMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("selectList", selectList);
		map.put("pagination", pagination);
		
		return map;
	}
}
