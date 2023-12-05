package com.TeamFiestar.Fiestar.shop.model.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

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
		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		
		return map;
	}
	
	
	@Override
	public int shopMainCount() {
		
		return mapper.shopMainCount();
	}
	
	
	

	
	
}
