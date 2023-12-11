package com.TeamFiestar.Fiestar.cart.model.mapper;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;

@Mapper
public interface CartMapper {

	List<Cart> cartPage(int memberNo);
}
