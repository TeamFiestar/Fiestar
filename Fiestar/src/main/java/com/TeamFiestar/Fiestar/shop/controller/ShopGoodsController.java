package com.TeamFiestar.Fiestar.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.service.ShopService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class ShopGoodsController {
	
	private final ShopService service;
	
	
	@GetMapping("goods")
	public String register() {
		return "admin/goods";
	}
	
	/*상품 등록
	 *  */
	@PostMapping("goods")
	public String insertGoods(RedirectAttributes ra,
						Product product,
						@RequestParam("name") String name,
						@RequestParam("shopContent") String shopContent,
						@RequestParam("shopImg") List<MultipartFile> images) {
		
		int productNo = service.insertGoods(product,images);
		
		
		return "redirect:shop/shopDetail";
	}

}
