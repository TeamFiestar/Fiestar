package com.TeamFiestar.Fiestar.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.service.AdminService;
import com.TeamFiestar.Fiestar.admin.model.service.ArtistAdminService;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.shop.model.dto.Product;
import com.TeamFiestar.Fiestar.shop.model.service.ShopService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("artistAdmin")
@SessionAttributes("{artistGroupTitle}")
@RequiredArgsConstructor
public class ArtistAdminContoller {
	
	private final ArtistAdminService service;
	
	private final ShopService shopService;
	
	private final AdminService adminService;
	
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
		Map<String, Object> map = service.selectPurchaseList(artistGroupTitle, searchPurchase, cp);
		
		model.addAttribute("map",map);
		
		return "admin/artistOrder";
	}
	
	
	/**상품 등록 화면
	 * @param artistGroupTitle
	 * @param model
	 * @return
	 */
	@GetMapping("{artistGroupTitle}/goods")
	public String register(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model,
			@SessionAttribute(value="loginMember", required = false) Member loginMember) {
		
		int memberNo = loginMember.getMemberNo();
		int adminNo = service.selectAdminNo(artistGroupTitle);
		
		if(memberNo == adminNo) {
			model.addAttribute("artistGroupTitle",artistGroupTitle);
			return "admin/goods";
		}else {
			
			return "/";
		}
		
	}
	
	/*상품 등록
	 *  */
	@PostMapping("{artistGroupTitle}/goods")
	public String insertGoods(RedirectAttributes ra,
								@ModelAttribute Product product,
								@RequestParam("contentImg") MultipartFile contentImg,
								@RequestParam ("thumbnailImg") MultipartFile thumbnailImg) throws IllegalStateException, IOException{
		
		int productNo = service.insertGoods(product, contentImg, thumbnailImg);
		
		if(productNo > 1) {
			ra.addFlashAttribute("message","상품 등록 성공");
			return "redirect:/shop/shopDetail/" + productNo;
		}else if(productNo == 1) {
			ra.addFlashAttribute("message","옵션을 선택해주세요");
			return "redirect:goods";
		}else{
			ra.addFlashAttribute("message","상품 등록 실패");
			return "redirect:goods";
		}
		
			
		
	}
	
	/**상품 수정화면 이동
	 * @param artistGroupTitle
	 * @param model
	 * @return
	 */
	@GetMapping("{artistGroupTitle}/{productNo}/goodsModify")
	public String registe(@PathVariable("artistGroupTitle") String artistGroupTitle,
							@PathVariable("productNo") int productNo,
							Model model) {
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("artistGroupTitle", artistGroupTitle);
		map.put("productNo", productNo);
		
		Map<String, Object> prod = shopService.shopDetail(productNo);
		
		model.addAttribute("map", map);
		model.addAttribute("prod", prod);
		
		return "admin/goodsModify";
	}
	
	
	
	/**상품 수정
	 * @param ra
	 * @param artistGroupTitle
	 * @param productNo
	 * @param product
	 * @param contentImg
	 * @param thumbnailImg
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("{artistGroupTitle}/{productNo}/goodsModify")
	public String updateGoods(RedirectAttributes ra,
								@PathVariable("artistGroupTitle") String artistGroupTitle,
								@PathVariable("productNo") int productNo,
								@ModelAttribute Product product,
								@RequestParam("contentImg") MultipartFile contentImg,
								@RequestParam ("thumbnailImg") MultipartFile thumbnailImg) throws IllegalStateException, IOException{
		
		product.setArtistGroupTitle(artistGroupTitle);
		product.setProductNo(productNo);
		
		int result = service.updateGoods(product, contentImg, thumbnailImg);
		
		if(result > 0) {
			ra.addFlashAttribute("message","상품 수정 성공");
			return "redirect:/shop/shopDetail/" + productNo;
		}
		ra.addFlashAttribute("message","상품 등록 실패");
		return "redirect:goods";	
	}
	
	
	
	/**상품 삭제
	 * @param productNo
	 * @param loginMember
	 * @param ra
	 * @return
	 */
	@GetMapping("{productNo}/goodsDelete")
	public String deleteGoods(@PathVariable("productNo") int productNo,
								@SessionAttribute(value="loginMember", required = false) Member loginMember,
								RedirectAttributes ra) {
		
		if(loginMember == null) {
			ra.addFlashAttribute("message" , "로그인 후 이용해주세요");
			return "redirect:/shop/home";
		}
		
		int result = service.deleteGoods(productNo);
		
		if(result >0) {
			ra.addFlashAttribute("message","상품이 삭제되었습니다.");
			return "redirect:/shop/home";	
		}
		ra.addFlashAttribute("message","상품 삭제 실패하였습니다.");
		return "redirect:/shop/shopDetail/" + productNo;		
	}
	
	
	
	
	
	@GetMapping("regi")
	public String regi(@SessionAttribute("loginMember") Member loginMember, Model model) {
		String artistGroupTitle = adminService.regi(loginMember.getMemberNo());
		model.addAttribute("artistGroupTitle", artistGroupTitle);
		return "admin/artistRegi";
	}
	
	@PostMapping("artistGroupRegi")
	public String artistGroupRegi(@RequestParam("backImg") MultipartFile backImg,
			@RequestParam("profile") MultipartFile profile,
			@RequestParam("image") MultipartFile image,
			@RequestParam("artistGroupTitle") String artistGroupTitle,
			@SessionAttribute("loginMember") Member loginMember,
			ArtistGroup1 artistGroup,
			RedirectAttributes ra
			
			) throws IllegalStateException, IOException {
		int adminNo = loginMember.getMemberNo();
		int result = adminService.artistGroupRegi(backImg, profile, image, artistGroupTitle, 
				adminNo, artistGroup);
		
		String message = null;
		
		if(result>0) {
			ra.addFlashAttribute("message", "등록 성공");
			return "redirect:/";
		}else { 
			ra.addFlashAttribute("message", "등록 실패");
			return "redirect:regi";
		}
	}
	
	@GetMapping("artistGroupUpdate")
	public String artistGroupUpdate(Model model, @SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra) {
		ArtistGroup1 artistGroup = adminService.artistGroupUpdate(loginMember.getMemberNo());
		String artistGroupTitle = adminService.regi(loginMember.getMemberNo());
		model.addAttribute("artistGroup", artistGroup);
		model.addAttribute("artistGroupTitle", artistGroupTitle);
		
		if(artistGroup == null) {
			ra.addFlashAttribute("message", "등록된 아티스트 그룹이 없습니다.");
			return "redirect:regi";
		}else
		return "admin/artistGroupUpdate";
	}
	
	
	@PostMapping("artistGroupUpdate")
	public String artistGroupUpdate(@RequestParam("backImg") MultipartFile backImg,
			@RequestParam("profile") MultipartFile profile,
			@RequestParam("image") MultipartFile image,
			@RequestParam("artistGroupTitle") String artistGroupTitle,
			@SessionAttribute("loginMember") Member loginMember,
			ArtistGroup1 artistGroup,
			RedirectAttributes ra) throws IllegalStateException, IOException {
		int adminNo = loginMember.getMemberNo();
		int result = adminService.GroupUpdate(backImg, profile, image, artistGroupTitle, adminNo, artistGroup);
		
		if(result > 0) {
			ra.addFlashAttribute("message", "그룹 변경 성공");
			return "redirect:/";
		}else {
			ra.addFlashAttribute("message", "변경 실패");
			return "redirect:artistGroupUpdate";
		}
		
	}
	
	
	

}
