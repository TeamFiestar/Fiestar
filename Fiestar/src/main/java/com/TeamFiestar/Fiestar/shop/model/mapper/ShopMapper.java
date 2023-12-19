package com.TeamFiestar.Fiestar.shop.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.TeamFiestar.Fiestar.shop.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductImage;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductOption;



@Mapper
public interface ShopMapper {
	
	//쇼핑몰 메인페이지 전체 조회
	List<Product> shopMain(RowBounds rowBounds);
	
	//쇼핑몰 상품 전체 개수
	int shopCount();

	//쇼핑몰 검색한 상품 조회
	List<Product> searchList(Map<String, Object> paramMap, RowBounds rowBounds);

	//쇼핑몰 검색한 상품 개수
	int shopSearchCount(Map<String, Object> paramMap);

	
	/**아티스트 그룹 조회
	 * @return
	 */
	List<ArtistGroup> artistSelect();
	

	//아티스트 그룹별로 상품 조회
	List<Product> artistGroupShop(Map<String, Object> paramMap, RowBounds rowBounds);

	//아티스트 그룹별 상품 개수
	int shopGroupCount(Map<String, Object> paramMap);

	//상품 전체 조회 후 정렬
	List<Product> selectAllSort(Map<String, Object> paramMap, RowBounds rowBounds);
	
	//그룹별 상품 조회 후 정렬
	List<Product> selectGroupSort(Map<String, Object> paramMap, RowBounds rowBounds);

	//상품 검색 조회 후 정렬
	List<Product> selectSearchSort(Map<String, Object> paramMap, RowBounds rowBounds);

	//상품 상세 조회
	Product shopDetail(int productNo);

	List<ProductOption> selectOption(int productNo);

	




	




	


	

}
