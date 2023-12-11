package com.TeamFiestar.Fiestar.admin.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.admin.model.service.AdminService;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
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
	

	
	
	@GetMapping("subscribeMember/{artistGroupNo:[0-9]+}")
	public String subscribe(Model model, Member member, @PathVariable("artistGroupNo") int artistGroupNo,
				@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
				@RequestParam Map<String, Object> paramMap) {
		
		if(paramMap.get("key") == null && paramMap.get("query") == null) {
			Map<String, Object> map = service.subscribeMember(member,cp, artistGroupNo);
			model.addAttribute("map", map);
			model.addAttribute("artistGroupNo", artistGroupNo);
		}else {
			Map<String, Object> map = service.searchSubscribe(paramMap,cp,artistGroupNo);
			model.addAttribute("map", map);
			model.addAttribute("artistGroupNo", artistGroupNo);
		}
		return "admin/subscribeMember";
	}
	
	@GetMapping("selectSubscribeBoard/{artistGroupNo:[0-9]+}")
	@ResponseBody
	public List<Board> selectSubscribeBoard(@RequestParam(value="memberNo", required = false) int memberNo,
										@PathVariable("artistGroupNo") int artistGroupNo) {
		List<Board> boardList = service.selectSubscribeBoard(memberNo,artistGroupNo); 
		return boardList;
	}
	
	@GetMapping("notice")
	public String admin() {
		return "admin/artistNotice";
	}
	
	@GetMapping("register")
	public String register() {
		return "admin/goods";
	}
	
	
	
	
	
}
