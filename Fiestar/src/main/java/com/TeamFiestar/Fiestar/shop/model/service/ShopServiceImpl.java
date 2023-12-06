package com.TeamFiestar.Fiestar.shop.model.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.shop.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{

	private final ShopMapper mapper;
	
	@Override
	public Map<String, Object> shopMain() {
		
		List<Product> productList = mapper.shopMain();
		List<ArtistGroup> artistList = mapper.artistSelect();
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("artistList", artistList);
		return map;
	}
	
	@Override
	public int shopCount() {
		return mapper.shopCount();
	}
	
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap) {
		List<Product> productList = mapper.searchList(paramMap);
		List<ArtistGroup> artistList = mapper.artistSelect();
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("artistList", artistList);
		return map;
	}
	
	
	@Override
	public int shopSearchCount(Map<String, Object> paramMap) {
		return mapper.shopSearchCount(paramMap);
	}
	
	
	@Override
	public Map<String, Object> artistGroupShop(Map<String, Object> paramMap) {
	
		List<Product> productList = mapper.artistGroupShop(paramMap);
		List<ArtistGroup> artistList = mapper.artistSelect();
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("artistList", artistList);
		return map;
	}
	
	@Override
	public int shopGroupCount(Map<String, Object> paramMap) {
		
		return mapper.shopGroupCount(paramMap);
	}
		

	

		

	
	
}
