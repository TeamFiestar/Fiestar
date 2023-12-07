package com.TeamFiestar.Fiestar.shop.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.shop.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;



@Mapper
public interface ShopMapper {
	
	//쇼핑몰 메인페이지 전체 조회
	List<Product> shopMain();
	
	//쇼핑몰 상품 전체 개수
	int shopCount();

	//쇼핑몰 검색한 상품 조회
	List<Product> searchList(Map<String, Object> paramMap);

	//쇼핑몰 검색한 상품 개수
	int shopSearchCount(Map<String, Object> paramMap);

	
	List<ArtistGroup> artistSelect();

	//아티스트 그룹별로 상품 조회
	List<Product> artistGroupShop(Map<String, Object> paramMap);

	//아티스트 그룹별 상품 개수
	int shopGroupCount(Map<String, Object> paramMap);

	//높은 금액순으로 상품 정렬
	List<Product> selectHigh(Map<String, Object> paramMap);




	


	

}
