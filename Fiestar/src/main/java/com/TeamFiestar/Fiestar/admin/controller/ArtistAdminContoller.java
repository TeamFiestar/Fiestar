package com.TeamFiestar.Fiestar.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.service.ArtistAdminAJAXService;
import com.TeamFiestar.Fiestar.admin.model.service.ArtistAdminService;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("artistAdmin")
@SessionAttributes("{artistGroupTitle}")
@RequiredArgsConstructor
public class ArtistAdminContoller {
	
	private final ArtistAdminService service;
	
	// 아티스트 공지사항 목록 조회
	@GetMapping("{artistGroupTitle}/notice")
	public String artistNotice(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp,
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model,
			ArtistNotice notice) {
		
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("notice", notice);
		paramMap.put("artistGroupTitle", artistGroupTitle);
		
		Map<String, Object> map = service.ArtistNoticeList(paramMap, cp);
		
		model.addAttribute("map", map);
		model.addAttribute("key", notice.getKey());
		model.addAttribute("noticeSearch", notice.getNoticeSearch());
		
		return "admin/artistNotice";
	}
	
	// 아티스트 공지사항 등록 조회
	@GetMapping("{artistGroupTitle}/noticeAdd")
	public String artistNoticeAdd(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model){
		
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		return "admin/artistNoticeAdd";
	}
	
	
	@GetMapping("{artistGroupTitle}/noticeUpdate")
	public String artistNoticeUpdate(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model){
		
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		return "admin/artistNoticeUpdate";
	}
	
	// 아티스트 공지사항 등록
	@PostMapping("{artistGroupTitle}/noticeAdd")	
	public String artistNoticeAdd(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model, ArtistNotice notice){
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		int result = service.artistNoticeAdd(artistGroupTitle, notice);
		
		return "redirect:/artistAdmin/{artistGroupTitle}/notice";
	}
	
	// 신고 조회
	@GetMapping("{artistGroupTitle}/report")
	public String artistReport(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp,
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model, Report report) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		Map<String, Object> map = service.selectReportList(artistGroupTitle, report, cp);
		model.addAttribute("map",map);
		model.addAttribute("key",report.getKey());
		model.addAttribute("reportSearch",report.getReportSearch());
		
		return "admin/artistReport";
		
	}
	
	// 아티스트 주문 조회
	@GetMapping("{artistGroupTitle}/order")
	public String artistOrder(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp,
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model,
			Purchase searchPurchase) {
		Map<String, Object> map = service.selectPurchaseList(artistGroupTitle, cp);
		
		model.addAttribute("map",map);
		
		return "admin/artistOrder";
	}
	
	
	@GetMapping("{artistGroupTitle}/goods")
	public String register(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		return "admin/goods";
	}
	
	/*상품 등록
	 *  */
	@PostMapping("{artistGroupTitle}/goods")
	public String insertGoods(RedirectAttributes ra,
								Product product,
								@PathVariable("artistGroupTitle") String artistGroupTitle,
								@RequestParam("contentImg") MultipartFile contentImg,
								@RequestParam ("thumbnailImg") MultipartFile thumbnailImg) throws IllegalStateException, IOException{
		
		int productNo = service.insertGoods(product,artistGroupTitle, contentImg, thumbnailImg);
		
		if(productNo > 0) {
			ra.addFlashAttribute("message","상품 등록 성공");
			return "redirect:/shop/shopDetail/" + productNo;
		}
		
		ra.addFlashAttribute("message","상품 등록 실패");
		return "redirect:goods";
		
	}

}
