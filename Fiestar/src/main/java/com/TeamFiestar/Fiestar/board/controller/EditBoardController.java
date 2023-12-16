package com.TeamFiestar.Fiestar.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.service.BoardService;
import com.TeamFiestar.Fiestar.board.model.service.EditBoardService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class EditBoardController {
	
	private final BoardService boardService;
	
	private final EditBoardService service;
	
	@GetMapping("{artistGroupTitle}/insert")
	public String insert() {
		
		return "artistHomepage/feedWrite";
	}
	
	@PostMapping("{artistGroupTitle}/insert")
	public String insertBoard(Board board, RedirectAttributes ra, 
			@PathVariable("artistGroupTitle") String artistGroupTitle, @SessionAttribute("loginMember") Member loginMember,
			@RequestParam("images") List<MultipartFile> images) throws IllegalStateException, IOException {
			
		board.setMemberNo(loginMember.getMemberNo());
		board.setArtistGroupTitle(artistGroupTitle);
		
		int boardNo = service.insertBoard(board,images);
		
		if(boardNo > 0 ) {
			ra.addFlashAttribute("message", "게시글 작성 성공"); 
			return "redirect:/{artistGroupTitle}/feed"; 
			
		}
		
		ra.addFlashAttribute("message", "게시글 작성 실패...");
		
		return "redirect:/{artistGroupTitle}/feed";
		
		
	}	

}
