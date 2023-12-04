package com.TeamFiestar.Fiestar.shop.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shop")
public class ShopController {
	
	@GetMapping("noticeDetail")
	public String noticeDetail() {
		return "shop/noticeDetail";
	}
	
	@GetMapping("shopNotice")
	public String shopNotice() {
		return "shop/shopNotice";
	}
	
	@GetMapping("noticeList")
	public String noticeList() {
		return "shop/noticeList";
	}

	@GetMapping("home")
	public String shopMain( ) {
		return "shop/home";
	}
	
	@GetMapping("shopDetail")
	public String shopDetail() {
		return "shop/shopDetail";
			
	}
	
	
	

}
