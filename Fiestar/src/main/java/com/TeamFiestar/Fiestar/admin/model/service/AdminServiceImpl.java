package com.TeamFiestar.Fiestar.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.AdminPagination;
import com.TeamFiestar.Fiestar.admin.model.mapper.AdminMapper;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;
	
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
	
	
	
	
	@Override
	public Map<String, Object> subscribeMember(Member member, int cp, int artistGroupNo) {
		
		int countList = mapper.countSubscribe(artistGroupNo);
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> subscribeList = mapper.subscribeMember(rowBounds, artistGroupNo);
		String artistGroupTitle = mapper.subArtistTitle(artistGroupNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("subscribeList", subscribeList);
		map.put("pagination", pagination);
		map.put("artistGroupTitle", artistGroupTitle);
		
		return map;
	}
	
	
	@Override
	public Map<String, Object> searchSubscribe(Map<String, Object> paramMap, int cp, int artistGroupNo) {
		paramMap.put("artistGroupNo", artistGroupNo);
		
		int countList = mapper.countSearchSubscribe(paramMap);
		AdminPagination pagination = new AdminPagination(countList, cp);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> subscribeList = mapper.searchSubscribe(paramMap, rowBounds);
		Map<String, Object> map = new HashMap<>();
		map.put("subscribeList", subscribeList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	
	@Override
	public List<Board> selectSubscribeBoard(int memberNo, int artistGroupNo) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("artistGroupNo", artistGroupNo);
		return mapper.selectSubscribeBoard(map);
	}
	
//	@Override
//	public int restoration(int memberNo) {
//		return mapper.restoration(memberNo);
//	}
	
}
