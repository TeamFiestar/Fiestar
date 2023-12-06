package com.TeamFiestar.Fiestar.shop.controller;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TeamFiestar.Fiestar.shop.model.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("shop")
@RequiredArgsConstructor
@Slf4j
public class ShopController {
	
	private final ShopService service;

	
	@GetMapping("home")
	public String shopMain(Model model,
							@RequestParam Map<String, Object> paramMap ) {
		
		if(paramMap.get("query") == null) {
			Map<String, Object> map = service.shopMain(paramMap);
			model.addAttribute("map",map);
			int shopCount = service.shopMainCount();
			model.addAttribute("shopCount", shopCount);
		
			
		}else {
			Map<String, Object> map =  service.searchList(paramMap);
			model.addAttribute("map",map);
			int shopCount = service.shopMainCount();
			model.addAttribute("shopCount", shopCount);
			
		}
		return "shop/home";
	}	
	
	
	
	
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
	
	
	
	@GetMapping("shopDetail")
	public String shopDetail() {
		return "shop/shopDetail";
			
	}
	
	
	
	
	
	
	
	
	

}
