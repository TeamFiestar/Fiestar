package com.TeamFiestar.Fiestar.cart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;   
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.dto.Orderer;
import com.TeamFiestar.Fiestar.cart.model.service.CartService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;


@SessionAttributes({"purchaseNo"})
@Controller
public class CheckOutController {
	
	    @Autowired
	    private CartService cartService;
	    
	    @PostMapping("checkout")
	    
	    public String checkout(Model model, 
	                           @SessionAttribute(value = "loginMember") Member loginMember, 
	                           @RequestParam("selectEach") List<String> cartNoList) {
	    	
	    	
	    	// List를   ","  ex) ( [2, 3, 5] -> 2, 3, 5 ) 로 구분되는 String으로 변환하는 방법
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
	        	
	        	totalQuantity += cart.getProductCount(); // 총 상품 수량
	            
	        	totalPrice += cart.getProductCount() * cart.getProductPrice(); // 총 상품 가격
	        	
	        	purchasePrice = (totalPrice + 2500); // 총 결제 가격
	       
	        	
//	        	cart.setMemberAddress(loginMember.getMemberAddress());
	        	
//	        	session으로 얻어오면 된다. 기초 공부 
	        	
	        	
	        }

	        
	        // 뷰에 데이터 전달
	        
	        	      
	        model.addAttribute("totalQuantity", totalQuantity); // 총 개수를 모델에 추가     
	        
	        model.addAttribute("totalPrice", totalPrice); // 총 가격을 모델에 추가
	        
	        model.addAttribute("purchasePrice", purchasePrice); // 결제 금액 총 상품 가격 + 배송비

	        model.addAttribute("checkout", checkout); // 뷰에 데이터 전달

	        return "cart/checkout"; // 결제 화면 페이지 뷰 이름
	    }
	    
	    
	    
	    
	    
	    @PostMapping("checkoutResult")
	    
	    public String order(@SessionAttribute(value = "loginMember") Member loginMember,
	    						Orderer inputOrderer, @RequestParam("address") String[] address,
	    						@RequestParam("purchasePrice") String purchasePrice, 
	    						RedirectAttributes ra,  @RequestParam("selectEach") List<String> cartNoList,  Model model ) {
	    	
	        
	    	int memberNo = loginMember.getMemberNo();
	    	
	    	inputOrderer.setMemberNo(memberNo);
	    	
	    	Set<String> set = new LinkedHashSet<>(cartNoList);
	    	String selectNo = String.join(",", set);
	    	
	        	        
	    	int purchaseNo = cartService.order(inputOrderer, address, selectNo);
	    	
//	    	맵에 회원 번호, 주문 번호 담아서 서비스로 넘긴다 -> 서비스에서 PURCHASE select 해주는 서비스, Purchase_List SELECT 하는 서비스 하나 
    	
    	  if (purchaseNo > 0) {
    		  model.addAttribute("purchaseNo", purchaseNo);
              return "redirect:/checkoutResult"; // 주문 결과 페이지로 리다이렉트
          } else {
              ra.addFlashAttribute("message", "주문 처리 중 오류가 발생했습니다.");
              return "redirect:/checkout"; // 주문 페이지로 리다이렉트
          }
	    }
	    	
	    
	    
	    @GetMapping("checkoutResult")
	    public String checkoutResult(Model model, Map<String, Object> HashMap, @SessionAttribute("purchaseNo") int purchaseNo) {
	    	
	    	Map<String, Object> map = cartService.checkoutResult(purchaseNo);
	    	
	    	model.addAttribute("map",map);

	    	return "cart/checkout-Result";
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	 
	    

}
