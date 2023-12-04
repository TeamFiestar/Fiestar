package com.TeamFiestar.Fiestar.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TeamFiestar.Fiestar.cart.model.dto.Cart;



@Controller

public class CartController {
	
	@GetMapping("cart")
	public String cart(Model model, Cart cart) {
		
		model.addAttribute("cart", cart);
		
		return "cart/cart";
	}
	
	
	@GetMapping("checkoutResult")
	public String checkoutResult() {
		
		return "cart/checkout-result";
	}
	
	@PostMapping("checkout")
	public String checkout() {
		return "cart/checkout";
	}
	
}



