package com.TeamFiestar.Fiestar.shop.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.shop.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;



@Mapper
public interface ShopMapper {

	List<Product> shopMain();
	
	int shopCount();

	List<Product> searchList(Map<String, Object> paramMap);

	int shopSearchCount(Map<String, Object> paramMap);

	List<ArtistGroup> artistSelect();

	List<Product> artistGroupShop(Map<String, Object> paramMap);

	int shopGroupCount(Map<String, Object> paramMap);



	


	

}
