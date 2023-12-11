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
	
	//쇼피몰 메인페이지 전체 조회
	@Override
	public Map<String, Object> shopMain() {
		
		List<Product> productList = mapper.shopMain();
		List<ArtistGroup> artistList = mapper.artistSelect();
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("artistList", artistList);
		return map;
	}
	
	//쇼핑몰 상품 전체 개수
	@Override
	public int shopCount() {
		return mapper.shopCount();
	}
	
	//쇼핑몰 검색 상품 조회
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap) {
		List<Product> productList = mapper.searchList(paramMap);
		List<ArtistGroup> artistList = mapper.artistSelect();
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("artistList", artistList);
		return map;
	}
	
	//쇼핑몰 검색 상품 개수
	@Override
	public int shopSearchCount(Map<String, Object> paramMap) {
		return mapper.shopSearchCount(paramMap);
	}
	
	//아티스트 그룹별 상품 조회
	@Override
	public Map<String, Object> artistGroupShop(Map<String, Object> paramMap) {
	
		List<Product> productList = mapper.artistGroupShop(paramMap);
		List<ArtistGroup> artistList = mapper.artistSelect();
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("artistList", artistList);
		return map;
	}
	
	//아티스트 그룹별 상품 개수
	@Override
	public int shopGroupCount(Map<String, Object> paramMap) {
		
		return mapper.shopGroupCount(paramMap);
	}
	
	//상품 전체 조회 후 정렬
	@Override
	public List<Product> selectAllSort(Map<String, Object> paramMap) {
		
		return mapper.selectAllSort(paramMap);
	}

	
	//그룹별 상품 조회 후 정렬
	@Override
	public List<Product> selectGroupSort(Map<String, Object> paramMap) {
		
		return mapper.selectGroupSort(paramMap);
	}
	
	//상품 검색 조회 후 정렬
	@Override
	public List<Product> selectSearchSort(Map<String, Object> paramMap) {
		
		return mapper.selectSearchSort(paramMap);
	}
	

		

	
	
}
