package com.TeamFiestar.Fiestar.admin.model.service;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistProfileMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistProfileServiceImpl implements ArtistProfileService{
	
	private final ArtistProfileMapper mapper;

}
