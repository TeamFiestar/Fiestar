package com.TeamFiestar.Fiestar.shop.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
						@RequestParam("shopImg") List<MultipartFile> images) throws IllegalStateException, IOException{
		
		int productNo = service.insertGoods(product,images);
		
		if(productNo > 0) {
			ra.addFlashAttribute("message","상품 등록 성공");
			return "redirect:shop/shopDetail";
		}
		
		ra.addFlashAttribute("message","상품 등록 실패");
		return "redirect:admin/goods";
		
	}

}
