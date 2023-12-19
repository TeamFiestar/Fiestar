package com.TeamFiestar.Fiestar.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TeamFiestar.Fiestar.board.model.dto.Board;
import com.TeamFiestar.Fiestar.board.model.service.BoardService;
import com.TeamFiestar.Fiestar.board.model.service.EditBoardService;
import com.TeamFiestar.Fiestar.media.model.dto.Media;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class EditBoardController {
	
	private final BoardService boardService;
	
	private final EditBoardService service;
	

	
	@PostMapping("{artistGroupTitle}/insert")
	public String insertBoard(Board board, RedirectAttributes ra, 
			@PathVariable("artistGroupTitle") String artistGroupTitle, @SessionAttribute("loginMember") Member loginMember,
			@RequestParam("images") List<MultipartFile> images) throws IllegalStateException, IOException {
			
		board.setMemberNo(loginMember.getMemberNo());
		int artistGroupNo = boardService.artistGroupNo(artistGroupTitle);
		board.setArtistGroupNo(artistGroupNo);
		board.setArtistGroupTitle(artistGroupTitle);
		
		int boardNo = service.insertBoard(board,images);
		
		if(boardNo > 0 ) {
			ra.addFlashAttribute("message", "게시글 작성 성공"); 
			return "redirect:/{artistGroupTitle}/feed"; 
			
		}
		
		ra.addFlashAttribute("message", "게시글 작성 실패...");
		
		return "redirect:/{artistGroupTitle}/feed";
		
		
	}	
	
	@PostMapping("{artistGroupTitle}/feed/{boardNo:[0-9]+}/delete")
	public String boardDelete(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("boardNo") int boardNo, Model model) {
		
		int result = service.deleteBoard(boardNo);
		
		return "redirect:/{artistGroupTitle}/feed";
	}
	
	@GetMapping("{artistGroupTitle}/{boardNo:[0-9]+}/update")
	public String updateBoardDetail(
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("boardNo") int boardNo, Model model,
			RedirectAttributes ra) {
		model.addAttribute("artistGroupTitle",artistGroupTitle);
		
		Board board = service.updateBoardDetail(boardNo);
		model.addAttribute("board",board);
		
		return "artistHomepage/feedUpdate";
	}
	
	
	@PostMapping("{artistGroupTitle}/{boardNo:[0-9]+}/update")
	public String boardUpdate (
			@PathVariable("artistGroupTitle") String artistGroupTitle,
			@PathVariable("boardNo") int boardNo, Model model,
			Board board,
			@RequestParam("images") List<MultipartFile> images,
			RedirectAttributes ra) throws IllegalStateException, IOException {
		
		int result = service.updateBoard(board, images);
		
		if(result > 0 ) {
			 ra.addFlashAttribute("message","게시글 수정 성공");
			 return "redirect:/{artistGroupTitle}/feed";
		} else {
			ra.addFlashAttribute("message", "게시글 수정 실패");
			return "redirect:/";
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
