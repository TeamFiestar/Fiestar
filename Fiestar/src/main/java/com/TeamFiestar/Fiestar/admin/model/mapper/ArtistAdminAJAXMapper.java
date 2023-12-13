package com.TeamFiestar.Fiestar.admin.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;


@Mapper
public interface ArtistAdminAJAXMapper {

	ArtistNotice selectArtistNotice(Map<String, Object> map);

	int deleteNotice(int noticeNo);

	int updateNotice(ArtistNotice inputNotice);

	int updatePurchase(Purchase purchase);


}
