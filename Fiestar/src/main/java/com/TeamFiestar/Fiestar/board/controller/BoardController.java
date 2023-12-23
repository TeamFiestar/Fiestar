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

import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.dto.BoardImg;
import com.TeamFiestar.Fiestar.board.model.service.BoardService;
import com.TeamFiestar.Fiestar.member.model.dto.ArtistGroup1;
import com.TeamFiestar.Fiestar.member.model.dto.Member;
import com.TeamFiestar.Fiestar.member.model.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes({"loginMember", "artistGroupTitle", "artistGroupNo"})
@RequiredArgsConstructor
public class BoardController {
	
	
	private final BoardService service;
	private final MemberService memberService;
	

	
	@GetMapping("{artistGroupTitle}/feed")
	public String selectBoard(Model model, @RequestParam Map<String, Object> paramMap, 
			@PathVariable("artistGroupTitle") String artistGroupTitle
			, ArtistGroup1 artistGroup,
			RedirectAttributes ra ,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		
		
			int artistGroupNo = service.artistGroupNo(artistGroupTitle);
			
			int subscribe = memberService.checkSubscribeGroupTitle(artistGroupTitle, loginMember.getMemberNo());
			if (subscribe == 0) {
				String message = "구독 후 이용해 주십시오";
				ra.addFlashAttribute("message", message);
				return "redirect:/";
			}
			
			paramMap.put("artistGroupNo", artistGroupNo);
			
			Map<String, Object> map = service.selectBoard(paramMap);
			Map<String, Object> map2 = service.artistGroup(artistGroup);
			model.addAttribute("map2",map2);
			model.addAttribute("artistGroupNo",artistGroupNo);
			model.addAttribute("map", map);
			model.addAttribute("artistGroupTitle", artistGroupTitle);
			
			
		return "artistHomepage/feed";
	}
	
	@GetMapping("{artistGroupTitle}/feed/{boardNo:[0-9]+}")
	public String boardDetail(Model model, 
			@PathVariable("artistGroupTitle" ) String artistGroupTitle, 
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			@PathVariable("boardNo" ) int boardNo,
			ArtistGroup1 artistGroup,
			RedirectAttributes ra 
			) {
			int subscribe = memberService.checkSubscribeGroupTitle(artistGroupTitle, loginMember.getMemberNo());
			if (subscribe == 0) {
				String message = "구독 후 이용해 주십시오";
				ra.addFlashAttribute("message", message);
				return "redirect:/";
			}
		
			int artistGroupNo = service.artistGroupNo(artistGroupTitle);
			
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("artistGroupNo", artistGroupNo);
			
			Map<String, Object> map = service.selectBoard(paramMap);
			Map<String, Object> map2 = service.artistGroup(artistGroup);
			model.addAttribute("map2",map2);
			model.addAttribute("artistGroupNo",artistGroupNo);
			model.addAttribute("map", map);
			model.addAttribute("artistGroupTitle", artistGroupTitle);
		
		
			Map<String, Object> boardDetailMap = new HashMap<>();
			boardDetailMap.put("boardNo", boardNo);
			boardDetailMap.put("artistGroupTitle", artistGroupTitle);
			
			Board board = service.boardDetail(boardDetailMap);
			
			if(board != null) {
				model.addAttribute("board", board);
				
			if(loginMember != null) {
					map.put("memberNo", loginMember.getMemberNo());
					int likeCheck = service.likeCheck(map);
					
					if(likeCheck ==1) model.addAttribute("likeCheck", "on");
					
				}
			}
		
		return "artistHomepage/feed";
	}
	
	@GetMapping("AJAXboardDetail")
	@ResponseBody
	public Board AJAXboardDetail(@RequestParam("boardNo") int boardNo,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardNo", boardNo);
		map.put("memberNo", loginMember.getMemberNo());
		
		Board board = service.boardDetail(map);
		
		return board;
	}
		
	
	@PostMapping("AJAXboardDetail/like")
	@ResponseBody
	public int like(@RequestBody Map<String, Object> paramMap, 
			@SessionAttribute("loginMember") Member loginMember) {
		
		paramMap.put("memberNo", loginMember.getMemberNo());
		
		
		return service.like(paramMap);
	}
	
	
	
	
	@GetMapping("{artistGroupTitle}/artist")
	public String selectartistBoard(Model model, @RequestParam Map<String, Object> paramMap, 
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			RedirectAttributes ra ,
			@PathVariable("artistGroupTitle" ) String artistGroupTitle) {
		
		
		int subscribe = memberService.checkSubscribeGroupTitle(artistGroupTitle, loginMember.getMemberNo());
		if (subscribe == 0) {
			String message = "구독 후 이용해 주십시오";
			ra.addFlashAttribute("message", message);
			return "redirect:/";
		}
		
		
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
	
//	@PostMapping("insertReport")
//	@ResponseBody
//	public int insertReport (@RequestBody Report report) {
//		return service.insertReport(report);
//	}
	
	
	
	
}
