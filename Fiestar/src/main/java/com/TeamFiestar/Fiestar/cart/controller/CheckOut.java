package com.TeamFiestar.Fiestar.cart.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.service.CartService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;


@Controller
public class CheckOut {
	
	    @Autowired
	    private CartService cartService;
	    
	    @PostMapping("checkout")
	    
	    public String checkout(Model model, 
	                           @SessionAttribute(value = "loginMember", required = false) Member loginMember, 
	                           @RequestParam("selectEach") List<String> cartNoList, Member member) {
	    	
	    	
	    	// List를   ","  ex) ([2, 3, 5] 2, 3, 5) 로 구분되는 String으로 변환하는 방법
	    	// cartNoList -> "9,13,15"
	    	//https://yjh5369.tistory.com/entry/java-List%EB%A5%BC-%EB%AC%B8%EC%9E%90%EC%97%B4%EB%A1%9C-Join%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95
	    	
	    	Set<String> set = new LinkedHashSet<>(cartNoList);
	    	String selectNo = String.join(",", set);
	    	
	        // 선택된 장바구니 항목 조회
	    	
	        List<Cart> checkout = cartService.checkout(selectNo);
	        
	        int totalQuantity = 0;
	        int totalPrice = 0;
	        int purchasePrice = 0;
	        
	        
	        for (Cart cart : checkout) {
	            totalQuantity += cart.getProductCount(); // 여기서 getQuantity()는 Cart 객체의 상품 개수를 반환하는 메소드입니다.
	            
	            totalPrice += cart.getProductPrice();
	            
	            purchasePrice = (totalPrice + 2500);
	        }
	        
	        // 뷰에 데이터 전달
	        model.addAttribute("totalQuantity", totalQuantity); // 총 개수를 모델에 추가     
	        
	        model.addAttribute("totalPrice", totalPrice);
	        
	        model.addAttribute("purchasePrice", purchasePrice);

	        // 뷰에 데이터 전달
	        model.addAttribute("checkout", checkout);
	        

	        return "cart/checkout"; // 결제 화면 페이지 뷰 이름
	    }

}
