package com.TeamFiestar.Fiestar.cart.model.service;

import java.util.HashMap;   
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.dto.Orderer;
import com.TeamFiestar.Fiestar.cart.model.mapper.CartMapper;
import com.TeamFiestar.Fiestar.member.model.dto.Member;



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
	
	
	
	@Override
	public int order(Orderer inputOrderer, String[] ordererAddress) {
		// TODO Auto-generated method stub
		String address = String.join("^^^", ordererAddress);
		inputOrderer.setOrdererAddress(address);
		
		return mapper.order(inputOrderer);
	}
	
	 @Override
	    @Transactional
	    public void insertPurchaseListItems(Orderer orderer) {
	        int purchaseNo = orderer.getPurchaseNo();
	        List<Cart> selectedCarts = orderer.getCheckout();

	        for (Cart cartItem : selectedCarts) {
	            // PURCHASE_LIST에 삽입할 데이터 설정
	            orderer.setProductNo(cartItem.getProductNo());
	            orderer.setProductCount(cartItem.getProductCount());
	            orderer.setProductPrice(cartItem.getProductPrice());

	            // PURCHASE_LIST 테이블에 데이터 삽입
	            mapper.insertPurchaseList(orderer);
	        }
	    }
}


	