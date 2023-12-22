package com.TeamFiestar.Fiestar.media.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.events.Comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.TeamFiestar.Fiestar.admin.model.dto.Report;
import com.TeamFiestar.Fiestar.media.model.dto.MediaComment;
import com.TeamFiestar.Fiestar.media.model.service.MediaCommentService;
import com.TeamFiestar.Fiestar.member.model.dto.Member;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("mediaComment")
@RequiredArgsConstructor
public class MediaCommentController {

	private final MediaCommentService service;
	
	@PostMapping("inputComment")
	@ResponseBody
	public int inputComment(@RequestBody MediaComment inputComment,
			@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		
		inputComment.setMemberNo(loginMember.getMemberNo());
		int result = service.inputComment(inputComment);
		
		return result;
		
	}
	
	@GetMapping(value="selectComment", produces = "application/json")
	@ResponseBody
	public List<Comment> selectComment(@RequestParam("mediaNo") int mediaNo, @RequestParam("mediaParentCommentNo") int mediaParentCommentNo
			,@RequestParam("memberNo") int memberNo) {
		
		Map<String, Integer> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("mediaNo", mediaNo);
		map.put("mediaParentCommentNo", mediaParentCommentNo);
		
		return service.selectComment(map);
		
	}
	
	@PutMapping("deleteComment")
	@ResponseBody
	public int deleteComment(@RequestBody int commentNo) {
		return service.deleteComment(commentNo);
	}
	
	@PutMapping("insertLike")
	@ResponseBody
	public int insertLike(@RequestBody MediaComment comment) {
		return service.insertLike(comment);
	}
	
	@DeleteMapping("deleteLike")
	@ResponseBody
	public int deleteLike(@RequestBody MediaComment comment) {
		return service.deleteLike(comment);
	}
	
	@PostMapping("insertReport")
	@ResponseBody
	public int insertReport(@RequestBody Report report) {
		return service.insertReport(report);
	}
	
}
