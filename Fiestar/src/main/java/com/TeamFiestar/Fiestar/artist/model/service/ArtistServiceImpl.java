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
		String artistGroupMainimg = mapper.artistGroupMainimg(artistGroupTitle);
		int artistGroupNo = artistAdminMapper.selectArtistGroupNo(artistGroupTitle);
		List<Artist> artist = mapper.artist(artistGroupNo);
		RowBounds rowBounds = new RowBounds(0,6);
		List<Media> artistGroupMedia = mapper.artistGroupMedia(rowBounds, artistGroupNo);
		RowBounds rowBounds2 = new RowBounds(0,8);
		List<Product> artistGroupProduct = mapper.artistGroupProduct(rowBounds2,artistGroupNo);
		RowBounds rowBounds3 = new RowBounds(0,3);
		List<ArtistNotice> artistGroupNotice = mapper.artistGroupNotice(rowBounds3, artistGroupNo);
		map.put("artistGroupMainimg", artistGroupMainimg);
		map.put("artist", artist);
		map.put("artistGroupMedia", artistGroupMedia);
		map.put("artistGroupProduct", artistGroupProduct);
		map.put("artistGroupNotice", artistGroupNotice);
		return map;
	}

}
