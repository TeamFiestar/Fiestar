package com.TeamFiestar.Fiestar.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.TeamFiestar.Fiestar.board.model.dto.Comment;
import com.TeamFiestar.Fiestar.board.model.service.CommentService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService service;
	
	@GetMapping(value="comment", produces = "application/json")
	public List<Comment> select(int boardNo, Model model, 
			@RequestParam("boardParentCommentNo") int boardParentCommentNo,
			@RequestParam("memberNo") int memberNo,
			@SessionAttribute(value="loginMember", required = false) Member loginMember
			){
		
		Map<String, Integer> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("boardNo", boardNo);
		map.put("boardParentCommentNo", boardParentCommentNo);
		
		List<Comment> comment = service.select(map);
		
		if(!comment.isEmpty()) {
			model.addAttribute("comment", comment);
			if(loginMember != null) {
				map.put("member", loginMember.getMemberNo());
				
				for(Comment vc : comment) {
					map.put("commentNo", vc.getBoardCommentNo());
					vc.setLikeClickComment(service.likeClick(map));
				}
			}
		}
				
		
		
		return comment;
		
		
	}
	
	@PostMapping("comment")
	public int insert(@RequestBody Comment comment) {
		
		return service.insert(comment);
	}
	
	
	@DeleteMapping("comment")
	public int delete(@RequestBody int boardCommentNo) {
		return service.delete(boardCommentNo);
	}
	
	@PostMapping("commentLike")
	@ResponseBody
	public int likeComment(@RequestBody Comment comment) {
		
		return service.likeComment(comment);
	}
	
	@DeleteMapping("deleteLike")
	@ResponseBody
	public int deleteLike(@RequestBody Comment comment) {
		return service.deleteLike(comment);
	}
	
	
}
