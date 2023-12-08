package com.TeamFiestar.Fiestar.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckOut {
	
	@PostMapping("checkout")
	public String checkout() {
		return "cart/checkout";
	}
	
	
}
