package com.TeamFiestar.Fiestar.shop.controller;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.service.ShopService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("shop")
@RequiredArgsConstructor
public class ShopController {
	
	private final ShopService service;

	
	/**쇼핑몰 메인페이지 전체 조회
	 * @param model
	 * @param paramMap
	 * @param shopSearch
	 * @return
	 */
	@GetMapping("home")
	public String shopMain(Model model,
							@RequestParam Map<String, Object> paramMap,
							@RequestParam(name="shopSearch", required = false, defaultValue = "") String shopSearch) {
		
		if(paramMap.get("shopSearch") == null) {
			Map<String, Object> map = service.shopMain();
			model.addAttribute("map",map);
			int shopCount = service.shopCount();
			model.addAttribute("shopCount", shopCount);
		
			
		}else {
			paramMap.put("shopSearch", shopSearch);
			Map<String, Object> map =  service.searchList(paramMap);
			model.addAttribute("map",map);
			int shopCount = service.shopSearchCount(paramMap);
			model.addAttribute("shopCount", shopCount);
			
		}
		return "shop/home";
	}	
	
	/**아티스트 그룹별로 상품 조회
	 * @param model
	 * @param paramMap
	 * @param artistGroupNo
	 * @return
	 */
	@GetMapping("home/{artistGroupTitle}")
	public String artistGroupShop(Model model,
									Map<String, Object> paramMap,
									@RequestParam("artistGroupNo") int artistGroupNo) {
		
		paramMap.put("artistGroupNo", artistGroupNo);
		int shopCount = service.shopGroupCount(paramMap);
		model.addAttribute("shopCount", shopCount);
		Map<String, Object> map = service.artistGroupShop(paramMap);
		model.addAttribute("map", map);
		return "shop/home";
	}
	
	/** 그룹별 상품 조회 후 정렬
	 * @return
	 */
	@GetMapping(value = "home/sortList" , produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Product> AllsortList(@RequestParam Map<String, Object> paramMap){
		
		return service.selectAllSort(paramMap);
	}
	
	/** 그룹별 상품 조회 후 정렬
	 * @return
	 */
	@GetMapping(value = "home/groupSortList" , produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Product> sortList(@RequestParam Map<String, Object> paramMap){
		
		return service.selectGroupSort(paramMap);
	}
	
	/** 상품 검색 조회 후 정렬
	 * @return
	 */
	@GetMapping(value = "home/searchSortList" , produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Product> searchSortList(@RequestParam Map<String, Object> paramMap){
		
		return service.selectSearchSort(paramMap);
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
