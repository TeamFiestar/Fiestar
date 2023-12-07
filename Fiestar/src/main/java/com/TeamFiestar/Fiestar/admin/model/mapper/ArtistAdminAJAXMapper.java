package com.TeamFiestar.Fiestar.admin.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;


@Mapper
public interface ArtistAdminAJAXMapper {

	ArtistNotice selectArtistNotice(int artistGroupNo);

}
