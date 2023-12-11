package com.TeamFiestar.Fiestar.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;
import com.TeamFiestar.Fiestar.cart.model.service.CartService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

@Controller
@SessionAttributes({ "loginMember" })

public class CartController {

	@Autowired
	private CartService service;

	/*
	 * 제품 페이지에서 받아오는 정보 상품 번호, 해당 상품 이미지,수량
	 */

//	제품 상세 페이지에서 멤버 번호, 상품 명, 옵션, 수량,  담아서 장바구니로 전달

//  전달된 데이터 받아와서 화면에 출력	

	@GetMapping("cartPage")
	public String cartPage(Model model, @SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra) {

		if (loginMember == null) {
			ra.addFlashAttribute("message", "로그인 후 이용 가능합니다");
			return "redirect:/member/login";
		}

		List<Cart> cartList = service.cartPage(loginMember.getMemberNo());

		model.addAttribute("cartList", cartList);

		return "cart/cart";
	}

	@PostMapping("cartPage")
	public ResponseEntity<?> updateCart(@RequestBody Cart cart) {
		int updatedRows = service.updateCart(cart);
		if (updatedRows > 0) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
	}

	@PostMapping("updateCartPage")
	public ResponseEntity<?> updateTotalPrice(@RequestBody Cart cart) {
		// requestBody에서 totalPrice 값을 추출하고 처리하는 로직을 작성
		// ...
		return ResponseEntity.ok().build(); // 응답 메시지를 반환하지 않음
	}

	@DeleteMapping("cartPage")
	@ResponseBody
	public /*ResponseEntity<?>*/ int deleteCart(@RequestBody int cartNo) {

		int result = service.deleteCart(cartNo);

		return result;
		
//		if (deleteCartNo > 0) {
//			return ResponseEntity.ok().build();
//
//		} else
//
//			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();

	}

//	 @DeleteMapping("cartPage")
//	 public ResponseEntity<?> deleteCart(@RequestBody Cart cart) {
//	        
//	        try {
//	          
//	        	return ResponseEntity.ok().build();
//	        	
//	        } catch (Exception e) {
//	        	
//	            e.printStackTrace();
//	            
//	            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
//	        }
//	    }

//	
//	@PostMapping("cartPage")
//	public int updateCart(@RequestBody Cart cart) {
//	    int updatedRows = service.updateCart(cart);
//	    return updatedRows; // int 값을 그대로 반환
//	}

	// 예: service.updateCartItem(cartUpdateDto);

	// 성공적으로 처리되었다고 가정하고 응답 반환
//	    return ResponseEntity.ok("장바구니 항목이 업데이트되었습니다.");

	@GetMapping("checkoutResult")
	public String checkoutResult() {

		return "cart/checkout-result";
	}


}
