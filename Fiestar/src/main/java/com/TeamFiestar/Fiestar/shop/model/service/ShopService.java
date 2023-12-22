package com.TeamFiestar.Fiestar.shop.model.service;

import java.util.List;
import java.util.Map;

import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;

public interface ShopService {

	
	/**쇼핑몰 메인페이지 전체 조회
	 * @return
	 */
	Map<String, Object> shopMain(int cp);
	
	

	/**쇼핑몰 검색한 상품 조회
	 * @param paramMap
	 * @param cp 
	 * @return
	 */
	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);


	/**아티스트 그룹별로 상품 조회
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> artistGroupShop(Map<String, Object> paramMap, int cp);


	
	/**상품 전체 조회 후 정렬
	 * @param paramMap
	 * @return
	 */
	List<Product> selectAllSort(Map<String, Object> paramMap, int cp);
	
	
	/**그룹별 상품 조회 후 정렬
	 * @param paramMap 
	 * @param cp 
	 * @return
	 */
	List<Product> selectGroupSort(Map<String, Object> paramMap, int cp);

	
	/**상품 검색 조회 후 정렬
	 * @param paramMap
	 * @param cp 
	 * @return
	 */
	List<Product> selectSearchSort(Map<String, Object> paramMap, int cp);



	/**상품 상세조회
	 * @param productNo
	 * @return
	 */
	Map<String, Object> shopDetail(int productNo);


	/**장바구니에 담기
	 * @param productNo
	 * @param productCount
	 * @param memberNo 
	 * @param memberNo2 
	 * @return
	 */
	int insertCart(int productNo, int productCount, int totalPrice, int memberNo);



	

	



	

	



	

	


	
	




	


}
