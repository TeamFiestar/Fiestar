package com.TeamFiestar.Fiestar.admin.controller;

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

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.admin.model.service.ArtistAdminAJAXService;
import com.TeamFiestar.Fiestar.admin.model.service.ArtistAdminService;

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
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("notice", notice);
		map.put("artistGroupTitle", artistGroupTitle);
		
		List<ArtistNotice> noticeList = service.ArtistNoticeList(map, cp);
		
		model.addAttribute("noticeList", noticeList);
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
		
		List<Report> reportList = service.selectReportList(artistGroupTitle, report, cp);
		model.addAttribute("reportList",reportList);
		model.addAttribute("key",report.getKey());
		model.addAttribute("reportSearch",report.getReportSearch());
		
		return "admin/artistReport";
		
	}
	
	
	@GetMapping("{artistGroupTitle}/order")
	public String artistOrder(
			@RequestParam(value="cp", required=false , defaultValue="1" ) int cp,
			@PathVariable("artistGroupTitle") String artistGroupTitle) {
		
		return "admin/artistOrder";
		
	}

}
