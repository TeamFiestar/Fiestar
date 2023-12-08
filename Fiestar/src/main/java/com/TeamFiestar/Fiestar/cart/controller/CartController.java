package com.TeamFiestar.Fiestar.cart.controller;
import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.service.CartService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;


@Controller
@SessionAttributes({"loginMember"})

public class CartController {
	
	@Autowired
	private CartService service;
	
/*	제품 페이지에서 받아오는 정보
	상품 번호, 해당 상품 이미지,수량 
*/
	
//	제품 상세 페이지에서 멤버 번호, 상품 명, 옵션, 수량,  담아서 장바구니로 전달

//  전달된 데이터 받아와서 화면에 출력	

	
	@GetMapping("cartPage")
	public String cartPage(Model model, 
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra) {
		
		if(loginMember == null) {
			ra.addFlashAttribute("message", "로그인 후 이용 가능합니다");
			return "redirect:/member/login";
		}
		
		
		List<Cart> cartList = service.cartPage(loginMember.getMemberNo());
		
		model.addAttribute("cartList", cartList);
		
		return "cart/cart";
	}
	
	
//	상품 수량 변경
	
//	@GetMapping(value="/cartPage/quantity", produces="application/json; charset=UTF-8") ?? 
	

//	받아 와야 하는 값 회원 번호 -> 장바구니 번호로 (상품 수량 )
	

	
	
	
	
	@GetMapping("checkoutResult")
	public String checkoutResult() {
		
		return "cart/checkout-result";
	}
	
//	@PostMapping("checkout")
//	public String checkout() {
//		return "cart/checkout";
//	}
//	
	
	
//	-----------------------------------------
	
	
	
	
	
	
	
	
}



