package com.TeamFiestar.Fiestar.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamFiestar.Fiestar.board.model.dto.Comment;
import com.TeamFiestar.Fiestar.board.model.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService service;
	
	@GetMapping(value="comment", produces = "application/json")
	public List<Comment> select(int boardNo){
		return service.select(boardNo);
		
		
	}
	
	@PostMapping("comment")
	public int insert(@RequestBody Comment comment) {
		
		return service.insert(comment);
	}
	
	@PutMapping("comment") 
	public int update(@RequestBody Comment comment) {
		return service.update(comment);
		
	}
	
	@DeleteMapping("comment")
	public int delete(@RequestBody int commentNo) {
		return service.delete(commentNo);
	}
	
}
