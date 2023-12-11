package com.TeamFiestar.Fiestar.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckOut {
	
	
	@PostMapping("checkout")
	values checked
	public String checkout() {
		return "cart/checkout";
	}
	
	
}
