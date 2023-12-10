package com.TeamFiestar.Fiestar.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("{artistGroupTitle}/notice")
	public String artistNotice(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		List<ArtistNotice> noticeList = service.ArtistNoticeList(artistGroupTitle);
		
		model.addAttribute("noticeList", noticeList);
		
		return "admin/artistNotice";
	}
	
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
	
	@PostMapping("{artistGroupTitle}/noticeAdd")	
	public String artistNoticeAdd(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model, ArtistNotice notice){
		
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		int result = service.artistNoticeAdd(artistGroupTitle, notice);
		
		return "redirect:/artistAdmin/{artistGroupTitle}/notice";
	}
	
	
	@GetMapping("{artistGroupTitle}/report")
	public String artistReport(
			@PathVariable("artistGroupTitle") String artistGroupTitle, Model model) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		List<Report> reportList = service.selectReportList(artistGroupTitle);
		
		return "admin/artistReport";
		
	}

}
