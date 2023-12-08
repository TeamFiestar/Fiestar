package com.TeamFiestar.Fiestar.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TeamFiestar.Fiestar.admin.model.dto.ArtistNotice;
import com.TeamFiestar.Fiestar.admin.model.service.ArtistAdminAJAXService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("artistAdmin")
@RequiredArgsConstructor
public class ArtistAdminAJAXContoller {
	
	private final ArtistAdminAJAXService service;
	
	@GetMapping("selectNotice")
	@ResponseBody
	public ArtistNotice selectArtistNotice(@RequestParam("artistGroupNoticeNo") int artistGroupNoticeNo) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("artistGroupNoticeNo", artistGroupNoticeNo);
		
		return service.selectArtistNotice(map);
	}
	
	@PutMapping("deleteNotice")
	@ResponseBody
	public int deleteNotice(@RequestBody int noticeNo) {
		return service.deleteNotice(noticeNo);
	}

	
	@PutMapping("updateNotice")
	@ResponseBody
	public int updateNotice(@RequestBody ArtistNotice inputNotice) {
		return service.updateNotice(inputNotice);
	}
	
	
}
