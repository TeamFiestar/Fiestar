//package com.TeamFiestar.Fiestar.cart.controller;
//
//import java.util.LinkedHashSet;  
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
//import com.TeamFiestar.Fiestar.cart.model.dto.Orderer;
//import com.TeamFiestar.Fiestar.cart.model.service.CartService;
//import com.TeamFiestar.Fiestar.member.model.dto.Member;
//
//
//@Controller
//public class CheckOut {
//	
//	    @Autowired
//	    private CartService cartService;
//	    
//	    @PostMapping("checkout")
//	    
//	    public String checkout(Model model, 
//	                           @SessionAttribute(value = "loginMember") Member loginMember, 
//	                           @RequestParam("selectEach") List<String> cartNoList) {
//	    	
//	    	
//	    	// List를   ","  ex) ( [2, 3, 5] -> 2, 3, 5 ) 로 구분되는 String으로 변환하는 방법
//	    	// cartNoList -> "9,13,15"
//	    	//https://yjh5369.tistory.com/entry/java-List%EB%A5%BC-%EB%AC%B8%EC%9E%90%EC%97%B4%EB%A1%9C-Join%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95
//	    		
//	    	Set<String> set = new LinkedHashSet<>(cartNoList);
//	    	String selectNo = String.join(",", set);
//	    	
//	        // 선택된 장바구니 항목 조회
//	    	
//	        List<Cart> checkout = cartService.checkout(selectNo);
//	        	        
//	        
//	        int totalQuantity = 0; 
//	        
//	        int totalPrice = 0;
//	        
//	        int purchasePrice = 0;
//	        
//	        
//	        
//	        for (Cart cart : checkout) {
//	        	
//	        	totalQuantity += cart.getProductCount(); // 총 상품 수량
//	            
//	        	totalPrice += cart.getProductCount() * cart.getProductPrice(); // 총 상품 가격
//	        	
//	        	purchasePrice = (totalPrice + 2500); // 총 결제 가격
//	       
//	        	
////	        	cart.setMemberAddress(loginMember.getMemberAddress());
//	        	
////	        	session으로 얻어오면 된다. 기초 공부 
//	        	
//	        	
//	        }
//
//	        
//	        // 뷰에 데이터 전달
//	        
//	        	      
//	        model.addAttribute("totalQuantity", totalQuantity); // 총 개수를 모델에 추가     
//	        
//	        model.addAttribute("totalPrice", totalPrice); // 총 가격을 모델에 추가
//	        
//	        model.addAttribute("purchasePrice", purchasePrice); // 결제 금액 총 상품 가격 + 배송비
//
//	        model.addAttribute("checkout", checkout); // 뷰에 데이터 전달
//
//	        return "cart/checkout"; // 결제 화면 페이지 뷰 이름
//	    }
//	    
//	    
//	    
//	    
//	    @PostMapping("checkoutResult")
//	    
//	    public String order(@SessionAttribute(value = "loginMember") Member loginMember,
//	    						Orderer inputOrderer, @RequestParam("address") String[] address,
//	    						@RequestParam("purchasePrice") String purchasePrice,
//	    						RedirectAttributes ra,
//	                                  
//	                                  Model model) {
//	    		    
//	    	    
//	    	// 리스트에서 	   
//			
//	    	int result = cartService.order(inputOrderer, address);
//	    	
//	    	if(result > 0) {
//	    		ra.addFlashAttribute("message", "주문이 완료 되었습니다");
//	    		return "cart-checkoutResult";
//	    		
//	    	} else {
//	    		
//	    		return "/";
//	    	}
//	    		
//	    	
//	    	}	
//	    
//
//}


//
//   
//	    @PostMapping("checkoutResult")
//	    
//	    public String order(@SessionAttribute(value = "loginMember") Member loginMember,
//	    						Orderer inputOrderer, @RequestParam("address") String[] address,
//	    						@RequestParam("purchasePrice") String purchasePrice, 
//	    						RedirectAttributes ra,  @RequestParam("selectEach") List<String> cartNoList,
//	                                  Model model) {
//	    		    
//	    	    
//	    	// 리스트에서 	   
////			
////	    	Set<String> set = new LinkedHashSet<>(cartNoList);
////	    	String selectNo = String.join(",", set);
////	    	
////	        // 선택된 장바구니 항목 조회
////	    	
////	        List<Cart> purchase = cartService.purchase(selectNo);
//	    	
////	        model.addAttribute("purchase", purchase);
//
//
//int result = cartService.order(inputOrderer, address);
//	    	
//	    	if(result > 0) {
//	    		ra.addFlashAttribute("message", "주문이 완료 되었습니다");
//	    		return "cart/checkout-Result";
//	    		
//	    	} else {
//	    		
//	    		return "/";
//	    	}
//	    		
//	    	
//	    	}	
//	    
//	    
//	    @GetMapping("checkoutResult")
//	    public String checkoutResult() {
//	    	
//	    	
//	    	
//	    	return "cart/checkout-Result";
//	    }
//	    
//	    
//	    
//	    
//	    
//	    
//	    
//	    
//	 
//	    
//
//}
