
//package com.TeamFiestar.Fiestar.shop.controller;
//
//
//import org.springframework.stereotype.Controller;  
//
//import java.util.Map;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.TeamFiestar.Fiestar.shop.model.service.ShopService;
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequestMapping("shop")
//
//public class ShopController {
//	
////	private final ShopService service;
//
//	
//	@GetMapping("home")
//	public String shopMain(Model model) {
//		
//		Map<String, Object> map = service.shopMain();
//		model.addAttribute("map",map);
//	
//		int shopCount = service.shopMainCount();
//		model.addAttribute("shopCount", shopCount);
//		
//		
//		return "shop/home";
//	}	
//	
//	
//
//	
//	
//
//	
//	
//	
//	@GetMapping("noticeDetail")
//	public String noticeDetail() {
//		return "shop/noticeDetail";
//	}
//	
//	
//	
//	
//	
//	
//	@GetMapping("shopNotice")
//	public String shopNotice() {
//		return "shop/shopNotice";
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	@GetMapping("noticeList")
//	public String noticeList() {
//		return "shop/noticeList";
//	}
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
//	
//	
//	@GetMapping("shopDetail")
//	public String shopDetail() {
//		return "shop/shopDetail";
//			
//	}
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

package com.TeamFiestar.Fiestar.shop.controller;


import org.springframework.stereotype.Controller;   


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.TeamFiestar.Fiestar.shop.model.service.ShopService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("shop")
@RequiredArgsConstructor
public class ShopController {
	
	private final ShopService service;

	
	@GetMapping("home")
	public String shopMain(Model model) {
		
		Map<String, Object> map = service.shopMain();
		model.addAttribute("map",map);
	
		int shopCount = service.shopMainCount();
		model.addAttribute("shopCount", shopCount);
		
		
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

