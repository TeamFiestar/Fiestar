package com.TeamFiestar.Fiestar.shop.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.shop.model.dto.Product;



@Mapper
public interface ShopMapper {

	List<Product> shopMain();

	int shopMainCount();


	

}
