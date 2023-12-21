package com.TeamFiestar.Fiestar.cart.model.service;

  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.dto.OrderedList;
import com.TeamFiestar.Fiestar.cart.model.dto.Orderer;
import com.TeamFiestar.Fiestar.cart.model.dto.PurchaseInfo;
import com.TeamFiestar.Fiestar.cart.model.mapper.CartMapper;




@Service

public class CartServiceImpl implements CartService{
	
	// 데이터 가공, DB 연결 등 비즈니스 로직을 처리
	@Autowired
	public CartMapper mapper;
	
	@Override
	public List<Cart> cartPage(int memberNo) {
		
		return mapper.cartPage(memberNo);
	}
	
	@Override
	public int updateCart(Cart cart) {
		// TODO Auto-generated method stub
		return mapper.updateCart(cart);
	}
	
	@Override
	public int deleteCart(int cartNo) {
		return mapper.deleteCart(cartNo);
	}
	
	@Override
	public List<Cart> checkout(String selectNo) {
		return mapper.checkout(selectNo);
	}
	
	
	@Transactional
	@Override
	public int order(Orderer inputOrderer, String[] ordererAddress, String selectNo) {
		// TODO Auto-generated method stub
		String address = String.join("^^^", ordererAddress);
		inputOrderer.setOrdererAddress(address);
		
		// 주문(PURCHASE 테이블 INSERT)
		int purchaseNo = 0;
		
		int result = mapper.order(inputOrderer);
		if(result == 0) return 0;
		
		
		purchaseNo = inputOrderer.getPurchaseNo();
		
		// 주문한 상품(PURCHASE_LIST 테이블 INSERT)
		
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("selectNo", selectNo);
		map.put("purchaseNo", purchaseNo);
		
		result = mapper.insertPurchaseList(map);
		
		if(result > 0) {
			// CART 삭제
			result = mapper.deleteOrderCart(selectNo);
			
			if(result > 0) {
				return purchaseNo;
			}
		}
		
		return 0;
	}
	
	
	
	
	@Override
	public Map<String, Object> checkoutResult(int purchaseNo) {
		
		
//		purchaseNo = inputOrderer.getPurchaseNo();
//		// 주문(PURCHASE 테이블 INSERT)
////			int purchaseNo = map.purchaseNo(
//
////			int result = mapper.selectPurchaseNo(purchaseNo);
////			맵으로 전달한 purchaseNo, purchase
//			
//			purchaseNo = orderedInfo.get
//			return mapper.checkoutResult(map);
	
		PurchaseInfo purchaseInfo = mapper.selectPurchaseInfo(purchaseNo); 
		
		List<OrderedList> orderedList = mapper.selectOrderedList(purchaseNo);
		
//		List<OrderedList> purchaseList = mapper.selectPurchaseList(purchaseNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("purchaseInfo", purchaseInfo);
		map.put("orderedList", orderedList);
		
		return map;
	}
	
	
	
	

}


	