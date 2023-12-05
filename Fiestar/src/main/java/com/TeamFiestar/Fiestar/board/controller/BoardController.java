package com.TeamFiestar.Fiestar.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.TeamFiestar.Fiestar.board.model.service.BoardService;

@Controller
@RequestMapping("{artistGroupTitle}")
@SessionAttributes({"loginMember"})
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("feed")
	public String selectBoard(Model model, @RequestParam Map<String, Object> paramMap) {
		{
			
			Map<String, Object> map = service.selectBoard(paramMap);
			
			model.addAttribute("map", map);
			
		}
		
		return "{artistGroupTitle}/feed";
	}

}
