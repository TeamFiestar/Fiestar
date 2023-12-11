package com.TeamFiestar.Fiestar.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.TeamFiestar.Fiestar.shop.model.service.ShopService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class ShopGoodsController {
	
	private final ShopService service;
	
	@GetMapping("goods")
	public String goods() {
		
		
		
		return "admin/goods";
	}

}
