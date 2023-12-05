package com.TeamFiestar.Fiestar.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.TeamFiestar.Fiestar.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes({"loginMember"})
@RequiredArgsConstructor
public class BoardController {
	
	
	private final BoardService service;
	
	@GetMapping("{artistGroupTitle}/feed")
	public String selectBoard(Model model, @RequestParam Map<String, Object> paramMap, 
			@PathVariable("artistGroupTitle" ) String artistGroupTitle) {
		{
			
			Map<String, Object> map = service.selectBoard(paramMap);
			
			model.addAttribute("map", map);
			model.addAttribute("artistGroupTitle", artistGroupTitle);
			
		}
		
		return "artistHomepage/feed";
	}

	
	@GetMapping("{artistGroupTitle}/artist")
	public String artist() {
		return "artistHomepage/artist";
	}
}
