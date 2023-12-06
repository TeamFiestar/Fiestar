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
@SessionAttributes({"loginMember"})
@RequiredArgsConstructor
public class BoardController {
	
	
	private final BoardService service;
	
	@GetMapping("{artistGroupTitle}/feed")
	public String selectBoard(Model model, @RequestParam Map<String, Object> paramMap, 
			@PathVariable("artistGroupTitle" ) String artistGroupTitle ) {
		{
			
			Map<String, Object> map = service.selectBoard(paramMap);
			
			model.addAttribute("map", map);
			model.addAttribute("artistGroupTitle", artistGroupTitle);
			
		}
		
		return "artistHomepage/feed";
	}

	@GetMapping("{artistGroupTitle}/feed/{boardNo:[0-9]+}")
	public String detail(@PathVariable("artistGroupTitle") String artistGrouptTitle,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember,
			Model model,
			@PathVariable("boardNo") int boardNo, RedirectAttributes ra, HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardNo", boardNo);
		
		Board board = service.detail(map);
		
		String path = null;
		
		if(board != null) {
			model.addAttribute("board", board);
			path = "artistHomepage/feed";
			
			if(loginMember != null) {
				map.put("memberNo", loginMember.getMemberNo());
				int likeCheck = service.likeCheck(map);
				
				if(likeCheck ==1) model.addAttribute("likeCheck", "on");
				
			}
			
			if(loginMember == null || loginMember.getMemberNo() != board.getMemberNo()) {
				
				Cookie c = null;
				
				Cookie[] cookies = req.getCookies();
				
				if(cookies != null) {
					for (Cookie cookie : cookies) {
						if(cookie.getName().equals("readBoardNo")) {
							c= cookie;
							break;
					}
					
				}
			}
				
			int result= 0;
			
			if(c == null) {
				
				c= new Cookie("readBoardNo", "|" + boardNo + "|" );
				
				result = service.updateReadCount(boardNo);
				
					
			} else {
				
				  if (c.getValue().indexOf("|" + boardNo + "|") == -1) {
		                 // 쿠키에 현재 게시글 번호가 없다면

		                 // 기존 값에 게시글 번호 추가해서 다시 세팅
		                 c.setValue(c.getValue() + "|" + boardNo + "|");

		                 // 조회수 증가 서비스 호출
		                 result = service.updateReadCount(boardNo);
		              }
				
			}
			
			 if (result > 0) {
	              board.setReadCount(board.getReadCount() + 1);

	              c.setPath("/");

	              Calendar cal = Calendar.getInstance(); // 싱글톤 패턴
	              cal.add(cal.DATE, 1); // cal이라는 곳에 24시간 후의 시간을 추가

	              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	              Date a = new Date(); // 현재 시간

	              Date temp = new Date(cal.getTimeInMillis()); // 다음날 (24시간 후)

	              Date b = sdf.parse(sdf.format(temp));

	              long diff = (b.getTime() - a.getTime()) / 1000;

	              c.setMaxAge((int) diff); 

	              resp.addCookie(c); 
	           }
				
		}
			
			
	}
		
		
		return path;
		
	}
	
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
			
			Map<String, Object> map = service.selectBoard(paramMap);
			
			model.addAttribute("map", map);
			model.addAttribute("artistGroupTitle", artistGroupTitle);
			
		}
		
		return "artistHomepage/artist";
	}
}
