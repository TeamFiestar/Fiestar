package com.TeamFiestar.Fiestar.shop.model.service;

import java.util.Map;

public interface ShopService {

	
	Map<String, Object> shopMain();
	
	int shopCount();

	Map<String, Object> searchList(Map<String, Object> paramMap);
	
	int shopSearchCount(Map<String, Object> paramMap);

	Map<String, Object> artistGroupShop(Map<String, Object> paramMap);

	int shopGroupCount(Map<String, Object> paramMap);

	

	


	
	




	


}
