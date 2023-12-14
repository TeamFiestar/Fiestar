package com.TeamFiestar.Fiestar.main.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;

@Mapper
public interface MainMapper {

	List<ArtistGroup1> main();

	List<ArtistGroup1> subscribeMain(int memberNo);

}
