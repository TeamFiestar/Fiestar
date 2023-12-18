package com.TeamFiestar.Fiestar.artist.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.artist.model.dto.Artist;
import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;

@Mapper
public interface ArtistMapper {

	List<Artist> artist(int artistGroupNo);

	List<Media> artistGroupMedia(RowBounds rowBounds, int artistGroupNo);

	List<Product> artistGroupProduct(RowBounds rowBounds2, int artistGroupNo);

	List<ArtistNotice> artistGroupNotice(RowBounds rowBounds3, int artistGroupNo);

	List<ArtistGroup1> artistGroup(String artistGroupTitle);

	int subscribe(Map<String, Object> map);

	int update(Map<String, Object> map);

//	int update(String artistGroupTitle, int memberNo);

	ArtistGroup1 artistUpdate(int memberNo);

	List<Artist> artistList(int artistGroupNo);

	int selectArtistGroupNo(int memberNo);

	int artistProfile(int artistGroupNo);

	int artistProfileUpdate(ArtistGroup1 artistGroup);

	int checkSubscribe(Map<String, Object> checkMap);

}
