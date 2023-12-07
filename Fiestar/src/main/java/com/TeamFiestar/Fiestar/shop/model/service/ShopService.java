package com.TeamFiestar.Fiestar.shop.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.shop.model.dto.Product;

public interface ShopService {

	
	/**쇼핑몰 메인페이지 전체 조회
	 * @return
	 */
	Map<String, Object> shopMain();
	
	/**쇼핑몰 상품 전체 개수
	 * @return
	 */
	int shopCount();

	/**쇼핑몰 검색한 상품 조회
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> searchList(Map<String, Object> paramMap);
	
	/**쇼핑몰 검색한 상품 개수
	 * @param paramMap
	 * @return
	 */
	int shopSearchCount(Map<String, Object> paramMap);

	/**아티스트 그룹별로 상품 조회
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> artistGroupShop(Map<String, Object> paramMap);

	/**아티스트 그룹별 상품 개수
	 * @param paramMap
	 * @return
	 */
	int shopGroupCount(Map<String, Object> paramMap);

	/**정렬
	 * @param paramMap 
	 * @return
	 */
	List<Product> selectSort(Map<String, Object> paramMap);




	

	


	
	




	


}
