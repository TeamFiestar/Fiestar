package com.TeamFiestar.Fiestar.artist.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.mapper.ArtistAdminMapper;
import com.TeamFiestar.Fiestar.artist.model.dto.Artist;
import com.TeamFiestar.Fiestar.artist.model.mapper.ArtistMapper;
import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService{
	private final ArtistMapper mapper;
	private final ArtistAdminMapper artistAdminMapper;
	
	@Override
	public Map<String, Object> artistMember(String artistGroupTitle) {
		
		Map<String, Object> map = new HashMap<>();
		List<ArtistGroup1> artistGroup = mapper.artistGroup(artistGroupTitle);
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		List<Artist> artistList = mapper.artist(artistGroupNo);
		RowBounds rowBounds = new RowBounds(0,6);
		List<Media> artistGroupMedia = mapper.artistGroupMedia(rowBounds, artistGroupNo);
		RowBounds rowBounds2 = new RowBounds(0,8);
		List<Product> artistGroupProduct = mapper.artistGroupProduct(rowBounds2,artistGroupNo);
		RowBounds rowBounds3 = new RowBounds(0,3);
		List<ArtistNotice> artistGroupNotice = mapper.artistGroupNotice(rowBounds3, artistGroupNo);
		map.put("artistGroup", artistGroup);
		map.put("artistList", artistList);
		map.put("artistGroupMedia", artistGroupMedia);
		map.put("artistGroupProduct", artistGroupProduct);
		map.put("artistGroupNotice", artistGroupNotice);
		return map;
	}
	
	@Override
	public int subscribe(int memberNo, String artistGroupTitle) {
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("artistGroupNo", artistGroupNo);
		return mapper.subscribe(map);
	}
	
	@Override
	public Map<String, Object> artist(int memberNo) {
		int artistGroupNo = mapper.selectArtistGroupNo(memberNo);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("artistGroupNo", artistGroupNo);
		
		int result = mapper.update(map);
		
		if(result == 1) {
			ArtistGroup1 artistGroup = mapper.artistUpdate(memberNo);
			List<Artist> artistList = mapper.artistList(artistGroupNo);
			map.put("artistGroup", artistGroup);
			map.put("artistList", artistList);
			return map;
		}
		return map;
	}
	
//	@Override
//	public int update(String artistGroupTitle, int memberNo) {
//		return mapper.update(artistGroupTitle,memberNo);
//	}

}
