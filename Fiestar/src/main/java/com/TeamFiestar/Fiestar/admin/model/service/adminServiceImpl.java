package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.AdminPagination;
import com.TeamFiestar.Fiestar.admin.model.mapper.adminMapper;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class adminServiceImpl implements adminService{

	private final adminMapper mapper;
	
	@Override
	public Map<String, Object> selectMember(Member member, int cp) {
		int countList = mapper.countList(member);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> memberList = mapper.selectMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberList", memberList);
		map.put("pagination", pagination);
		return map;
	}
	
	
	@Override
	public Map<String, Object> searchMember(Map<String, Object> paramMap, int cp) {
		int countList = mapper.countMember(paramMap);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> memberList = mapper.searchMember(rowBounds, paramMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberList", memberList);
		map.put("pagination", pagination);
		return map;
	}
	
	
	@Override
	public List<Board> selectBoard(int memberNo) {
		return mapper.selectBoard(memberNo);
	}
	
	
	@Override
	public Map<String, Object> deleteMember(Member member, int cp) {
		int countList = mapper.countDeleteMember(member);
		
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> deleteList = mapper.deleteMember(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("deleteList", deleteList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	@Override
	public Map<String, Object> searchDeleteMember(Map<String, Object> paramMap, int cp) {
		int countList = mapper.countDeleteList(paramMap);
	
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> deleteList = mapper.searchDelete(rowBounds, paramMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("deleteList", deleteList);
		map.put("pagination", pagination);
		
		return map;
	}
	
}
