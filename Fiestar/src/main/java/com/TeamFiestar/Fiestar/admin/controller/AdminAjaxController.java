package com.TeamFiestar.Fiestar.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.admin.model.service.AdminAjaxService;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminAjaxController {

	private final AdminAjaxService service;
	
	
	@GetMapping("selectBoard")
	@ResponseBody
	public List<Board> selectBoard(@RequestParam(value="memberNo", required = false) int memberNo) {
		return service.selectBoard(memberNo);
	}
	

	@GetMapping("selectDelMember")
	@ResponseBody
	public Map<String, Object> selectDelMember(Model model, Member member,
			@RequestParam(value="cp", required = false, defaultValue = "1")int cp,
			@RequestParam Map<String, Object> paramMap) {
		
		if(paramMap.get("key") == null && paramMap.get("query") == null) {
			Map<String, Object> map = service.selectDelMember(member,cp);		
			return map;
		}else {
			Map<String, Object> map = service.searchDelMember(paramMap, cp);	
			return map;
		}
	}
	
	
	@PutMapping("deleteMember")
	@ResponseBody
	public int update(@RequestBody Map<String, Object> paramMap, RedirectAttributes ra) {
		int result = service.update(paramMap);
		return result;
	}
	
	
	@PutMapping("withDraw")
	@ResponseBody
	public int withDraw(@RequestBody Map<String, Object> paramMap) {
		int result = service.withDraw(paramMap);
		return result;
	}
	
	@GetMapping("selectMemberAjax")
	@ResponseBody
	public Map<String, Object> selectMemberAjax(Model model,Member member,
			@RequestParam(value="cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null && paramMap.get("query") == null) {
			Map<String, Object> map = service.selectMember(member,cp);		
			return map;
		}else {
			Map<String, Object> map = service.searchMember(paramMap, cp);	
			return map;
		}
	}
	
	
	@PutMapping("changeAuthority")
	@ResponseBody
	public int changeAuthority(@RequestBody Map<String, Object> paramMap) {
		int result = service.changeAuthority(paramMap);
		return result;
	}
	
	
}
