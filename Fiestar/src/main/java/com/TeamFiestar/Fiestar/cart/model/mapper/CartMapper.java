package com.TeamFiestar.Fiestar.cart.model.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;

@Mapper
public interface CartMapper {

	List<Cart> cartPage(int memberNo);

	int updateCart(Cart cart);

	int deleteCart(int cartNo);


}
