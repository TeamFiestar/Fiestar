package com.TeamFiestar.Fiestar.scheduling.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.scheduling.mapper.SchedulingMapper;


@Service
public class SchedulingServiceImpl implements SchedulingService{

	@Autowired
	private SchedulingMapper mapper;
	
	@Override
	public List<String> selectDbImageList() {
		return mapper.artistList();
	}
}
