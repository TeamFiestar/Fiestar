package com.TeamFiestar.Fiestar.shop.model.service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.TeamFiestar.Fiestar.common.utility.Util;
import com.TeamFiestar.Fiestar.shop.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductImage;
import com.TeamFiestar.Fiestar.shop.model.dto.ShopPagination;
import com.TeamFiestar.Fiestar.shop.model.excption.shopException;
import com.TeamFiestar.Fiestar.shop.model.mapper.ShopMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl implements ShopService{

	private final ShopMapper mapper;
	
	

	
	
	
	//쇼피몰 메인페이지 전체 조회
	@Override
	public Map<String, Object> shopMain(int cp) {
		
		int shopCount = mapper.shopCount();
		
		ShopPagination pagination = new ShopPagination(cp, shopCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Product> productList = mapper.shopMain(rowBounds);
		List<ArtistGroup> artistList = mapper.artistSelect();
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("artistList", artistList);
		map.put("pagination", pagination);
		return map;
	}
	
	//쇼핑몰 상품 전체 개수
	@Override
	public int shopCount() {
		return mapper.shopCount();
	}
	
	//쇼핑몰 검색 상품 조회
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		
		int shopCount = mapper.shopCount();
		
		ShopPagination pagination = new ShopPagination(cp, shopCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Product> productList = mapper.searchList(paramMap, rowBounds);
		List<ArtistGroup> artistList = mapper.artistSelect();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("productList", productList);
		map.put("artistList", artistList);
		map.put("pagination", pagination);
		return map;
	}
	
	//쇼핑몰 검색 상품 개수
	@Override
	public int shopSearchCount(Map<String, Object> paramMap) {
		return mapper.shopSearchCount(paramMap);
	}
	
	//아티스트 그룹별 상품 조회
	@Override
	public Map<String, Object> artistGroupShop(Map<String, Object> paramMap, int cp) {
		
		int shopCount = mapper.shopCount();
		
		ShopPagination pagination = new ShopPagination(cp, shopCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
	
		List<Product> productList = mapper.artistGroupShop(paramMap, rowBounds);
		List<ArtistGroup> artistList = mapper.artistSelect();
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("artistList", artistList);
		map.put("pagination", pagination);
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
	
	//상품 상세 조회
	@Override
	public Product shopDetail(int productNo) {
		
		
		return mapper.shopDetail(productNo);
	}
	

		

	
	
}
