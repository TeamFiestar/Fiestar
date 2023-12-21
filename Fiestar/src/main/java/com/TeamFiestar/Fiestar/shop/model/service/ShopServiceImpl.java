package com.TeamFiestar.Fiestar.shop.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.shop.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.dto.ProductOption;
import com.TeamFiestar.Fiestar.shop.model.dto.ShopPagination;

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
		map.put("shopCount", shopCount);
		return map;
	}
	
	
	//쇼핑몰 검색 상품 조회
	@Override
	public Map<String, Object> searchList(Map<String, Object> paramMap, int cp) {
		
		int shopCount = mapper.shopSearchCount(paramMap);
		
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
		map.put("shopCount", shopCount);
		return map;
	}
	
	//아티스트 그룹별 상품 조회
	@Override
	public Map<String, Object> artistGroupShop(Map<String, Object> paramMap, int cp) {
		
		int shopCount = mapper.shopGroupCount(paramMap);
		
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
		map.put("shopCount", shopCount);
		return map;
	}
	
	
	
	//상품 전체 조회 후 정렬
	@Override
	public List<Product> selectAllSort(Map<String, Object> paramMap, int cp) {
		
		int shopCount = mapper.shopCount();
		
		ShopPagination pagination = new ShopPagination(cp, shopCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		
		return mapper.selectAllSort(paramMap, rowBounds);
	}

	
	//그룹별 상품 조회 후 정렬
	@Override
	public List<Product> selectGroupSort(Map<String, Object> paramMap, int cp) {
		
		int shopCount = mapper.shopGroupCount(paramMap);
		
		ShopPagination pagination = new ShopPagination(cp, shopCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return mapper.selectGroupSort(paramMap,rowBounds);
	}
	
	//상품 검색 조회 후 정렬
	@Override
	public List<Product> selectSearchSort(Map<String, Object> paramMap, int cp) {
		
		int shopCount = mapper.shopSearchCount(paramMap);
		
		ShopPagination pagination = new ShopPagination(cp, shopCount);

		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		
		return mapper.selectSearchSort(paramMap, rowBounds);
	}
	
	
	//상품 상세 조회
	@Override
	public Map<String, Object> shopDetail(int productNo) {
		
		List<ProductOption> optionList = mapper.selectOption(productNo); //옵션을 리스트 형태로 조회
		
		Product detail = mapper.shopDetail(productNo); //디테일에 필요한 요소 조회
		
		Map<String, Object> map = new HashMap<>();
		map.put("optionList", optionList);
		map.put("detail", detail);
		
		return map;
	}
	
	//장바구니에 담기
	@Override
	public int insertCart(int productNo, int productCount, int totalPrice, int memberNo) {
		
		Map<String, Integer> map = new HashMap<>();
		map.put("productNo", productNo);
		map.put("productCount", productCount);
		map.put("memberNo", memberNo);
		map.put("totalPrice", totalPrice);
		
		int result = mapper.insertCart(map);
		
		if(result > 0)	return map.get("cartNo");
		
		return 0;
	}
	
	

	
	

	

		

	
	
}
