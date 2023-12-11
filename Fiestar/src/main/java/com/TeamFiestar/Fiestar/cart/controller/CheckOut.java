package com.TeamFiestar.Fiestar.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.service.CartService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Controller
public class CheckOut {
	
	    @Autowired
	    private CartService cartService;
	    @PostMapping("/checkout")
	    public String checkout(Model model, 
	                           @SessionAttribute(value = "loginMember", required = false) Member loginMember, 
	                           @RequestParam("cartNo") int cartNo) {
	    	
	    	
	   
	        // 사용자의 장바구니 데이터를 조회
	        List<Cart> cartList = cartService.checkout(loginMember.getMemberNo());

	        // 선택된 장바구니 항목 조회
	        List<Cart> checkout = cartService.checkout(cartNo);

	        // 뷰에 데이터 전달
	        model.addAttribute("cartList", cartList);

	        return "checkout"; // 결제 화면 페이지 뷰 이름
	    }

}
