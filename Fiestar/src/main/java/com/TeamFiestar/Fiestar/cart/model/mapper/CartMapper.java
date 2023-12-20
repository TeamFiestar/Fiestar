package com.TeamFiestar.Fiestar.cart.model.mapper;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.dto.Orderer;
import com.TeamFiestar.Fiestar.cart.model.dto.PurchaseInfo;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.member.model.dto.PurchaseList;

@Mapper
public interface CartMapper {

	List<Cart> cartPage(int memberNo);

	int updateCart(Cart cart);

	int deleteCart(int cartNo);

	List<Cart> checkout(String selectNo);

	int order(Orderer inputOrderer);

	int insertPurchaseList(Map<String, Object> map);

	int deleteOrderCart(String selectNo);

	Orderer checkoutResult(Map<String, Object> map);

//	int selectPurchaseNo(Map<String, Object> map);

	int selectPurchaseNo(int purchaseNo);

	Object insertPurchaseList(int purchaseNo);

	PurchaseInfo selectPurchaseInfo(int purchaseNo);

	List<PurchaseList> selectPurchaseList(int purchaseNo);


	

	

	

}
