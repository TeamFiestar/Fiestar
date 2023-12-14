package com.TeamFiestar.Fiestar.main.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.main.model.mapper.MainMapper;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{

	private final MainMapper mapper;
	
	
	@Override
	public Map<String, Object> main() {
		Map<String, Object> map = new HashMap<>();
		List<ArtistGroup1> artistGroupList = mapper.main();
		map.put("artistGroupList", artistGroupList);
		
		return map;
	}
	
	@Override
	public Map<String, Object> mainLogin(int memberNo) {
		Map<String, Object> map = new HashMap<>();

		List<ArtistGroup1> subscribeGroupList = mapper.subscribeMain(memberNo);
		map.put("subscribeGroupList", subscribeGroupList);

		return map;
	}
}
