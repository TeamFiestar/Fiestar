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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.admin.model.dto.SiteNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Purchase;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.service.AdminService;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
//import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping ("admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService service;
	
	@GetMapping("selectMember")
	public String member(Model model,Member member,
					@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
					@RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null && paramMap.get("query") == null) {
			Map<String, Object> map = service.selectMember(member,cp);
			model.addAttribute("map", map);
		}else {
			Map<String, Object> map = service.searchMember(paramMap, cp);
			model.addAttribute("map", map);
		}
		
		return "admin/selectMember";
	}
	
	@GetMapping("deleteMember")
	public String deleteMember(Model model, Member member,
				@RequestParam(value="cp", required = false, defaultValue = "1")int cp,
				@RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null && paramMap.get("query") == null) {
			Map<String, Object> map = service.deleteMember(member,cp);
			model.addAttribute("map", map);
		}else {
			Map<String, Object> map = service.searchDeleteMember(paramMap, cp);
			model.addAttribute("map", map);
		}
		return "admin/deleteMember";
	}
	

	
	
	@GetMapping("subscribeMember")
	public String subscribe(Model model, Member member,
				@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
				@RequestParam Map<String, Object> paramMap,
				@SessionAttribute("loginMember") Member loginMember) {
		int memberNo = loginMember.getMemberNo();
		if(paramMap.get("key") == null && paramMap.get("query") == null) {
			Map<String, Object> map = service.subscribeMember(member,cp, memberNo);
			model.addAttribute("map", map);
//			model.addAttribute("artistGroupNo");
		}else {
			Map<String, Object> map = service.searchSubscribe(paramMap,cp,memberNo);
			model.addAttribute("map", map);
//			model.addAttribute("artistGroupNo", artistGroupNo);
		}
		return "admin/subscribeMember";
	}
	
//	@GetMapping("selectSubscribeBoard")
//	@ResponseBody
//	public List<Board> selectSubscribeBoard(@RequestParam(value="memberNo", required = false) int memberNo,
//										@PathVariable("artistGroupNo") int artistGroupNo,
//										@SessionAttribute("loginMember") Member loginMember) {
//		int loginMemberNo = loginMember.getMemberNo();
//		List<Board> boardList = service.selectSubscribeBoard(loginMemberNo); 
//		return boardList;
//	}
	
	@GetMapping("register")
	public String register() {
		return "admin/goods";
	}
	
	
	@GetMapping("regi")
	public String regi(@SessionAttribute("loginMember") Member loginMember, Model model) {
		String artistGroupTitle = service.regi(loginMember.getMemberNo());
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
		int result = service.artistGroupRegi(backImg, profile, image, artistGroupTitle, 
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
		ArtistGroup1 artistGroup = service.artistGroupUpdate(loginMember.getMemberNo());
		String artistGroupTitle = service.regi(loginMember.getMemberNo());
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
		int result = service.GroupUpdate(backImg, profile, image, artistGroupTitle, adminNo, artistGroup);
		
		if(result > 0) {
			ra.addFlashAttribute("message", "그룹 변경 성공");
			return "redirect:/";
		}else {
			ra.addFlashAttribute("message", "변경 실패");
			return "redirect:artistGroupUpdate";
		}
		
	}
	
	// 공지사항 조회
	@GetMapping("notice")
	public String SiteNotice(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp, Model model,
			SiteNotice notice) {
		
		Map<String, Object> map = service.siteNoticeList(notice, cp);
		
		model.addAttribute("map", map);
		model.addAttribute("key", notice.getKey()); 
		
		return "admin/notice";
	}
	
	// 공지사항 등록
	@GetMapping("noticeAdd")
	public String SiteNoticeAdd( Model model){
		
		return "admin/noticeAdd";
	}
	
	// 공지사항 등록
	@PostMapping("noticeAdd")	
	public String SiteNoticeAdd( Model model, SiteNotice notice){
		
		int result = service.siteNoticeAdd(notice);
		
		return "redirect:/admin/notice";
	}
	
	// 신고 조회
	@GetMapping("report")
	public String artistReport(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp, Model model, Report report) {
		
		Map<String, Object> map = service.selectReportList( report, cp);
		model.addAttribute("map",map);
		model.addAttribute("key",report.getKey());
		model.addAttribute("reportSearch",report.getReportSearch());
		
		
		return "admin/report";
		
	}
	
	// 주문 조회
	@GetMapping("order")
	public String artistOrder(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp, Model model,
			Purchase searchPurchase) {
		Map<String, Object> map = service.selectPurchaseList( cp);
		
		model.addAttribute("map",map);
		
		return "admin/order";
		
	}
	
	
}
