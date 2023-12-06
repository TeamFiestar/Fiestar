package com.TeamFiestar.Fiestar.cart.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
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


	
}
