package com.TeamFiestar.Fiestar.cart.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.dto.Orderer;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Transactional
public interface CartService {
	
	
	
	/** 로그인 한 회원의 장바구니 조회
	 * @param memberNo 로그인한 회원의 회원 번호
	 * @return 장바구니 내용 조회
	 */
	List<Cart> cartPage(int memberNo);

	int updateCart(Cart cart);

	int deleteCart(int cartNo);

	List<Cart> checkout(String selectNo);

	List<Cart> checkoutResult(String selectNo);

	int order(Orderer inputOrderer, String[] ordererAddress, String selectNo);




	
}
