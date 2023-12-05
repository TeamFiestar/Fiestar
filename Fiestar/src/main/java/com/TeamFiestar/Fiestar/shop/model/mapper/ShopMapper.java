package com.TeamFiestar.Fiestar.shop.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.shop.model.dto.Product;



@Mapper
public interface ShopMapper {

	List<Product> shopMain(Map<String, Object> paramMap);

	int shopMainCount();

	List<Product> searchList();

	


	

}
