package com.TeamFiestar.Fiestar.artist.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.artist.model.dto.Artist;

@Mapper
public interface ArtistMapper {

	String artistGroupBackimg(String artistGroupTitle);

	List<Artist> artist(int artistGroupNo);

}
