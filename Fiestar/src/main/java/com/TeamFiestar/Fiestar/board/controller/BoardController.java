package com.TeamFiestar.Fiestar.board.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.BoardImg;
import com.TeamFiestar.Fiestar.board.model.service.BoardService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes({"loginMember", "artistGroupTitle", "artistGroupNo"})
@RequiredArgsConstructor
public class BoardController {
	
	
	private final BoardService service;
	

	
	@GetMapping("{artistGroupTitle}/feed")
	public String selectBoard(Model model, @RequestParam Map<String, Object> paramMap, 
			@PathVariable("artistGroupTitle" ) String artistGroupTitle) {
		
			int artistGroupNo = service.artistGroupNo(artistGroupTitle);
			paramMap.put("artistGroupNo", artistGroupNo);
			Map<String, Object> map = service.selectBoard(paramMap);
			model.addAttribute("artistGroupNo",artistGroupNo);
			model.addAttribute("map", map);
			model.addAttribute("artistGroupTitle", artistGroupTitle);
			
			
		
		
		return "artistHomepage/feed";
	}
	
	@GetMapping("{artistGroupTitle}/feed/{boardNo:[0-9]+}")
	public String detail(Model model, 
			@RequestParam Map<String, Object> paramMap, 
			@PathVariable("artistGroupTitle" ) String artistGroupTitle, 
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			@PathVariable("boardNo" ) int boardNo, 
			Board boardDetail) {
		
			int artistGroupNo = service.artistGroupNo(artistGroupTitle);
//			Map<String, Object> map = service.detail(paramMap);
			Map<String, Object> map = new HashMap<>();
			map.put("boardNo", boardNo);
			
			
			Board board = service.boardDetail(map);
			
			model.addAttribute("map", map);
			model.addAttribute("artistGroupNo", artistGroupNo);
			model.addAttribute("boardDetail",boardDetail);
			model.addAttribute("artistGroupTitle", artistGroupTitle);
			
			String path = null;
			
			if(board != null) {
				model.addAttribute("board", board);
				path = "artistHomepage/feedDetail";
				
			if(loginMember != null) {
					map.put("memberNo", loginMember.getMemberNo());
					int likeCheck = service.likeCheck(map);
					
					if(likeCheck ==1) model.addAttribute("likeCheck", "on");
					
				}
				
					
			} else {
				path = "redirect:/artistHomepage/feed";
			}
		
		return path;
	}

//	@GetMapping("{artistGroupTitle}/feed/{boardNo:[0-9]+}")
//	public String detail(@PathVariable("artistGroupTitle") String artistGrouptTitle,
//			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
//			Model model,
//			@PathVariable("boardNo") int boardNo, Board boardDetail,
//			RedirectAttributes ra, HttpServletRequest req, HttpServletResponse resp) 
//			throws ParseException {
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("boardNo", boardNo);
//		
//		Board board = service.detail(map);
//		
//		model.addAttribute("boardDetail",boardDetail);
//		
//		String path = null;
//		
//		if(board != null) {
//			model.addAttribute("board", board);
//			path = "artistHomepage/feed";
//			
//			if(loginMember != null) {
//				map.put("memberNo", loginMember.getMemberNo());
//				int likeCheck = service.likeCheck(map);
//				
//				if(likeCheck ==1) model.addAttribute("likeCheck", "on");
//				
//			}
//			
//				
//		}
//		return path;
//			
//	}
		
	
	@PostMapping("like")
	@ResponseBody
	public int like(@RequestBody Map<String, Object> paramMap, @SessionAttribute("loginMember") Member loginMember) {
		
		paramMap.put("memberNo", loginMember.getMemberNo());
		
		
		return service.likeCheck(paramMap);
	}
	
	
	
	
	@GetMapping("{artistGroupTitle}/artist")
	public String selectartistBoard(Model model, @RequestParam Map<String, Object> paramMap, 
			@PathVariable("artistGroupTitle" ) String artistGroupTitle) {
		{
			
			int artistGroupNo = service.artistGroupNo(artistGroupTitle);
			paramMap.put("artistGroupNo", artistGroupNo);
			Map<String, Object> map = service.selectArtistBoard(paramMap);
			model.addAttribute("artistGroupNo",artistGroupNo);
			model.addAttribute("map", map);
			model.addAttribute("artistGroupTitle", artistGroupTitle);
			
		}
		
		return "artistHomepage/artist";
	}
	
	
	
	
	
	
}
